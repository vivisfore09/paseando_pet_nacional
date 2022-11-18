package com.unal.paseando_pet

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.unal.paseando_pet.databinding.ActivityRegistrarMascotaBinding
import java.io.File


class RegistrarMascota: AppCompatActivity() {

    lateinit var binding: ActivityRegistrarMascotaBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarMascotaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = Firebase.auth
        val email= firebaseAuth.currentUser?.email.toString()

        //val email=intent.getStringExtra("email")

        Toast.makeText(this,"Bienvenido........$email", Toast.LENGTH_LONG).show()

        binding.tomarFoto.setOnClickListener{
            //abrirCamara.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{
                crearArchivo()
                val fotoUri=FileProvider.getUriForFile(this,BuildConfig.APPLICATION_ID+".fileprovider",file)
                it.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri)
            }
            abrirCamara.launch(intent)
        }

        binding.registrarMas.setOnClickListener{
            if (email != null) {
                guardarMascota(email)
            }else{
                Toast.makeText(this,"No se registro la mascota", Toast.LENGTH_LONG).show()
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.cerrar->{
                firebaseAuth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun guardarMascota(email:String){

        val nombre: String = binding.nombreMas.text.toString()
        val tipo: String = binding.tipoMas.text.toString()
        val raza : String = binding.razaMas.text.toString()
        val color:String= binding.colorMas.text.toString()
        val sexo:Int = binding.sexo.id
        val foto: String = file.toString()

        var pref= getSharedPreferences("${email}_${nombre}", Context.MODE_PRIVATE)
        var edit=pref.edit()
        edit.putString("id_mascota","${email}_${nombre}")
        edit.putString("usuario",email)
        edit.putString("nombre_mascota",nombre)
        edit.putString("tipo_mascota",tipo)
        edit.putString("raza_mascota",raza)
        edit.putString("color_mascota",color)
        edit.putString("foto_mascota",foto)
        if(sexo==0){
            edit.putString("sexo_mascota","Hembra")
        }else {
            edit.putString("sexo_mascota", "Macho")
        }
        edit.commit()
        Toast.makeText(this,"Mascota Registrada exitosamente", Toast.LENGTH_LONG).show()
    }

    val abrirCamara =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if(result.resultCode == RESULT_OK){
               // val data = result.data
               // val bitmap = data.extras?.get("data") as Bitmap
                val bitmap = BitmapFactory.decodeFile(file.toString())
                binding.imagenFoto.setImageBitmap(bitmap)
            }

        }

    private lateinit var file: File
    private  fun crearArchivo(){
        val dir= getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        file = File.createTempFile("Foto_${System.currentTimeMillis()}_",".jpg",dir)
    }




}