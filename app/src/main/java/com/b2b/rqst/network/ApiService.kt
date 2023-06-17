package com.b2b.rqst.network

import com.b2b.rqst.model.BaseAnswer
import com.b2b.rqst.model.ChatSupport
import com.b2b.rqst.model.Create
import com.b2b.rqst.model.Data
import com.b2b.rqst.model.FileAnswer
import com.b2b.rqst.model.LoginAnswer
import com.b2b.rqst.model.LoginBody
import com.b2b.rqst.model.Request
import com.b2b.rqst.model.Status
import com.b2b.rqst.model.Statuses
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @Headers("accept: application/json") @GET("form")
    suspend fun form(@Header("Authorization") token: String): Response<BaseAnswer<ArrayList<Data>?>>

    @Headers("accept: application/json") @GET("form-request/statuses")
    suspend fun statuses(@Header("Authorization") token: String): Response<BaseAnswer<Statuses>>


    @Headers("accept: application/json") @GET("form-request")
    suspend fun formRequest(@Header("Authorization") token: String, @Query("filter[status]") filter: Status, @Query("search") search: String): Response<BaseAnswer<ArrayList<Request>?>>

    @Headers("accept: application/json") @GET("chat/support")
    suspend fun chatSupport(@Header("Authorization") token: String): Response<BaseAnswer<ChatSupport>>

    @Headers("accept: application/json") @POST("auth")
    suspend fun login(@Body body: LoginBody): Response<LoginAnswer>

    @Headers("accept: application/json") @POST("form-request/before-create")
    suspend fun beforeСreate(@Header("Authorization") token: String): Response<BaseAnswer<Request.Chat>>

    @Headers("accept: application/json") @POST("form-request/create")
    suspend fun create(@Header("Authorization") token: String, @Body body: Create): Response<BaseAnswer<Request>>

    @Multipart @Headers("accept: application/json") @POST("upload/image")
    suspend fun imageUpload(@Header("Authorization") token: String, @Part image: MultipartBody.Part): Response<BaseAnswer<ArrayList<FileAnswer>>>
//    suspend fun image(@Header("Authorization") token: String, @Body image: File): Response<BaseAnswer<Any>>

//    suspend fun login(@Header("Authorization") token: String, @Body body: LoginBody): LoginAnswer



}