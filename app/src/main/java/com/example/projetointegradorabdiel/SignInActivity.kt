package com.example.projetointegradorabdiel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetointegradorabdiel.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        // --- INÍCIO DA CORREÇÃO ---
        // MOVA O LISTENER DE RESETAR SENHA PARA CÁ

        // Listener para ir para a tela de resetar senha
        binding.textView3.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
        // --- FIM DA CORREÇÃO ---

        // Listener para ir para a tela de cadastro
        binding.textView.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        // Listener para o botão de login
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString().trim()
            val pass = binding.passET.text.toString().trim()

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
                        // Login OK → ir para tela principal
                        startActivity(Intent(this, MainActivity::class.java))
                        finish() // Fecha a tela de login para o usuário não voltar para ela
                    } else {
                        Toast.makeText(
                            this,
                            task.exception?.message ?: "Erro ao autenticar",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    // O listener de resetar senha foi removido daqui.
                }
        }
    }
}
