package com.example.miaula.ui.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.miaula.MainActivity
import com.example.miaula.R
import com.example.miaula.api.MiAulaService
import com.example.miaula.databinding.FragmentSplashBinding
import com.example.miaula.sharedPreferences.SessionPreferences
import org.koin.android.ext.android.inject


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val sessionPreferences: SessionPreferences by inject()
    private val miAulaService: MiAulaService by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        initUI()
        return binding.root
    }

    private fun initUI(){

    }

    override fun onStart() {
        super.onStart()
        sessionPreferences.getUser()?.let { me ->
//            miAulaService.getUser(getUserUrl(me.id)).observe(this) { response ->
//                when (response) {
//                    is ApiSuccessResponse -> {
//                        sessionPreferences.setUser(response.body.user)
//                        onUserLoggedIn()
//                    }
//                    else -> onUserNotLoggedIn()
//                }
//            }
            if (me != null) {
                onUserLoggedIn()
            } else onUserNotLoggedIn()

        }
//        } ?: run {
//            appExecutors.networkIO().execute {
//                Thread.sleep(2000)
//                appExecutors.mainThread().execute {
//                    onUserNotLoggedIn()
//                }
//            }
//        }

    }

    private fun onUserNotLoggedIn(){
        findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
    }

    private fun onUserLoggedIn(){
        MainActivity.start(requireContext())
    }

}