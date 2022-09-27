package com.example.spacevi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class PantallaDatosarrendador : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_datosarrendador)


        val btnSiguienteDatosCasa: Button =findViewById(R.id.btnSiguienteDatosCasa)
        val txtNombre=findViewById<EditText>(R.id.txtNombre)
        val txApellido=findViewById<EditText>(R.id.txtApellido)
        val txtTelefono=findViewById<EditText>(R.id.txtTelefono)



        btnSiguienteDatosCasa.setOnClickListener {

            val lanzar= Intent(this,PantallaDatoCasa::class.java)
            lanzar.putExtra("Datos",txtNombre.text.toString())
            lanzar.putExtra("Apellido",txApellido.text.toString())
            lanzar.putExtra("Telefono",txtTelefono.text.toString())


            startActivity(lanzar)



        }

    }


}