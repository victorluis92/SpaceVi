package com.example.spacevi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnQuieroDarARentar:Button=findViewById(R.id.btnQuieroDarARentar)
        btnQuieroDarARentar.setOnClickListener{
            val lanzar=Intent(this,PantallaDatosarrendador::class.java)
            startActivity(lanzar)
        }


    }
}