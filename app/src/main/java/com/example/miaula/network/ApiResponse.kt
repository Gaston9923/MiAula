package com.example.miaula.network

import com.google.gson.Gson
import retrofit2.Response

/**
 * Common class used by API responses.
 * @param <T> the type of the response object
</T> */
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(
                ApiError(
                    ApiErrorType.EXCEPTION_ERROR,
                    error.message ?: "unknown error"
                )
            )
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body = body)
                }
            } else {
                val gson = Gson()
                var apiError: ApiError? = null
                try {
                    apiError = gson.fromJson(response.errorBody()!!.charStream(), ApiError::class.java)
                } catch (e: Exception) {

                }
                if (apiError != null) {
                    if (apiError.type === null) {
                        apiError.type = ApiErrorType.UNKNOWN_ERROR
                    }
                    ApiErrorResponse(apiError)
                } else {
                    val msg = response.errorBody()?.string()
                    val errorMsg = if (msg.isNullOrEmpty()) {
                        response.message()
                    } else {
                        msg
                    }
                    ApiErrorResponse(ApiError(ApiErrorType.UNKNOWN_ERROR, errorMsg))
                }
            }
        }
    }
}


/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(
    val body: T
) : ApiResponse<T>()

data class ApiErrorResponse<T>(val body: ApiError) : ApiResponse<T>()
