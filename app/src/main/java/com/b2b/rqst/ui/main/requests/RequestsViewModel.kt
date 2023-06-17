package com.b2b.rqst.ui.main.requests

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.b2b.rqst.Const
import com.b2b.rqst.CustomToast
import com.b2b.rqst.model.BaseAnswer
import com.b2b.rqst.model.Request
import com.b2b.rqst.model.Status
import com.b2b.rqst.network.ApiFactory
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestsViewModel @Inject constructor(private val preferences: SharedPreferences, private val context: Context) : ViewModel() {


    val answer = MutableLiveData<BaseAnswer<ArrayList<Request>?>?>()
    fun getForms(filter: Status = Status.all, search: String = "!") = MutableLiveData<Int>().apply {
        viewModelScope.launch {
            val token = preferences.getString(Const.TOKEN_SAVE, null)
            if (token == null){
                //TODO Переход на страницу авторизации
                return@launch
            }
            val apiResponse = try {
                ApiFactory.getService().formRequest(" Bearer $token", filter, search)
            } catch (error: Throwable) {
                null
            }
            if (apiResponse == null){
                answer.value = null
            }else if (apiResponse.code() == 200){
                val answerForm = apiResponse.body()
                if (answerForm == null) {
                    answer.value = null
                }else if (answerForm.success){
                    answer.value = answerForm
                    CustomToast.make(context, answerForm.toString())
                }
            }else{
                val apiError = apiResponse.errorBody()?.string()
                val type = object : TypeToken<BaseAnswer<ArrayList<Request>?>>() {}.type
                answer.value = GsonBuilder().create().fromJson(apiError, type)
                CustomToast.make(context, apiError)
            }
        }
    }
    fun statuses() = MutableLiveData<Int>().apply {
        viewModelScope.launch {
            val token = preferences.getString(Const.TOKEN_SAVE, null)
            if (token == null){
                //TODO Переход на страницу авторизации
                return@launch
            }
            val apiResponse = try {
                ApiFactory.getService().statuses(" Bearer $token")
            } catch (error: Throwable) {
                null
            }
            if (apiResponse == null){
                answer.value = null
            }else if (apiResponse.code() == 200){
                val answerForm = apiResponse.body()
                if (answerForm == null) {
                    answer.value = null
                }else if (answerForm.success){
//                    answer.value = answerForm
                    CustomToast.make(context, answerForm.toString())
                }
            }else{
                val apiError = apiResponse.errorBody()?.string()
 //               answer.value = GsonBuilder().create().fromJson(apiError, FormRequest::class.java)
                CustomToast.make(context, apiError)
            }
        }
    }
}