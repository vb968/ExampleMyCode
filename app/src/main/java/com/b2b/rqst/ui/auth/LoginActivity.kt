package com.b2b.rqst.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.b2b.rqst.R
import com.b2b.rqst.databinding.ActivityLoginBinding
import com.b2b.rqst.databinding.ActivityMainBinding
import com.b2b.rqst.model.LoginBody
import com.b2b.rqst.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
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
        val viewModel: LoginViewModel by viewModels()
        this.viewModel = viewModel
        binding.buttonLogin.setOnClickListener{
            /*val intent = (Intent(this, MainActivity()::class.java))
            startActivity(intent)*/
            doLogin()
        }
        viewModel.answer.observe(this) { answer ->
            binding.progress.visibility = View.GONE
            if (answer != null){
                if (answer.success){
                    val intent = (Intent(this, MainActivity()::class.java))
                    startActivity(intent)
                }else{
                    Toast.makeText(this, answer.message, Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, R.string.an_error_occurred_communicating_server, Toast.LENGTH_LONG).show()
            }
        }

        // For Test!
        binding.editUsername.text = SpannableStringBuilder("agent")
        binding.editCode.text = SpannableStringBuilder("agent")
        // For Test!

    }
    private fun doLogin(userEmail: String? = null, userPassword: String? = null) {
        if (userEmail != null && userPassword != null){
            binding.editUsername.text = SpannableStringBuilder(userEmail)
            binding.accessCode.text = SpannableStringBuilder(userPassword)
        }
        if (!binding.checkMark.isVisible){
            Toast.makeText(this, R.string.please_confirm_you_not_robot, Toast.LENGTH_LONG).show()
            return
        }
        val email = binding.editUsername.text.toString().trim()
        if (email.isEmpty()/* || !Patterns.EMAIL_ADDRESS.matcher(email).matches()*/){
            Toast.makeText(this, R.string.please_enter_email, Toast.LENGTH_LONG).show()
            return
        }
        val password = binding.editCode.text.toString().trim()
        if (password.isEmpty()){
            Toast.makeText(this, R.string.please_enter_access_code, Toast.LENGTH_LONG).show()
            return
        }
        binding.progress.visibility = View.VISIBLE
        val loginBody = LoginBody(email, password)
        //        val login = Login("vb968g@gmail.com", "PPoo9232321713")
        viewModel.login(loginBody)
    }
}