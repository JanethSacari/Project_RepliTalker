package com.repliforce.replitalker.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.repliforce.replitalker.R
import com.repliforce.replitalker.databinding.ActivityHomeBinding
import com.repliforce.replitalker.databinding.ActivityProfileBinding
import com.repliforce.replitalker.util.DATA_USERS
import com.repliforce.replitalker.util.DATA_USER_EMAIL
import com.repliforce.replitalker.util.DATA_USER_USERNAME
import com.repliforce.replitalker.util.User

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDB = FirebaseFirestore.getInstance()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (userId == null) {
            finish()
        }
        binding.profileProgressLayout.setOnTouchListener { v, event -> true }
        populateInfo()
    }

    fun populateInfo() {
        binding.profileProgressLayout.visibility = View.VISIBLE
        firebaseDB.collection(DATA_USERS).document(userId!!).get()
            .addOnSuccessListener { documentSnapshot ->
                val user = documentSnapshot.toObject(User::class.java)
                binding.usernameET.setText(user?.username, TextView.BufferType.EDITABLE)
                binding.emailET.setText(user?.email, TextView.BufferType.EDITABLE)
                binding.profileProgressLayout.visibility = View.GONE
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
                finish()
            }
    }
    fun onApply(v: View) {
        binding.profileProgressLayout.visibility = View.VISIBLE
        val username = binding.usernameET.text.toString()
        val email = binding.emailET.text.toString()
        val map = HashMap<String, Any>()
        map[DATA_USER_USERNAME] = username
        map[DATA_USER_EMAIL] = email
        firebaseDB.collection(DATA_USERS).document(userId!!)
            .update(map).addOnSuccessListener {
                Toast.makeText(this, "Update successful", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
                Toast.makeText(this, "Update failed. Please try again.", Toast.LENGTH_SHORT)
                binding.profileProgressLayout.visibility = View.GONE
            }
    }
    fun onSignout(v: View) {
        firebaseAuth.signOut()
        finish()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, ProfileActivity::class.java)
    }
}