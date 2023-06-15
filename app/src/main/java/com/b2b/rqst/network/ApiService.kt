package com.b2b.rqst.network

import com.b2b.rqst.model.FormAnswer
import com.b2b.rqst.model.FormRequest
import com.b2b.rqst.model.LoginAnswer
import com.b2b.rqst.model.LoginBody
import com.b2b.rqst.model.Status
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @Headers("accept: application/json") @GET("form")
    suspend fun form(@Header("Authorization") token: String): Response<FormAnswer>

    @Headers("accept: application/json") @GET("form-request")
    suspend fun formRequest(@Header("Authorization") token: String, @Query("filter[status]") filter: Status, @Query("search") search: String): Response<FormRequest>

    @Headers("accept: application/json") @POST("auth")
    suspend fun login(@Body body: LoginBody): Response<LoginAnswer>
//    suspend fun login(@Header("Authorization") token: String, @Body body: LoginBody): LoginAnswer



}