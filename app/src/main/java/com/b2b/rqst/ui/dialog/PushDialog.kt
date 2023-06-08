package com.b2b.rqst.ui.dialog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.Gravity
import com.b2b.rqst.databinding.ActivityPushDialogBinding


class PushDialog : Activity() {
    private var _binding: ActivityPushDialogBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPushDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding.buttonGotIt.setOnClickListener {
           binding.buttonGotIt.isClickable = false
           finish()
       }
        window.setGravity(Gravity.TOP)
    }

}