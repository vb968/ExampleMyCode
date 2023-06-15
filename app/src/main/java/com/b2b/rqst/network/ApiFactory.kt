package com.b2b.rqst.network

import com.b2b.rqst.App
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*
import java.util.concurrent.TimeUnit

object ApiFactory {
    private var mBingoService: ApiService
    private const val CONNECT_TIMEOUT = 15
    private const val WRITE_TIMEOUT = 60
    private const val TIMEOUT = 60
    private val loggingInterceptor = HttpLoggingInterceptor()

    init {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val sslClient = Objects.requireNonNull(HttpsTrustManager.getOkHttpClient("SSL"))
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS).addInterceptor(loggingInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)

        val retrofit = Retrofit.Builder()
            .baseUrl(App.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(sslClient)
//            .client(client.build())
            .build()
        mBingoService = retrofit.create(ApiService::class.java)
    }
    fun getBingoService() : ApiService {
        return mBingoService
    }

}