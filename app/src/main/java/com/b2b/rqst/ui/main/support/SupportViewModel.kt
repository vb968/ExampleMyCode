package com.b2b.rqst.ui.main.support

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.b2b.rqst.Const
import com.b2b.rqst.CustomToast
import com.b2b.rqst.model.ChatSend
import com.b2b.rqst.network.ApiFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupportViewModel @Inject constructor(private val preferences: SharedPreferences, private val context: Context) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun chatSupport() = MutableLiveData<Int>().apply {
        viewModelScope.launch {
            val token = preferences.getString(Const.TOKEN_SAVE, null)
            if (token == null){
                //TODO Переход на страницу авторизации
                return@launch
            }
            val apiResponse = try {
                ApiFactory.getService().chatSupport(" Bearer $token")
            } catch (error: Throwable) {
                null
            }
            if (apiResponse == null){
 //               answer.value = null
            }else if (apiResponse.code() == 200){
                val answerForm = apiResponse.body()
                if (answerForm == null) {
//                    answer.value = null
                }else if (answerForm.success){
                    //                    answer.value = answerForm
                    CustomToast.make(context, answerForm.toString())
                    chatSend(answerForm.data.uid)
                    chatMessages(answerForm.data.uid)
                }
            }else{
                val apiError = apiResponse.errorBody()?.string()
                //               answer.value = GsonBuilder().create().fromJson(apiError, BaseAnswer::class.java)
                CustomToast.make(context, apiError)
            }
        }
    }
    fun chatSend(uid: String) = MutableLiveData<Int>().apply {
        viewModelScope.launch {
            val token = preferences.getString(Const.TOKEN_SAVE, null)
            if (token == null){
                //TODO Переход на страницу авторизации
                return@launch
            }
            val chatSend = ChatSend("4", "996d8ec9-01f9-45d6-88ae-5c71d2cc0564")
            val apiResponse = try {
                ApiFactory.getService().chatSend(" Bearer $token", chatSend, uid)
            } catch (error: Throwable) {
                null
            }
            if (apiResponse == null){
 //               answer.value = null
            }else if (apiResponse.code() == 200){
                val answerForm = apiResponse.body()
                if (answerForm == null) {
//                    answer.value = null
                }else if (answerForm.success){
                    //                    answer.value = answerForm
                    CustomToast.make(context, answerForm.toString())
                }
            }else{
                val apiError = apiResponse.errorBody()?.string()
                //               answer.value = GsonBuilder().create().fromJson(apiError, BaseAnswer::class.java)
                CustomToast.make(context, apiError)
            }
        }
    }
    fun chatMessages(uid: String) = MutableLiveData<Int>().apply {
        viewModelScope.launch {
            val token = preferences.getString(Const.TOKEN_SAVE, null)
            if (token == null){
                //TODO Переход на страницу авторизации
                return@launch
            }
            val apiResponse = try {
                ApiFactory.getService().chatMessages(" Bearer $token", uid)
            } catch (error: Throwable) {
                null
            }
            if (apiResponse == null){
 //               answer.value = null
            }else if (apiResponse.code() == 200){
                val answerForm = apiResponse.body()
                if (answerForm == null) {
//                    answer.value = null
                }else if (answerForm.success){
                    //                    answer.value = answerForm
                    CustomToast.make(context, answerForm.toString())
                }
            }else{
                val apiError = apiResponse.errorBody()?.string()
                //               answer.value = GsonBuilder().create().fromJson(apiError, BaseAnswer::class.java)
                CustomToast.make(context, apiError)
            }
        }
    }


}