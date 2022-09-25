package com.example.spacevi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val userlist:ArrayList<User>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override  fun onCreateViewHolder(parent:ViewGroup, viewType:Int):MyAdapter.MyViewHolder {

        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.list_item,
            parent,false)
        return  MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder:MyAdapter.MyViewHolder, position: Int) {
        val user :User=userlist[position]
        holder.nombre.text=user.nombre
        holder.apellido.text=user.apellido
        holder.telefono.text=user.telefono
    }


    override fun getItemCount():Int{
        return userlist.size
    }

    public class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nombre:TextView=itemView.findViewById(R.id.tvnombre)
        val apellido:TextView=itemView.findViewById(R.id.tvapellido)
        val telefono:TextView=itemView.findViewById(R.id.tvtelefono)

    }


}