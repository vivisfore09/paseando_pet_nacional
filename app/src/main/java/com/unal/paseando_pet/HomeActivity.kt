package com.unal.paseando_pet

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.unal.paseando_pet.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var firebaseAuth: FirebaseAuth

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
        firebaseAuth = Firebase.auth

        /*val bundle = Bundle()
        bundle.putString("email",email)
        mascotas_F.arguments=bundle*/

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

    private fun menuInferior(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            commit()
        }
    }
}