package com.b2b.rqst.ui.auth

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.b2b.rqst.Const
import com.b2b.rqst.model.LoginAnswer
import com.b2b.rqst.model.LoginBody
import com.b2b.rqst.network.ApiFactory
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject constructor(private val preferences: SharedPreferences)   : ViewModel(){
    val answer = MutableLiveData<LoginAnswer?>()

    fun login(loginBody: LoginBody){
        viewModelScope.launch {
            val token = preferences.getString(Const.TOKEN_SAVE, null)
            if (token != null){
                answer.value = LoginAnswer(true, LoginAnswer.Data(token), "", null)
                return@launch
            }
            val apiResponse = try {
                ApiFactory.getService().login(loginBody)
            } catch (error: Throwable) {
                Log.d("Show", error.toString())
                null
            }
            if (apiResponse == null){
                answer.value = null
            }else if (apiResponse.code() == 200){
                val answerLogin = apiResponse.body()
                if (answerLogin == null) {
                    answer.value = null
                }else if (answerLogin.success){
                    preferences.edit().putString(Const.TOKEN_SAVE, answerLogin.data?.token).commit()
                    answer.value = answerLogin
                }
            }else{
                val apiError = apiResponse.errorBody()?.string()
                answer.value = GsonBuilder().create().fromJson(apiError, LoginAnswer::class.java)
            }

        }
    }
}