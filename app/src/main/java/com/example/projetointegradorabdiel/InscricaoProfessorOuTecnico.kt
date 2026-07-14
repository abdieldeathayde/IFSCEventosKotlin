package com.example.projetointegradorabdiel

import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class InscricaoProfessorOuTecnico : AppCompatActivity() {

    private lateinit var btnSalvar: AppCompatButton

    private lateinit var etCargo: EditText
    private lateinit var etTelefone: EditText
    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private lateinit var etConfirmarSenha: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscricao_professor_ou_tecnico)

        inicializarComponentes()

        btnSalvar.setOnClickListener {
            if (validarCampos()) {
                Toast.makeText(
                    this,
                    "Cadastro realizado com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()

                // TODO:
                // salvar no Firebase
                // ou enviar para API
            }
        }
    }

    private fun inicializarComponentes() {
        btnSalvar = findViewById(R.id.button4)

        etCargo = findViewById(R.id.editTextText2)
        etTelefone = findViewById(R.id.editTextText4)
        etEmail = findViewById(R.id.editTextText3)
        etSenha = findViewById(R.id.editTextTextPassword3)
        etConfirmarSenha = findViewById(R.id.editTextTextPassword4)
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
            etSenha.error = "A senha deve ter pelo menos 6 caracteres"
            etSenha.requestFocus()
            return false
        }

        if (senha != confirmarSenha) {
            etConfirmarSenha.error = "As senhas não coincidem"
            etConfirmarSenha.requestFocus()
            return false
        }

        return true
    }
}