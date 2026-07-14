package com.example.projetointegradorabdiel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetointegradorabdiel.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        binding.buttonCadastrar.setOnClickListener {

            val nome = binding.nomeEt.text.toString().trim()
            val email = binding.emailEt.text.toString().trim()
            val senha = binding.passET.text.toString().trim()
            val confirmarSenha = binding.confirmPassEt.text.toString().trim()

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (senha != confirmarSenha) {
                Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, senha)
                .addOnSuccessListener {

                    val uid = auth.currentUser!!.uid

                    val usuario = hashMapOf(
                        "uid" to uid,
                        "nome" to nome,
                        "email" to email,
                        "tipo" to "ALUNO"
                    )

                    firestore.collection("usuarios")
                        .document(uid)
                        .set(usuario)
                        .addOnSuccessListener {

                            Toast.makeText(
                                this,
                                "Cadastro realizado com sucesso!",
                                Toast.LENGTH_SHORT
                            ).show()

                            startActivity(
                                Intent(this, SignInActivity::class.java)
                            )

                            finish()
                        }
                        .addOnFailureListener { e ->

                            Toast.makeText(
                                this,
                                e.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }

                }
                .addOnFailureListener { e ->

                    Toast.makeText(
                        this,
                        e.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
        }
    }
}