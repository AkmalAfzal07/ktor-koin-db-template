package com.example.koinstructure.ui.activity.splash

import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.koinstructure.R
import com.example.koinstructure.databinding.ActivitySplashBinding
import com.example.koinstructure.ui.activity.main.MainActivity

class Splash : AppCompatActivity() {

    private var binding: ActivitySplashBinding? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_splash)

        Handler(mainLooper).postDelayed({
            val intent = android.content.Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}