package com.libaoming.kotlinproject.sample.navigation

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.libaoming.kotlinproject.BaseActivity
import com.libaoming.kotlinproject.R
import com.libaoming.kotlinproject.databinding.ActivityNavigationBinding
import com.libaoming.kotlinproject.inflate

class NavigationActivity : BaseActivity() {
    private val binding by inflate<ActivityNavigationBinding>(true)

    override fun initView() {
        val bottomNavigation:BottomNavigationView = binding.btNavTab
        val navHostFragment:NavHostFragment? = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment?

        val navController:NavController? = navHostFragment?.navController

        if (navController != null) {
            // 绑定BottomNavigationView
            NavigationUI.setupWithNavController(bottomNavigation, navController)
        }
    }

    override fun initData() {

    }

    override fun onResume() {
        super.onResume()
    }
}