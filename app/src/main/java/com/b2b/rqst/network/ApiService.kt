package com.b2b.rqst.network

import com.b2b.rqst.model.LoginAnswer
import com.b2b.rqst.model.LoginBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
//    @GET("bingo/api/systems") suspend fun getInfo(@Header("Authorization") token: String): Response<ApiResponse<List<Information>>>


    @Headers("accept: application/json") @POST("auth")
    suspend fun login(@Body body: LoginBody): LoginAnswer
//    suspend fun login(@Header("Authorization") token: String, @Body body: LoginBody): LoginAnswer



}