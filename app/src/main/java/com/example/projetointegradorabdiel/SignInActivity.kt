package com.example.projetointegradorabdiel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetointegradorabdiel.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Esqueci minha senha
        binding.textView3.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }

        // Ir para cadastro
        binding.textView.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        // Botão Entrar
        binding.button.setOnClickListener {

            val email = binding.emailEt.text.toString().trim()
            val senha = binding.passET.text.toString().trim()

            if (email.isEmpty()) {
                binding.emailEt.error = "Informe o e-mail"
                binding.emailEt.requestFocus()
                return@setOnClickListener
            }

            if (senha.isEmpty()) {
                binding.passET.error = "Informe a senha"
                binding.passET.requestFocus()
                return@setOnClickListener
            }

            firebaseAuth.signInWithEmailAndPassword(email, senha)
                .addOnSuccessListener {

                    val uid = firebaseAuth.currentUser?.uid

                    if (uid == null) {
                        Toast.makeText(
                            this,
                            "Erro ao identificar o usuário.",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@addOnSuccessListener
                    }

                    firestore.collection("usuarios")
                        .document(uid)
                        .get()
                        .addOnSuccessListener { document ->

                            if (!document.exists()) {

                                Toast.makeText(
                                    this,
                                    "Perfil do usuário não encontrado.",
                                    Toast.LENGTH_LONG
                                ).show()

                                firebaseAuth.signOut()

                                return@addOnSuccessListener
                            }

                            val tipo = document.getString("tipo")

                            when (tipo) {

                                "PROFESSOR", "TI" -> {

                                    startActivity(
                                        Intent(
                                            this,
                                            AdminDashboardActivity::class.java
                                        )
                                    )

                                    finish()
                                }

                                "ALUNO" -> {

                                    startActivity(
                                        Intent(
                                            this,
                                            MainActivity::class.java
                                        )
                                    )

                                    finish()
                                }

                                else -> {

                                    Toast.makeText(
                                        this,
                                        "Tipo de usuário inválido.",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    firebaseAuth.signOut()
                                }
                            }

                        }
                        .addOnFailureListener {

                            Toast.makeText(
                                this,
                                "Erro ao carregar perfil.",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                }
                .addOnFailureListener { e ->

                    Toast.makeText(
                        this,
                        e.localizedMessage ?: "Erro ao fazer login.",
                        Toast.LENGTH_LONG
                    ).show()

                }
        }
    }
}