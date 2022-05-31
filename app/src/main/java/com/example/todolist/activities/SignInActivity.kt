package com.example.todolist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.todolist.R
import com.example.todolist.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    var binding: ActivitySignInBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpActionBar()
        binding?.btnSignIn?.setOnClickListener {
            signInRegisteredUser()
        }
    }
    private fun signInRegisteredUser(){
        val email = binding?.etEmail?.text.toString().trim { it <= ' ' }
        val password = binding?.etPassword?.text.toString().trim { it <= ' ' }
        if (validateForm(email, password)){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    private fun validateForm(email: String, password: String): Boolean{
        return when{
            TextUtils.isEmpty(email) -> {
                Toast.makeText(this, "Please Enter Your Email Address", Toast.LENGTH_SHORT).show()
                false
            }
            TextUtils.isEmpty(password) -> {
                Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show()
                false
            }else -> {
                true
            }
        }
    }
    private fun setUpActionBar(){
        setSupportActionBar(binding?.toolbarSignInActivity)
        var actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        }
        binding?.toolbarSignInActivity?.setNavigationOnClickListener { onBackPressed() }
    }
}