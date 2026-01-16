package com.example.projetointegradorabdiel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetointegradorabdiel.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        // Voltar para login
        binding.textView.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        // 👉 Update Your Information → Inscrição do Aluno
        binding.textView4.setOnClickListener {
            startActivity(Intent(this, InscricaoAlunoActivity::class.java))
        }

        // Botão Sign Up
        binding.button.setOnClickListener {

            val email = binding.emailEt.text.toString().trim()
            val password = binding.passET.text.toString().trim()
            val confirmPassword = binding.confirmPassEt.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "As senhas não conferem", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, SignInActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            task.exception?.message ?: "Erro ao criar conta",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}
