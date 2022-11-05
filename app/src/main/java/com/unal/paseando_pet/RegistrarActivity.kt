package com.unal.paseando_pet

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.unal.paseando_pet.databinding.ActivityLoginBinding
import com.unal.paseando_pet.databinding.ActivityResgistrarBinding


class RegistrarActivity : Activity() {

    lateinit var binding: ActivityResgistrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResgistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registrarLocal.setOnClickListener{guardarUsuario()}

    }

    fun guardarUsuario(){

        val nombre:String = binding.nombre.text.toString()
        val apellido:String = binding.apellidos.text.toString()
        val correo:String= binding.correo.text.toString()
        val telefono:String = binding.telefono.text.toString()
        val direccion: String= binding.direccion.text.toString()
        val password:String = binding.password.text.toString()
        val genero:Int = binding.genero.id


        var pref=getSharedPreferences(correo,Context.MODE_PRIVATE)
        var editar= pref.edit()
        editar.putString("email",correo)
        editar.putString("nombre", nombre)
        editar.putString("apellidos",apellido)
        editar.putString("telefono",telefono)
        editar.putString("difreccion",direccion)
        editar.putString("password", password)
        if(genero==0){
            editar.putString("genero", "Mujer")
        }else{
            editar.putString("genero", "Hombre")
        }
        editar.commit()
        Toast.makeText(this,"Usuario registrado exitosamente", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, LoginActivity::class.java))
    }

}