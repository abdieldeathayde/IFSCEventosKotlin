package com.example.projetointegradorabdiel // 🔴 ajuste para o seu pacote real

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class InscricaoProfessorOuTecnico : AppCompatActivity() {

    private lateinit var btnSalvar: AppCompatButton
    private lateinit var etCargo: EditText
    private lateinit var etTelefone: EditText
    private lateinit var imageLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscricao_professor_ou_tecnico)

        // 🔗 Ligando os componentes do XML
        btnSalvar = findViewById(R.id.button4)
        etCargo = findViewById(R.id.editTextText2)
        etTelefone = findViewById(R.id.editTextText4)
        imageLogo = findViewById(R.id.imageView4)

        // 🟣 Ação do botão Salvar
        btnSalvar.setOnClickListener {
            val cargo = etCargo.text.toString().trim()
            val telefone = etTelefone.text.toString().trim()

            if (cargo.isEmpty()) {
                etCargo.error = "Informe o cargo"
                return@setOnClickListener
            }

            if (telefone.isEmpty()) {
                etTelefone.error = "Informe o telefone"
                return@setOnClickListener
            }

            // Aqui você pode salvar no Firebase, banco local ou enviar para API
            // Exemplo:
            // salvarDadosProfessorOuTecnico(cargo, telefone)
        }
    }
}
