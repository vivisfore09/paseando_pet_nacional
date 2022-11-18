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
import com.unal.paseando_pet.databinding.ActivityRecuperarBinding
import kotlinx.coroutines.launch

class RecuperarActivity : AppCompatActivity() {

    lateinit var binding: ActivityRecuperarBinding
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecuperarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth

        binding.Enviar.setOnClickListener {
            var correo = binding.envioemail.text.toString()
            enviarCorreo(correo)
        }

    }

    fun enviarCorreo(correo:String){
        firebaseAuth.sendPasswordResetEmail(correo).addOnCompleteListener(){
            task->
            if(task.isSuccessful){
                Toast.makeText(this, "Se ha enviado un correo para recuperar contraseña.", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }else{
                Toast.makeText(this, "Error al enviar el correo", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }

    }
}