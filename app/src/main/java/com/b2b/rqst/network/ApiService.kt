package com.b2b.rqst.network

import com.b2b.rqst.model.BaseAnswer
import com.b2b.rqst.model.Data
import com.b2b.rqst.model.FormRequest
import com.b2b.rqst.model.LoginAnswer
import com.b2b.rqst.model.LoginBody
import com.b2b.rqst.model.Request
import com.b2b.rqst.model.Status
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @Headers("accept: application/json") @GET("form")
    suspend fun form(@Header("Authorization") token: String): Response<BaseAnswer<ArrayList<Data>?>>

    @Headers("accept: application/json") @GET("form-request")
    suspend fun formRequest(@Header("Authorization") token: String, @Query("filter[status]") filter: Status, @Query("search") search: String): Response<FormRequest>

    @Headers("accept: application/json") @POST("auth")
    suspend fun login(@Body body: LoginBody): Response<LoginAnswer>

    @Headers("accept: application/json") @POST("form-request/before-create")
    suspend fun beforeСreate(@Header("Authorization") token: String): Response<BaseAnswer<Request.Chat>>

//    suspend fun login(@Header("Authorization") token: String, @Body body: LoginBody): LoginAnswer



}