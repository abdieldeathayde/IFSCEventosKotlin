package com.example.projetointegradorabdiel

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnTodo: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTodo = findViewById(R.id.imageButton4)

        btnTodo.setOnClickListener {
            startActivity(Intent(this, TodoActivity::class.java))
        }
    }
}