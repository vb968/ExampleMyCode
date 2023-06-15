package com.b2b.rqst.ui.main.addrequest

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.b2b.rqst.Const
import com.b2b.rqst.model.FormAnswer
import com.b2b.rqst.model.FormRequest
import com.b2b.rqst.model.Status
import com.b2b.rqst.network.ApiFactory
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

class AddRequestModel @Inject constructor(private val preferences: SharedPreferences, private val context: Context) : ViewModel() {

    val answer = MutableLiveData<FormRequest?>()
    fun getRequest(filter: Status, search: String) = MutableLiveData<Int>().apply {
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
                    Toast.makeText(context, answerForm.toString(), Toast.LENGTH_LONG).show()
                }
            }else{
                val apiError = apiResponse.errorBody()?.string()
                answer.value = GsonBuilder().create().fromJson(apiError, FormRequest::class.java)
                Toast.makeText(context, apiError, Toast.LENGTH_LONG).show()
            }
        }
    }
}