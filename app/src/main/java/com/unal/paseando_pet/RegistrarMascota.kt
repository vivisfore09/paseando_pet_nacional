package com.unal.paseando_pet

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.unal.paseando_pet.databinding.ActivityRegistrarMascotaBinding
import java.io.File


class RegistrarMascota: AppCompatActivity() {

    lateinit var binding: ActivityRegistrarMascotaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarMascotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email=intent.getStringExtra("email")
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

    }

    val abrirCamara =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if(result.resultCode == RESULT_OK){
                val data = result.data!!
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