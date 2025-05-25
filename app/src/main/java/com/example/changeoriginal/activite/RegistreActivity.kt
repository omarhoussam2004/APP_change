package com.example.changeoriginal.activite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.changeoriginal.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class RegistreActivity : AppCompatActivity() {

    private lateinit var fullName: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var confirmPassword: TextInputEditText
    private lateinit var btnRegister: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registre)

        fullName = findViewById(R.id.editFullName)
        email = findViewById(R.id.editEmail)
        password = findViewById(R.id.editPassword)
        confirmPassword = findViewById(R.id.editConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val name = fullName.text.toString().trim()
            val mail = email.text.toString().trim()
            val pass = password.text.toString().trim()
            val confirmPass = confirmPassword.text.toString().trim()

            when {
                name.isEmpty() -> fullName.error = "Veuillez entrer votre nom"
                mail.isEmpty() -> email.error = "Veuillez entrer votre email"
                pass.isEmpty() -> password.error = "Veuillez entrer un mot de passe"
                confirmPass.isEmpty() -> confirmPassword.error = "Veuillez confirmer le mot de passe"
                pass != confirmPass -> confirmPassword.error = "Les mots de passe ne correspondent pas"
                else -> {
                    Toast.makeText(this, "Compte créé avec succès !", Toast.LENGTH_SHORT).show()
                    // Lancer HomeActivity après inscription
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish() // ferme RegistreActivity pour ne pas revenir en arrière
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
