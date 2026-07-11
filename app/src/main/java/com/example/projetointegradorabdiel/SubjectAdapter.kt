package com.example.projetointegradorabdiel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegradorabdiel.model.Subject;

public class SubjectAdapter(
    private val lista: MutableList<Subject>
): RecyclerView.Adapter<SubjectAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nome=itemView.findViewById<TextView>(R.id.txtNome)
        val progresso=itemView.findViewById<TextView>(R.id.txtProgresso)
        val barra=itemView.findViewById<ProgressBar>(R.id.progress)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subject,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount()=lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){

        val materia=lista[position]

        holder.nome.text=materia.nome

        holder.progresso.text=
            "${materia.concluidas} de ${materia.tarefas}"

        holder.barra.max=materia.tarefas

        holder.barra.progress=materia.concluidas
    }
}