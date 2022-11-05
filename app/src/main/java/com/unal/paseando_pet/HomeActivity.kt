package com.unal.paseando_pet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.unal.paseando_pet.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val email=intent.getStringExtra("email")
        Toast.makeText(this,"Bienvenido........$email", Toast.LENGTH_LONG).show()
        val mascotas_F = MascotasFragment()
        val paseadores_F = PaseadorFragment()
        val paseos_F = PaseosFragment()
        val tips_F = InformacionFragment()

        val bundle = Bundle()
        bundle.putString("email",email)
        mascotas_F.arguments=bundle

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {

            when(it.itemId){
                 R.id.mascotas -> {
                     menuInferior(mascotas_F)
                     true
                 }
                R.id.paseadores -> {
                    menuInferior(paseadores_F)
                    true
                }
                R.id.paseos -> {
                    menuInferior(paseos_F)
                    true
                }
                R.id.tips -> {
                    menuInferior(tips_F)
                    true
                }
                else->false
         }
        }

    }

    private fun menuInferior(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            commit()
        }
    }
}