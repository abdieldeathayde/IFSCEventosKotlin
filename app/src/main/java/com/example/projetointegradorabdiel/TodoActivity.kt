package com.example.projetointegradorabdiel

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.projetointegradorabdiel.model.Subject
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoActivity : AppCompatActivity() {

    lateinit var recycler:
            RecyclerView

    lateinit var adapter: SubjectAdapter

    val materias= mutableListOf(

        Subject("Java", 12, 4),
        Subject("Kotlin",12,8),
        Subject("POO",12,7),
        Subject("Projeto Integrador",12,12)

    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_todo)

        recycler=findViewById(R.id.recyclerMaterias)

        recycler.layoutManager=GridLayoutManager(this,2)

        adapter= SubjectAdapter(materias)

        recycler.adapter=adapter

        findViewById<FloatingActionButton>(R.id.fabAdicionar).setOnClickListener {

            abrirDialog()

        }

    }

    private fun abrirDialog(){

        val view=layoutInflater.inflate(R.layout.dialog_add_subject,null)

        val nome=view.findViewById<EditText>(R.id.edtNome)

        val tarefas=view.findViewById<EditText>(R.id.edtTarefas)

        AlertDialog.Builder(this)

            .setTitle("Nova Matéria")

            .setView(view)

            .setPositiveButton("Salvar"){_,_->

                materias.add(

                    Subject(

                        nome.text.toString(),

                        tarefas.text.toString().toInt(),

                        0

                    )

                )

                adapter.notifyItemInserted(materias.size-1)

            }

            .setNegativeButton("Cancelar",null)

            .show()

    }

}