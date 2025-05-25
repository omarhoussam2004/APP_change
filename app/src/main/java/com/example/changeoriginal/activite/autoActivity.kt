package com.example.changeoriginal.activite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.changeoriginal.R

class autoActivity : AppCompatActivity() {

    private lateinit var cree: Button
    private lateinit var btnConnexion: Button
    private lateinit var editEmail: TextInputEditText
    private lateinit var editPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auto)

        cree = findViewById(R.id.crée)
        btnConnexion = findViewById(R.id.btnConnexion)
        editEmail = findViewById(R.id.editemail)
        editPassword = findViewById(R.id.editPassword)

        cree.setOnClickListener {
            val intent = Intent(this, RegistreActivity::class.java)
            startActivity(intent)
        }


        btnConnexion.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()

            when {
                email.isEmpty() -> {
                    editEmail.error = "Veuillez entrer votre email"
                }
                password.isEmpty() -> {
                    editPassword.error = "Veuillez entrer votre mot de passe"
                }
                else -> {
                    // TODO : Ajouter authentification réelle
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish() // Empêche retour à la page login
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
