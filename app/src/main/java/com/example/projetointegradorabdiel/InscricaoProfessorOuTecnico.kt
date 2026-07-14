package com.example.projetointegradorabdiel

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.RadioButton
import android.widget.RadioGroup

class InscricaoProfessorOuTecnico : AppCompatActivity() {

    private lateinit var btnSalvar: AppCompatButton

    private lateinit var etCargo: EditText
    private lateinit var etTelefone: EditText
    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private lateinit var etConfirmarSenha: EditText

    private lateinit var radioGroupTipo: RadioGroup
    private lateinit var radioProfessor: RadioButton
    private lateinit var radioTI: RadioButton

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    // Altere para "TI" caso queira cadastrar um profissional de TI


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscricao_professor_ou_tecnico)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        inicializarComponentes()

        btnSalvar.setOnClickListener {

            if (!validarCampos()) {
                return@setOnClickListener
            }

            cadastrarUsuario()
        }
    }

    private fun inicializarComponentes() {

        radioGroupTipo = findViewById(R.id.radioGroupTipo)
        radioProfessor = findViewById(R.id.radioProfessor)
        radioTI = findViewById(R.id.radioTI)

        btnSalvar = findViewById(R.id.button4)

        etCargo = findViewById(R.id.editTextText2)
        etTelefone = findViewById(R.id.editTextText4)
        etEmail = findViewById(R.id.editTextText3)
        etSenha = findViewById(R.id.editTextTextPassword3)
        etConfirmarSenha = findViewById(R.id.editTextTextPassword4)
    }

    private fun cadastrarUsuario() {

        val cargo = etCargo.text.toString().trim()
        val telefone = etTelefone.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val senha = etSenha.text.toString()

        btnSalvar.isEnabled = false

        val tipo = if (radioProfessor.isChecked) {
            "PROFESSOR"
        } else {
            "TI"
        }

        auth.createUserWithEmailAndPassword(email, senha)
            .addOnSuccessListener {

                val uid = auth.currentUser!!.uid

                val usuario = hashMapOf(
                    "uid" to uid,
                    "cargo" to cargo,
                    "telefone" to telefone,
                    "email" to email,
                    "tipo" to tipo,
                    "dataCadastro" to System.currentTimeMillis()
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

                        btnSalvar.isEnabled = true

                        Toast.makeText(
                            this,
                            "Erro ao salvar dados: ${e.localizedMessage}",
                            Toast.LENGTH_LONG
                        ).show()
                    }

            }
            .addOnFailureListener { e ->

                btnSalvar.isEnabled = true

                Toast.makeText(
                    this,
                    "Erro ao criar usuário: ${e.localizedMessage}",
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    private fun validarCampos(): Boolean {

        val cargo = etCargo.text.toString().trim()
        val telefone = etTelefone.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val senha = etSenha.text.toString()
        val confirmarSenha = etConfirmarSenha.text.toString()

        etCargo.error = null
        etTelefone.error = null
        etEmail.error = null
        etSenha.error = null
        etConfirmarSenha.error = null

        if (cargo.isEmpty()) {
            etCargo.error = "Informe o cargo"
            etCargo.requestFocus()
            return false
        }

        if (telefone.isEmpty()) {
            etTelefone.error = "Informe o telefone"
            etTelefone.requestFocus()
            return false
        }

        if (email.isEmpty()) {
            etEmail.error = "Informe o e-mail"
            etEmail.requestFocus()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "E-mail inválido"
            etEmail.requestFocus()
            return false
        }

        if (senha.length < 6) {
            etSenha.error = "A senha deve possuir pelo menos 6 caracteres"
            etSenha.requestFocus()
            return false
        }

        if (senha != confirmarSenha) {
            etConfirmarSenha.error = "As senhas não coincidem"
            etConfirmarSenha.requestFocus()
            return false
        }

        if (radioGroupTipo.checkedRadioButtonId == -1) {
            Toast.makeText(
                this,
                "Selecione Professor ou Profissional de TI",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        return true
    }
}