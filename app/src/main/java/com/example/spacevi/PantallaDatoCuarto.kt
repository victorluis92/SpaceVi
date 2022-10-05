package com.example.spacevi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class PantallaDatoCuarto : AppCompatActivity() {
    val datos  = arrayOf("Habitacion privada","Habitacion compartida")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_datoscuarto)
        val btnGuardar: Button =findViewById(R.id.btnGuardar)
        val miSpiner: Spinner =findViewById(R.id.spinner)


        val tvRespuesta:TextView=findViewById(R.id.tvCuarto)
        val adaptador=ArrayAdapter(this,
            android.R.layout.simple_spinner_item,datos)

        adaptador.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)

        miSpiner.adapter=adaptador
      //  boton.setOnClickListener{
         //   tvRespuesta.text=campoCuarto.selectedItem.toString()
       // }

        miSpiner.onItemSelectedListener=
            object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?,
                                            view: View?,
                                            position: Int,
                                            id: Long) {
                    tvRespuesta.text=
                        miSpiner.getItemAtPosition(position).toString()

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                //No hacer nada
                }
            }


        btnGuardar.setOnClickListener{
           val campoNombre: TextView =findViewById(R.id.txtNombre)
           val campoApellido: TextView =findViewById(R.id.txtApellido)
           val campoTelefono: TextView =findViewById(R.id.txtTelefono)
         val campoDescripcion: TextView =findViewById(R.id.txtDescripcion)
            val campoCuarto: TextView =findViewById(R.id.tvCuarto)


            val nombre:String=campoNombre.text.toString()
           val apellido:String=campoApellido.text.toString()
           val telefono:String=campoTelefono.text.toString()
         val descripcion:String=campoDescripcion.text.toString()
            val cuarto:String=campoCuarto.text.toString()

            // tvRespuesta.text=campoCuarto.selectedItem.toString()


            //  val telefono:Int=campoTelefono.text.toString().toInt()

            saveFirestore(nombre,apellido,telefono,descripcion,cuarto)
//,tvRespuesta
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


   fun saveFirestore(nombre:String, apellido:String, telefono: String, descripcion: String, cuarto: String)
   {
        //, apellido:String, telefono: String , tvRespuesta: TextView
        val db= FirebaseFirestore.getInstance()
        val user:MutableMap<String,Any> =HashMap()
        user["nombre"]=nombre
       user["apellido"]=apellido
       user["telefono"]=telefono
       user["descripcion"]=descripcion
      user["cuarto"]=cuarto




       db.collection("clientes")
            .add(user)
            .addOnSuccessListener{
                Toast.makeText(this@PantallaDatoCuarto, "Registro Exitoso", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener{
                Toast.makeText(this@PantallaDatoCuarto, "no hay exito", Toast.LENGTH_LONG).show()

            }
    }

}