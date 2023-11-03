package com.example.miaula.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.example.miaula.R
import com.example.miaula.databinding.ActivityWelcomeBinding


class WelcomeActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(this)
    }

    companion object {
        val DEEP_LINK_ACTION = "android.intent.action.VIEW"
        val EXTRA_DIRECTION = "direction"
        val EXTRA_DIRECTION_LOGIN = "login"
        fun start(context: Context) {
            val intent = Intent(context, WelcomeActivity::class.java)
                .apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                }
            context.startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        navController.navigate(R.id.welcomeFragment)
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {

    }

}