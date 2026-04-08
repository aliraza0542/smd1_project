package com.stockmaster.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.stockmaster.R

class LoginActivity : AppCompatActivity() {

    private lateinit var tilUsername: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnSignIn: MaterialButton
    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        rootView = findViewById(R.id.root_login)
        tilUsername = findViewById(R.id.til_username)
        tilPassword = findViewById(R.id.til_password)
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnSignIn = findViewById(R.id.btn_sign_in)

        // Clear errors on text change
        etUsername.setOnFocusChangeListener { _, _ -> tilUsername.error = null }
        etPassword.setOnFocusChangeListener { _, _ -> tilPassword.error = null }

        btnSignIn.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (username.isEmpty()) {
            tilUsername.error = "Enter username"
            return
        }
        if (password.isEmpty()) {
            tilPassword.error = "Enter password"
            return
        }

        val role = when {
            username == "admin" && password == "admin123" -> "ADMIN"
            username == "staff" && password == "staff123" -> "STAFF"
            else -> null
        }

        if (role == null) {
            Snackbar.make(rootView, "Invalid credentials", Snackbar.LENGTH_SHORT).show()
            return
        }

        // F1 — Intent with extras
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("USER_NAME", username)
        intent.putExtra("USER_ROLE", role)
        intent.putExtra("USER_ID", 1)
        startActivity(intent)
        finish()
    }
}
