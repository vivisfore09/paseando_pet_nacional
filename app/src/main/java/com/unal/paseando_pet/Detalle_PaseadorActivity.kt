package com.unal.paseando_pet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.unal.paseando_pet.databinding.ActivityDetallePaseadorBinding
import com.unal.paseando_pet.databinding.ActivityRecuperarBinding

class Detalle_PaseadorActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetallePaseadorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetallePaseadorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var mapa = Intent(this, MapaActivity::class.java)

        var nombre= intent.getStringExtra("nomPas")
        var ciudad= intent.getStringExtra("ciudPas")
        var contacto= intent.getStringExtra("contactoPas")
        var perfil= intent.getStringExtra("perfilPas")
        var foto= intent.getStringExtra("fotoPas")

        binding.nombrePas.text=nombre
        binding.ciudadPas.text=ciudad
        binding.telefonoPas.text=contacto
        binding.perfilPas.text=perfil
        Glide.with(this)
            .load(foto)
            .into(binding.fotoPas);

        binding.ubicacion.setOnClickListener{
            mapa.putExtra("nombre",nombre)
            startActivity(mapa)
        }
    }
}