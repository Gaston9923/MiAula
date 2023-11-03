package com.example.miaula.api

import com.example.miaula.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface MiAulaService {

//    @POST
//    suspend fun login(
//        @Url url: String,
//        @Body body: LoginBody
//    ): Response<ResponseLogin>
//
//    @GET
//    suspend fun getBanners(
//        @Url url: String
//    ): Response<Banners>
//
    @GET
    suspend fun getUser(
        @Url url: String
    ): Response<User>
//
//    @GET
//    suspend fun getCourses(
//        @Url url: String
//    ): Response<Courses>
//
//    @GET
//    suspend fun getDetailsCourse(
//        @Url url: String
//    ): Response<DetailsCourse>
//
//    @Multipart
//    @PATCH
//    suspend fun updateProfilePicture(
//        @Url url: String,
//        @Part image: MultipartBody.Part
//    ): Response<ResponseProfilePhoto>






}