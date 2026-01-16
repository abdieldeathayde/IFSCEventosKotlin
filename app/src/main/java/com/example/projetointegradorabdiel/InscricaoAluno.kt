package com.example.projetointegradorabdiel

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetointegradorabdiel.databinding.ActivityInscricaoAlunoBinding

class InscricaoAlunoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInscricaoAlunoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        binding = ActivityInscricaoAlunoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Clique no botão "Entrar"
        binding.button3.setOnClickListener {

            val matricula = binding.editTextText5.text.toString().trim()
            val nomeCivil = binding.editTextText6.text.toString().trim()
            val curso = binding.editTextText7.text.toString().trim()
            val email = binding.editTextText8.text.toString().trim()
            val telefone = binding.editTextText9.text.toString().trim()

            // Validação básica
            if (
                matricula.isEmpty() ||
                nomeCivil.isEmpty() ||
                curso.isEmpty() ||
                email.isEmpty() ||
                telefone.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Sucesso (aqui você pode integrar com API ou Firebase)
            Toast.makeText(
                this,
                "Inscrição realizada com sucesso!",
                Toast.LENGTH_LONG
            ).show()

            // Exemplo: finalizar a tela
            // finish()
        }
    }
}
