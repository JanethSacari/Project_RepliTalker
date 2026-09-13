package com.repliforce.replitalker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.repliforce.replitalker.databinding.ActivitySignupBinding
import com.repliforce.replitalker.util.DATA_USERS
import com.repliforce.replitalker.util.User

class SignupActivity : AppCompatActivity() {

    private val firebaseDB = FirebaseFirestore.getInstance()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseAuthListener = FirebaseAuth.AuthStateListener {
        val user = firebaseAuth.currentUser?.uid
        user?.let {
            startActivity(HomeActivity.newIntent(this))
            finish()
        }
    }

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTextChangeListener(binding.usernameET, binding.usernameTIL)
        setTextChangeListener(binding.emailET, binding.emailTIL)
        setTextChangeListener(binding.passwordET, binding.passwordTIL)

        binding.signupProgressLayout.setOnTouchListener { view, event -> true }
    }

    fun setTextChangeListener(et: EditText, til: TextInputLayout) {
        et.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                til.isErrorEnabled = false
            }
        })
    }

    fun onSignup(v: View) {
        var proceed = true
        if(binding.usernameET.text.isNullOrEmpty()) {
            binding.usernameTIL.error = "Username is required"
            binding.usernameTIL.isErrorEnabled = true
            proceed = false
        }
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
            binding.signupProgressLayout.visibility = View.VISIBLE
            firebaseAuth.createUserWithEmailAndPassword(
                binding.emailET.text.toString(),
                binding.passwordET.text.toString()
            ).addOnCompleteListener() {
                    task ->
                if (!task.isSuccessful) {
                    binding.signupProgressLayout.visibility = View.GONE
                    Toast.makeText(
                        this@SignupActivity,
                        "Signup Error: ${task.exception?.localizedMessage}",
                        Toast.LENGTH_SHORT).show()
                } else {
                    val email = binding.emailET.text.toString()
                    val name = binding.usernameET.text.toString()
                    val user = User(
                        email,
                        name,
                        "",
                        arrayListOf(),
                        arrayListOf()
                    )
                    firebaseDB.collection(DATA_USERS)
                        .document(firebaseAuth.uid!!).set(user)
                }
                binding.signupProgressLayout.visibility = View.GONE
            }
                .addOnFailureListener { e ->
                    e.printStackTrace()
                    binding.signupProgressLayout.visibility = View.GONE
                }
        }
    }

    fun goToLogin(v: View) {
        startActivity(LoginActivity.newIntent(this))
        finish()
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(firebaseAuthListener)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(firebaseAuthListener)
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, SignupActivity::class.java)
    }
}