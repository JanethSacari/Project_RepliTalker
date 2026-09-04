package com.repliforce.replitalker

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)}

    fun onLogin(v: View) {}
    fun goToSignup(v: View) {}
}