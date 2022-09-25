package com.example.spacevi



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class MainActivity : AppCompatActivity() {
    private lateinit var  recyclerView: RecyclerView
    private lateinit var  userArrayList: ArrayList<User>
    private lateinit var  myAdapter: MyAdapter
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnQuieroDarARentar: Button =findViewById(R.id.btnQuieroDarARentar)
        btnQuieroDarARentar.setOnClickListener{
            val lanzar= Intent(this,PantallaDatosarrendador::class.java)
            startActivity(lanzar)
        }


     
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        userArrayList= arrayListOf()
        myAdapter=MyAdapter(userArrayList)
        recyclerView.adapter=myAdapter
        EventChangeListener()




    }


    private fun EventChangeListener() {
        db=FirebaseFirestore.getInstance()
        db.collection("clientes").orderBy("nombre",Query.Direction.ASCENDING).
        addSnapshotListener(object :EventListener<QuerySnapshot>{
            override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
            ) {
             if (error !=null){
                 Log.e("error Firestore",error.message.toString())
             return
             }
                for (dc:DocumentChange in value?.documentChanges!!){
                    if (dc.type==DocumentChange.Type.ADDED){
                        userArrayList.add(dc.document.toObject(User::class.java))
                    }
                }
                myAdapter.notifyDataSetChanged()
            }

        })

    }


}