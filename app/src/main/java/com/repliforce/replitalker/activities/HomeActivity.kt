package com.repliforce.replitalker.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.repliforce.replitalker.R
import com.repliforce.replitalker.databinding.ActivityHomeBinding
import com.repliforce.replitalker.databinding.ActivityLoginBinding
import com.repliforce.replitalker.fragments.HomeFragment
import com.repliforce.replitalker.fragments.MyActivityFragment
import com.repliforce.replitalker.fragments.SearchFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val firebaseAuth = FirebaseAuth.getInstance()
    private var sectionsPagerAdapters: SectionPageAdapter? = null
    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val myActivityFragment = MyActivityFragment()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sectionsPagerAdapters = SectionPageAdapter(supportFragmentManager)
        binding.container.adapter = sectionsPagerAdapters
        binding.container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabs))
        binding.tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(binding.container))
        binding.tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.logo.setOnClickListener { view ->
            startActivity(ProfileActivity.newIntent(this))
        }
    }

    override fun onResume() {
        super.onResume()
        if(userId == null) {
            startActivity(LoginActivity.newIntent(this))
            finish()
        }
    }

    inner class SectionPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when(position) {
                0 -> homeFragment
                1 -> searchFragment
                else -> myActivityFragment
            }
        }

        override fun getCount() = 3
    }
    companion object {
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}