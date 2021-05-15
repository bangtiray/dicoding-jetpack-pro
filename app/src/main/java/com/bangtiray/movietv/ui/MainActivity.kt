package com.bangtiray.movietv.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bangtiray.movietv.R
import com.bangtiray.movietv.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNav =binding.bottomNavigation
        val navHostFragment =supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNav, navHostFragment.navController)
    }
}