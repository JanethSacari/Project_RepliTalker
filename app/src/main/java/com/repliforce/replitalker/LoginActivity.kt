package com.repliforce.replitalker

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.repliforce.replitalker.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseAuthListener = FirebaseAuth.AuthStateListener {
        val user = firebaseAuth.currentUser?.uid
        user?.let {}
    }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onLogin(v: View) {
        var proceed = true
        if (binding.emailET.text.isNullOrEmpty()) {
            binding.emailTIL.error = "Email is required"
            binding.emailTIL.isErrorEnabled = true
            proceed = false
        }
        if (binding.passwordET.text.isNullOrEmpty()) {
            binding.passwordTIL.error = "Password is required"
            binding.passwordTIL.isErrorEnabled = true
            proceed = false
        }
        if (proceed) {
            binding.loginProgressLayout.visibility = View.VISIBLE
            firebaseAuth.signInWithEmailAndPassword(
                binding.emailET.text.toString(),
                binding.passwordET.text.toString()
            ).addOnCompleteListener() {
                task ->
                if (!task.isSuccessful) {
                    binding.loginProgressLayout.visibility = View.GONE
                    Toast.makeText(
                        this@LoginActivity,
                        "Login Error: ${task.exception?.localizedMessage}",
                        Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
                binding.loginProgressLayout.visibility = View.GONE
            }
        }
    }
    fun goToSignup(v: View) {}
}