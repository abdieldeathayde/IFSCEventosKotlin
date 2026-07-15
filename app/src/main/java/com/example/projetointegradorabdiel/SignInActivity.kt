package com.example.projetointegradorabdiel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetointegradorabdiel.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import kotlin.jvm.java

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        // Ir para tela de cadastro
        binding.textView.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        // Ir para tela de resetar senha
        binding.textView3.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }

        // Botão de login
        binding.button.setOnClickListener {

            val email = binding.emailEt.text.toString().trim()
            val pass = binding.passET.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth
                    .signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Login OK → tela principal
                            startActivity(Intent(this, MainActivity::class.java))
                            finish() // impede voltar para login
                        } else {
                            Toast.makeText(
                                this,
                                task.exception?.message ?: "Erro ao autenticar",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

            } else {
                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(
                        this,
                        "Email e senha não podem estar vazios",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                firebaseAuth
                    .signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            // ✅ Login OK → ir para tela principal
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()

                        } else {
                            Toast.makeText(
                                this,
                                task.exception?.message ?: "Erro ao autenticar",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}