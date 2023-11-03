package com.example.miaula.di

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.bu_android.network.LiveDataCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.auth0.android.jwt.DecodeException
import com.auth0.android.jwt.JWT
import com.example.miaula.MainActivity
import com.example.miaula.api.MiAulaService
import com.example.miaula.models.SessionTokens
import com.example.miaula.network.AuthorizationType
import com.example.miaula.sharedPreferences.SessionPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.internal.http2.ErrorCode
import okhttp3.internal.http2.StreamResetException
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

interface AuthorizationInterceptor : Interceptor
interface RefreshTokenInterceptor : Interceptor
interface UserAgentInterceptor : Interceptor

const val BASE_URL = "https://dev-balanzuniversity-api.balpays.com/"
val miAulaApiModule = module {

    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .create()
    }

    fun provideRefreshTokenInterceptor(
        preferences: SessionPreferences,
        appContext: Context
    ): RefreshTokenInterceptor {
        return object : RefreshTokenInterceptor {
            val lock = Object()

            private fun getAuthHeader(token: String): String {
                return "Bearer $token"
            }

            @Synchronized
            fun refreshToken() {
                preferences.getToken()?.let { token ->
                    try {
                        val jwt = JWT(token)
                        if (!jwt.isExpired(0)) {
                            //Token was already renewed in another thread
                            Log.d("REFRESH", "PREVENT REFRESHING")
                            return
                        }
                    } catch (e: DecodeException) {

                    }
                }

                Log.d("REFRESH", "REFRESHING")
                val request: Request = Request.Builder()
                    .url(BASE_URL + "auth/login/refresh-token")
                    .method("POST", ByteArray(0).toRequestBody(null, 0, 0))
                    .addHeader("Authorization", getAuthHeader(preferences.getRefreshToken() ?: ""))
                    .build()

                try {
                    val response = OkHttpClient().newCall(request).execute()
                    if (response.code == 200) {
                        val jsonData = response.body!!.string()
                        val gson = Gson()
                        val refreshTokenResponseModel: SessionTokens = gson.fromJson(
                            jsonData,
                            SessionTokens::class.java
                        )
                        preferences.remove(SessionPreferences.SESSION_PASSWORD_TOKEN)
                        preferences.setToken(refreshTokenResponseModel.token)
                        preferences.setRefreshToken(refreshTokenResponseModel.refreshToken)
                    } else {
                        if (preferences.getToken() != null) {
                            preferences.clear()
                            Log.d("REFRESH", "REDIRECTING")
                            val intent = Intent(appContext, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            appContext.startActivity(intent)
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }


            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                synchronized(lock) {
                    val request: Request = chain.request()
                    val newRequest: Request
                    var response: Response? = null
                    var unauthorized = true
                    try {
                        response = chain.proceed(request)
                        unauthorized = response.code == 401
                    } catch (e: StreamResetException) {
                        if (e.errorCode != ErrorCode.NO_ERROR) {
                            throw e
                        }
                    }

                    return if (unauthorized) {
                        refreshToken()
                        val token = preferences.getToken()
                        newRequest = if (token != null) {
                            request.newBuilder()
                                .removeHeader("Authorization")
                                .addHeader("Authorization", getAuthHeader(token))
                                .build()
                        } else {
                            request.newBuilder().build()
                        }
                        response?.close()
                        chain.proceed(newRequest)
                    } else {
                        response!!
                    }

                }
            }
        }
    }

    fun provideAuthorizationInterceptor(preferences: SessionPreferences): AuthorizationInterceptor {
        return object : AuthorizationInterceptor {
            private fun getAuthHeader(token: String): String {
                return "Bearer $token"
            }

            private fun getToken(request: Request): String? {
                return when (AuthorizationType.fromRequest(request)) {
                    AuthorizationType.NONE -> null
                    AuthorizationType.BIOMETRIC_TOKEN -> null
                    AuthorizationType.PASSWORD_TOKEN -> preferences.getPasswordToken()
                    AuthorizationType.ACCESS_TOKEN -> preferences.getToken()
                }
            }

            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request()
                val newRequest: Request
                val token = getToken(request)
                newRequest = if (token != null) {
                    request.newBuilder()
                        .addHeader("Authorization", getAuthHeader(token))
                        .build()
                } else {
                    request.newBuilder().build()
                }
                return chain.proceed(newRequest)
            }
        }
    }

    fun provideUserAgentInterceptor() : UserAgentInterceptor {
        return object : UserAgentInterceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request()
                val newRequest = request.newBuilder().addHeader("User-Agent", "Android").build()
                return chain.proceed(newRequest)
            }
        }
    }

    fun provideHttpClient(
        authorizationInterceptor: AuthorizationInterceptor,
        refreshTokenInterceptor: RefreshTokenInterceptor,
        userAgentInterceptor: UserAgentInterceptor
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClientBuilder.addNetworkInterceptor(interceptor) // same for .addInterceptor(...)
            .connectTimeout(30, TimeUnit.SECONDS) //Backend is really slow
            .writeTimeout(90, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(authorizationInterceptor)
        okHttpClientBuilder.addInterceptor(refreshTokenInterceptor)
        okHttpClientBuilder.addInterceptor(userAgentInterceptor)
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(
        factory: Gson,
        client: OkHttpClient,
        baseUrl: String = BASE_URL
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(factory))
        .addCallAdapterFactory(LiveDataCallAdapterFactory()) // To get LiveData response
        .client(client)
        .build()

    fun provideBalanzUniversityApi(retrofit: Retrofit): MiAulaService {
        return retrofit.create(MiAulaService::class.java)
    }

    single { provideGson() }
    single { provideAuthorizationInterceptor(get()) }
    single { provideRefreshTokenInterceptor(get(), get()) }
    single { provideUserAgentInterceptor() }
    single { provideHttpClient(get(), get(), get()) }
    single { provideRetrofit(get(), get()) }
    single { provideBalanzUniversityApi(get()) }
}