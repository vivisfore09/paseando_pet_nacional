package com.unal.paseando_pet

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import com.unal.paseando_pet.databinding.ActivityLoginBinding

class LoginActivity : Activity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ingresar.setOnClickListener { validar()}

        binding.registrar.setOnClickListener{ enviarRegistrar()}
    }

    fun validar(){

        val correo:String= binding.email.text.toString()
        val password:String= binding.pass.text.toString()

        var pref=getSharedPreferences(correo,Context.MODE_PRIVATE)

        var email_bd=pref.getString("email","")
        var pass_bd=pref.getString("password","")
        var nombre_bd=pref.getString("nombre","")
        var apel_bd=pref.getString("apellido","")
        if(correo.isEmpty()){
            binding.email.setHint("Ingrese su correo")
            binding.email.setHintTextColor(Color.RED)
            Toast.makeText(this,"Ingrese su correo.",Toast.LENGTH_LONG).show()
        }else if(password.isEmpty()){
            binding.pass.setHint("Ingrese su contraseña")
            binding.pass.setHintTextColor(Color.RED)
            Toast.makeText(this,"Ingrese su contraseña.",Toast.LENGTH_LONG).show()
        }else if(correo==email_bd)
                if(password==pass_bd){
                    //Toast.makeText(this,"Bienvenido........$nombre_bd $apel_bd",Toast.LENGTH_LONG).show()
                    var intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("email",email_bd)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Contraseña incorrecta.",Toast.LENGTH_LONG).show()
                }
        else{
            Toast.makeText(this,"Usuario no registrado.",Toast.LENGTH_LONG).show()
        }
    }


    fun enviarRegistrar(){
        startActivity(Intent(this, RegistrarActivity::class.java))
    }
}