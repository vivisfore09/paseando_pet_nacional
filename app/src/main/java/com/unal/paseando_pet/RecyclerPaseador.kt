package com.unal.paseando_pet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerPaseador(var listaPaseadores: MutableList<Paseador>):RecyclerView.Adapter<RecyclerPaseador.MiHolder>() {

    inner class MiHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
          lateinit var nombres:TextView
          lateinit var ciudad:TextView
          lateinit var telefono:TextView
          lateinit var imagen: ImageView

          init {
              nombres = itemView.findViewById(R.id.nombreP)
              ciudad = itemView.findViewById(R.id.ciudadP)
              telefono = itemView.findViewById(R.id.telefonoP)
              imagen = itemView.findViewById(R.id.foto)

              itemView.setOnClickListener{

                  Toast.makeText(itemView.context, "${nombres.text.toString()}", Toast.LENGTH_LONG).show()
              }
          }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiHolder {

        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_paseador,parent,false)
        return MiHolder(itemView)
    }

    override fun onBindViewHolder(holder: MiHolder, position: Int) {

        var paseador = listaPaseadores[position]
        holder.nombres.text=paseador.nombre
        holder.ciudad.text=paseador.ciudad
        holder.telefono.text=paseador.contacto
        Glide.with(holder.itemView)
            .load(paseador.foto)
            .into(holder.imagen);


    }

    override fun getItemCount(): Int {
        return listaPaseadores.size
    }
}