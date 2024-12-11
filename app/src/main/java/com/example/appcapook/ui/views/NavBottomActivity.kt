package com.example.appcapook.ui.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.appcapook.R
import com.example.appcapook.databinding.ActivityNavBottomBinding

class NavBottomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavBottomBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()
        navController.navigate(R.id.menu_home)
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController( binding.bottomNavigation, navController)
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    navController.navigate(R.id.menu_home)
                    true
                }
                R.id.menu_search -> {
                    navController.navigate(R.id.menu_search) // Certifique-se de ter um SearchFragment
                    true
                }
                R.id.menu_bookshelves -> {
                    navController.navigate(R.id.menu_bookshelves) // Certifique-se de ter um BookshelvesFragment
                    true
                }
                R.id.menu_profile -> {
                    navController.navigate(R.id.menu_profile) // Certifique-se de ter um ProfileFragment
                    true
                }
                else -> false
            }
        }

    }
}