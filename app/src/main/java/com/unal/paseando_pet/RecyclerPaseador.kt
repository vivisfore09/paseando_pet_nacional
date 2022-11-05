package com.unal.paseando_pet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
        holder.telefono.text=paseador.telefono
        holder.imagen.setImageResource(paseador.foto)

    }

    override fun getItemCount(): Int {
        return listaPaseadores.size
    }
}