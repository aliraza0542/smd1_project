package com.stockmaster.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.stockmaster.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // F1 — Intent: SplashActivity → MainActivity with user info via Intent Extras
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("USER_NAME", "John Smith")
                putExtra("USER_EMAIL", "john@stockmaster.com")
                putExtra("USER_ROLE", "Store Manager")
                putExtra("STORE_ID", 1001)
            }
            startActivity(intent)
            finish()
        }, 2000)
    }
}
