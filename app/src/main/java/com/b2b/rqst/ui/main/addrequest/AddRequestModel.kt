package com.b2b.rqst.ui.main.addrequest

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.b2b.rqst.Const
import com.b2b.rqst.model.BaseAnswer
import com.b2b.rqst.model.Data
import com.b2b.rqst.network.ApiFactory
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AddRequestModel @Inject constructor(private val preferences: SharedPreferences, private val context: Context) : ViewModel() {

    val answer = MutableLiveData<BaseAnswer<ArrayList<Data>?>?>()
    fun getRequest() = MutableLiveData<Int>().apply {
        viewModelScope.launch {


            val token = preferences.getString(Const.TOKEN_SAVE, null)
            if (token == null){
                //TODO Переход на страницу авторизации
                return@launch
            }
            val apiResponse = try {
                ApiFactory.getService().form(" Bearer $token")
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
                val type = object : TypeToken<BaseAnswer<ArrayList<Data>?>>() {}.type
                answer.value = GsonBuilder().create().fromJson<BaseAnswer<ArrayList<Data>?>>(apiError, type)
                Toast.makeText(context, apiError, Toast.LENGTH_LONG).show()
            }
        }
    }
    fun beforeСreate() = MutableLiveData<Int>().apply {
        viewModelScope.launch {


            val token = preferences.getString(Const.TOKEN_SAVE, null)
            if (token == null){
                //TODO Переход на страницу авторизации
                return@launch
            }
            val apiResponse = try {
                ApiFactory.getService().beforeСreate(" Bearer $token")
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
                    Toast.makeText(context, answerForm.toString(), Toast.LENGTH_LONG).show()
                }
            }else{
                val apiError = apiResponse.errorBody()?.string()
 //               answer.value = GsonBuilder().create().fromJson(apiError, BaseAnswer::class.java)
                Toast.makeText(context, apiError, Toast.LENGTH_LONG).show()
            }
        }
    }

}