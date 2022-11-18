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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.unal.paseando_pet.databinding.ActivityLoginBinding
import com.unal.paseando_pet.databinding.ActivityResgistrarBinding
import kotlinx.coroutines.launch


class RegistrarActivity : AppCompatActivity() {

    lateinit var binding: ActivityResgistrarBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var  firestoreBd : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResgistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = Firebase.auth
        firestoreBd = FirebaseFirestore.getInstance()
        binding.registrarLocal.setOnClickListener{
            //guardarUsuario()
            registrarFirebase()
        }

    }

    fun guardarUsuario(){

        val nombre:String = binding.nombre.text.toString()
        val apellido:String = binding.apellidos.text.toString()
        val correo:String= binding.correo.text.toString()
        val telefono:String = binding.telefono.text.toString()
        val direccion: String= binding.direccion.text.toString()
        val password:String = binding.password.text.toString()
        val genero:Int = binding.genero.id
        val gen:String

        if(genero==0){
            gen = "Mujer"
        }else{
            gen= "Hombre"
        }
        var usuario = Usuario_Entidad(correo,nombre,apellido,telefono,direccion,gen,password)
        println("-------->>>>>> ${usuario.email} -- ${usuario.nombre}")
        val room = Room.databaseBuilder(this, bdUsuarios::class.java,"prueba123").build()

        lifecycleScope.launch {
         var usuRes=room.daoUsuario().agregarUsuario(usuario)
            var lista= room.daoUsuario().obtenerUsuarios()
            for (item in lista){
                println("-------->>>>>> ${item.email}")
            }
        }

       /*var pref=getSharedPreferences(correo,Context.MODE_PRIVATE)
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
        editar.commit()*/
        Toast.makeText(this,"Usuario registrado exitosamente", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun registrarFirebase(){
        val correo:String= binding.correo.text.toString()
        val password:String = binding.password.text.toString()
        val nombre:String = binding.nombre.text.toString()
        val apellido:String = binding.apellidos.text.toString()
        val telefono:String = binding.telefono.text.toString()
        val direccion: String= binding.direccion.text.toString()
        val genero:Int = binding.genero.id
        val gen:String
        if(genero==0){
            gen = "Mujer"
        }else{
            gen= "Hombre"
        }
        var id:String


        if(password.isEmpty() || correo.isEmpty() || nombre.isEmpty() || apellido.isEmpty()
            ||telefono.isEmpty() || direccion.isEmpty()) {
            Toast.makeText(this, "Complete los datos.", Toast.LENGTH_LONG).show()
        }else{
            firebaseAuth.createUserWithEmailAndPassword(correo,password).addOnCompleteListener(this){
                task ->
                if(task.isSuccessful){
                    id= firebaseAuth.currentUser?.uid.toString()
                    val data = hashMapOf<String, String>(
                        "nombre" to nombre,
                        "apellido" to apellido,
                        "contacto" to telefono,
                        "dirreccion" to direccion,
                        "genero" to gen
                    )
                    firestoreBd.collection("Usuarios").document(id).set(data).addOnSuccessListener {
                        task ->
                        Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                    }.addOnFailureListener{
                        error ->
                        Toast.makeText(this, "Error al registrarse $error", Toast.LENGTH_LONG).show()
                    }

                }else{
                    Toast.makeText(this, "Error al registrarse.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}