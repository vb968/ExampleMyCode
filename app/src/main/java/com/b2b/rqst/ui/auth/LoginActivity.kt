package com.b2b.rqst.ui.auth

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.b2b.rqst.R
import com.b2b.rqst.databinding.ActivityLoginBinding
import com.b2b.rqst.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.checkboxSwitch.setOnClickListener{
            if (binding.checkMark.visibility == View.VISIBLE){
                binding.checkMark.visibility = View.GONE
            }else{
                binding.checkMark.visibility = View.VISIBLE
            }
        }

    }
}