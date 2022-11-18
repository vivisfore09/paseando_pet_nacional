package com.unal.paseando_pet

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.unal.paseando_pet.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseAuth: FirebaseAuth


    var email: String? = null
    var pass: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth

        binding.ingresar.setOnClickListener {
            //validarMemoria()
            /*val room = Room.databaseBuilder(this, bdUsuarios::class.java, "prueba123").build()
            val correo:String= binding.email.text.toString()
            lifecycleScope.launch{
                var usuRes= room.daoUsuario().buscarUsuario(correo)
                email=usuRes.email
                pass=usuRes.password
                println("-------->>>>>>Dentro  ${email} -- ${pass}")
                validarRoom(email!!, pass!!)
            }*/
            validarFirebase()
            }

        binding.registrar.setOnClickListener{ enviarRegistrar()}
        binding.recuperar.setOnClickListener { enviarRecuperar() }
    }

    fun validarMemoria(){

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

    fun validarRoom(email_bd:String,pass_bd:String){

        val correo:String= binding.email.text.toString()
        val password:String= binding.pass.text.toString()

        println("-------->>>>>> Afuera ${email_bd} -- ${pass_bd}")
        if(correo.isEmpty()){
            binding.email.setHint("Ingrese su correo")
            binding.email.setHintTextColor(Color.RED)
            Toast.makeText(this,"Ingrese su correo.",Toast.LENGTH_LONG).show()
        }else if(password.isEmpty()){
            binding.pass.setHint("Ingrese su contraseña")
            binding.pass.setHintTextColor(Color.RED)
            Toast.makeText(this,"Ingrese su contraseña.",Toast.LENGTH_LONG).show()
        }else if(correo==email_bd) {
            if (password == pass_bd) {
                //Toast.makeText(this,"Bienvenido........$nombre_bd $apel_bd",Toast.LENGTH_LONG).show()
                var intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("email", email_bd)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Contraseña incorrecta.", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this,"Usuario no registrado.",Toast.LENGTH_LONG).show()
        }
    }


    fun validarFirebase(){
        val correo:String= binding.email.text.toString()
        val password:String= binding.pass.text.toString()

        if(correo.isEmpty()){
            binding.email.setHint("Ingrese su correo")
            binding.email.setHintTextColor(Color.RED)
            Toast.makeText(this,"Ingrese su correo.",Toast.LENGTH_LONG).show()
        }else if(password.isEmpty()) {
            binding.pass.setHint("Ingrese su contraseña")
            binding.pass.setHintTextColor(Color.RED)
            Toast.makeText(this, "Ingrese su contraseña.", Toast.LENGTH_LONG).show()
        }else{
            firebaseAuth.signInWithEmailAndPassword(correo,password).addOnCompleteListener(this){
                task ->
                if(task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    if (user != null) {
                        Toast.makeText(this,"Bienvenido ${user.email}",Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
                }else{
                    Toast.makeText(this,"Datos Incorrectos",Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    fun enviarRegistrar(){
        startActivity(Intent(this, RegistrarActivity::class.java))
    }

    fun enviarRecuperar(){
        startActivity(Intent(this, RecuperarActivity::class.java))
    }
}