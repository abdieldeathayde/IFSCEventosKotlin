package com.example.projetointegradorabdiel

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetointegradorabdiel.databinding.ActivityInscricaoAlunoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class InscricaoAlunoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInscricaoAlunoBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInscricaoAlunoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        binding.button3.setOnClickListener {

            val matricula = binding.editTextText5.text.toString().trim()
            val nomeCivil = binding.editTextText6.text.toString().trim()
            val curso = binding.editTextText7.text.toString().trim()
            val email = binding.editTextText8.text.toString().trim()
            val telefone = binding.editTextText9.text.toString().trim()

            // Adicione este EditText no XML
            val senha = binding.editTextSenha.text.toString().trim()

            if (matricula.isEmpty() ||
                nomeCivil.isEmpty() ||
                curso.isEmpty() ||
                email.isEmpty() ||
                telefone.isEmpty() ||
                senha.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Preencha todos os campos.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                Toast.makeText(
                    this,
                    "E-mail inválido.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (senha.length < 6) {

                Toast.makeText(
                    this,
                    "A senha deve possuir pelo menos 6 caracteres.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, senha)
                .addOnSuccessListener {

                    val uid = auth.currentUser!!.uid

                    val usuario = hashMapOf(
                        "uid" to uid,
                        "matricula" to matricula,
                        "nome" to nomeCivil,
                        "curso" to curso,
                        "email" to email,
                        "telefone" to telefone,
                        "tipo" to "ALUNO"
                    )

                    firestore.collection("usuarios")
                        .document(uid)
                        .set(usuario)
                        .addOnSuccessListener {

                            Toast.makeText(
                                this,
                                "Cadastro realizado com sucesso!",
                                Toast.LENGTH_LONG
                            ).show()

                            startActivity(
                                Intent(
                                    this,
                                    SignInActivity::class.java
                                )
                            )

                            finish()
                        }
                        .addOnFailureListener { e ->

                            Toast.makeText(
                                this,
                                e.localizedMessage,
                                Toast.LENGTH_LONG
                            ).show()
                        }

                }
                .addOnFailureListener { e ->

                    Toast.makeText(
                        this,
                        e.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()

                }
        }
    }
}