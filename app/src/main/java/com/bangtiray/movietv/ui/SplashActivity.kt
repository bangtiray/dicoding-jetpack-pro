package com.bangtiray.movietv.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bangtiray.movietv.R

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    private val splashScreenTime: Long = 6000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, splashScreenTime)
    }
}