package com.b2b.rqst

import android.R
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import com.b2b.rqst.databinding.ToastBinding
import java.util.*


object CustomToast {

    fun make(context: Context, text: String?){
        val toastBinding: ToastBinding = ToastBinding.inflate(LayoutInflater.from(context), null, false)
        if (text == null){
            toastBinding.text.text = ""
        }else{
            toastBinding.text.text = text
        }
        val toast = Toast(context)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.duration = Toast.LENGTH_LONG
        toast.view = toastBinding.root
        toast.show()
    }

}