package com.unal.paseando_pet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.unal.paseando_pet.databinding.ActivityLoginBinding
import com.unal.paseando_pet.databinding.FragmentPaseadorBinding


class PaseadorFragment : Fragment() , RecyclerPaseador.clickPaseador {


    private var _binding: FragmentPaseadorBinding? = null
    private val binding get() = _binding!!
    private var lista:MutableList<Paseador> = mutableListOf()
    private lateinit var recycler: RecyclerView
    lateinit var firestoreBD : FirebaseFirestore
    lateinit var intent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaseadorBinding.inflate(inflater)
        var view: FrameLayout = binding.root
        firestoreBD = FirebaseFirestore.getInstance()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lista.clear()
        intent=Intent(context,Detalle_PaseadorActivity::class.java)
        firestoreBD.collection("Paseadores").get().addOnSuccessListener {
            resultado->
            for(doc in resultado){
                var pas = Paseador(
                    doc.getString("nombre") as String,
                    doc.getString("contacto") as String,
                    doc.getString("ciudad") as String,
                    doc.getString("foto") as String,
                    doc.getString("perfil") as String
                )
                print("------------------------------>>>>>>>>>>>>>> ${doc.id}")
                lista.add(pas)
            }
            binding.listaPaseadores.apply {
                layoutManager=LinearLayoutManager(activity)
                adapter= RecyclerPaseador(lista,this@PaseadorFragment)
            }

        }


        //lista.add(Paseador("Juan Perez", "3203459876", "Bogotá", "https://firebasestorage.googleapis.com/v0/b/mascotas-unal-app.appspot.com/o/img_paseadores%2Fpaseador3.webp?alt=media&token=3c12ae3a-22cc-4e5e-9850-7c77785ca0c3","perfil"))
        //lista.add(Paseador("Maria Rojas", "3129085433", "Bogotá", "https://firebasestorage.googleapis.com/v0/b/mascotas-unal-app.appspot.com/o/img_paseadores%2Fpaseador3.webp?alt=media&token=3c12ae3a-22cc-4e5e-9850-7c77785ca0c3","perfil"))
       // lista.add(Paseador("Pedro Mora", "3212329043", "Medellin", "https://firebasestorage.googleapis.com/v0/b/mascotas-unal-app.appspot.com/o/img_paseadores%2Fpaseador3.webp?alt=media&token=3c12ae3a-22cc-4e5e-9850-7c77785ca0c3","perfil"))
        /*lista.add(Paseador("Luisa Giraldo", "3138923456", "Medellin", R.drawable.email))
        lista.add(Paseador("Francisco Sanchez", "3205678923", "Cali", R.drawable.persona))
        lista.add(Paseador("Juanita Rojas", "3129085433", "Bogotá", R.drawable.info))
        lista.add(Paseador("Fernando Mora", "3212329043", "Medellin", R.drawable.ninio))
        lista.add(Paseador("Wilson Giraldo", "3138923456", "Medellin", R.drawable.direccion))
        lista.add(Paseador("Monica Sanchez", "3205678923", "Cali", R.drawable.password))*/

    }

    override fun onItemClick(paseador: Paseador) {
        intent.putExtra("nomPas",paseador.nombre)
        intent.putExtra("ciudPas",paseador.ciudad)
        intent.putExtra("contactoPas",paseador.contacto)
        intent.putExtra("perfilPas",paseador.perfil)
        intent.putExtra("fotoPas",paseador.foto)

        startActivity(intent)
        Toast.makeText(context,"${paseador.ciudad}", Toast.LENGTH_LONG).show()
    }

}