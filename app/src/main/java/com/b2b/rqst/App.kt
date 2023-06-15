package com.b2b.rqst

import android.app.Application
import android.content.SharedPreferences
import com.b2b.rqst.Const.BASE_URL_DEBUG
import com.b2b.rqst.Const.BASE_URL_RELEASE
import dagger.hilt.android.HiltAndroidApp
import java.util.*
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject lateinit var preferences: SharedPreferences
    private val Tag = "Show"
    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        val isDebug = BuildConfig.DEBUG
//        val isDebug = false

        val baseUrl = if(isDebug){
            BASE_URL_DEBUG
        }else{
            BASE_URL_RELEASE
        }
    }
}