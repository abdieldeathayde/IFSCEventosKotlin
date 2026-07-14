package com.example.projetointegradorabdiel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AdminDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_admin_dashboard)

        findViewById<Button>(R.id.btnNovoCurso).setOnClickListener {

            startActivity(
                Intent(this, NovoCursoActivity::class.java)
            )

        }

        findViewById<Button>(R.id.btnNovaNoticia).setOnClickListener {

            startActivity(
                Intent(this, NovaNoticiaActivity::class.java)
            )

        }

    }

}