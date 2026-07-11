package com.example.projetointegradorabdiel

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.example.projetointegradorabdiel.R.layout.activity_main

class MainActivity : ComponentActivity() {

    private lateinit var btnTodo: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(activity_main)

        btnTodo = findViewById(R.id.imageButton4)

        btnTodo.setOnClickListener {
            val intent = Intent(this, TodoActivity::class.java)
            startActivity(intent)
        }
    }
}