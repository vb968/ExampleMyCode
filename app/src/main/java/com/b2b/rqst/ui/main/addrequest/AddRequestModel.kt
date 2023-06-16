package com.b2b.rqst.ui.main.addrequest

import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.webkit.MimeTypeMap
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
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import java.util.Locale
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
    fun imageUpload(uri: Uri, name: String, byteArray: ByteArray?, contentResolver: ContentResolver) = MutableLiveData<Int>().apply {
        viewModelScope.launch {
            val token = preferences.getString(Const.TOKEN_SAVE, null)
            if (token == null){
                //TODO Переход на страницу авторизации
                return@launch
            }
            val requestBody = getRequestBody(byteArray!!, 0, byteArray.size, uri.mimeType(contentResolver))
            val body = MultipartBody.Part.createFormData(name = "image", filename = name, body = requestBody)
            val apiResponse = try {
                ApiFactory.getService().imageUpload(" Bearer $token", body)
            } catch (error: Throwable) {
                Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
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
    private fun getRequestBody(byteArray: ByteArray, offset: Int = 0, size: Int, contentType: MediaType? = null): RequestBody {
        return object : RequestBody(){
            override fun contentType() = contentType

            override fun writeTo(sink: BufferedSink) {
                sink.write(byteArray, offset, size)
            }
        }
    }
    private fun Uri.mimeType(contentResolver: ContentResolver)
            : MediaType? {
        if (scheme.equals(ContentResolver.SCHEME_CONTENT)) {
            // get (image/jpeg, video/mp4) from ContentResolver if uri scheme is "content://"
            return contentResolver.getType(this)?.toMediaTypeOrNull()
        } else {
            // get (.jpeg, .mp4) from uri "file://example/example.mp4"
            val fileExtension = MimeTypeMap.getFileExtensionFromUrl(toString())
            // turn ".mp4" into "video/mp4"
            return MimeTypeMap.getSingleton()
                .getMimeTypeFromExtension(fileExtension.toLowerCase(Locale.US))
                ?.toMediaTypeOrNull()
        }
    }

}