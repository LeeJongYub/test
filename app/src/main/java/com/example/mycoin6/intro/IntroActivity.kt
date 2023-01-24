package com.example.mycoin6.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.example.mycoin6.main.MainActivity
import com.example.mycoin6.databinding.ActivityIntroBinding
import com.example.mycoin6.datastore.IntroViewModel

class IntroActivity : AppCompatActivity() {

    private val viewModel : IntroViewModel by viewModels()

    private lateinit var binding : ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.checkFirstOrAlreadyUser()

        viewModel.userLiveData.observe(this, Observer {

            // it == true (이미 접속한 유저)
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                binding.fragmentContainerView.visibility = View.INVISIBLE
                // it 이 false (처음 접속하는 유저)
            } else {
                binding.coinAnimation.visibility = View.INVISIBLE
                binding.fragmentContainerView.visibility = View.VISIBLE
            }
        })
    }
}