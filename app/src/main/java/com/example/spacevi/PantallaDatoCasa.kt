package com.example.spacevi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class PantallaDatoCasa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_datoscasa)
        val btnGuardar: Button =findViewById(R.id.btnGuardar)

     btnGuardar.setOnClickListener{
           val campoNombre: TextView =findViewById(R.id.txtNombre)
           val campoApellido: TextView =findViewById(R.id.txtApellido)
           val campoTelefono: TextView =findViewById(R.id.txtTelefono)
         val campoDescripcion: TextView =findViewById(R.id.txtDescripcion)


         val nombre:String=campoNombre.text.toString()
           val apellido:String=campoApellido.text.toString()
           val telefono:String=campoTelefono.text.toString()
         val descripcion:String=campoDescripcion.text.toString()

         //  val telefono:Int=campoTelefono.text.toString().toInt()


           saveFirestore(nombre,apellido,telefono,descripcion)
//
         val lanzar= Intent(this,MainActivity::class.java)
         startActivity(lanzar)

       }
      val bundle=intent.extras
        val dato=bundle?.getString("Datos")
        val datoapellido=bundle?.getString("Apellido")
        val datoTelefono=bundle?.getString("Telefono")


        val envio=findViewById<TextView>(R.id.txtNombre)
        val envioapellido=findViewById<TextView>(R.id.txtApellido)
        val envioTelefono=findViewById<TextView>(R.id.txtTelefono)


        envio.text=""+dato.toString()
        envioapellido.text=""+datoapellido.toString()
        envioTelefono.text=""+datoTelefono.toString()




    }
   fun saveFirestore(nombre:String, apellido:String, telefono: String, descripcion: String){
        //, apellido:String, telefono: String
        val db= FirebaseFirestore.getInstance()
        val user:MutableMap<String,Any> =HashMap()
        user["nombre"]=nombre
       user["apellido"]=apellido
       user["telefono"]=telefono
       user["descripcion"]=descripcion



       db.collection("clientes")
            .add(user)
            .addOnSuccessListener{
                Toast.makeText(this@PantallaDatoCasa, "Registro Exitoso", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener{
                Toast.makeText(this@PantallaDatoCasa, "no hay exito", Toast.LENGTH_LONG).show()

            }
    }

}