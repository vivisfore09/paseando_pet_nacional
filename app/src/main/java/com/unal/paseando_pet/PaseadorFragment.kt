package com.unal.paseando_pet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unal.paseando_pet.databinding.ActivityLoginBinding
import com.unal.paseando_pet.databinding.FragmentPaseadorBinding


class PaseadorFragment : Fragment() {


    private var _binding: FragmentPaseadorBinding? = null
    private val binding get() = _binding!!
    private var lista:MutableList<Paseador> = mutableListOf()
    private lateinit var recycler: RecyclerView

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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lista.add(Paseador("Juan Perez", "3203459876", "Bogotá", R.drawable.chico))
        lista.add(Paseador("Maria Rojas", "3129085433", "Bogotá", R.drawable.paseos))
        lista.add(Paseador("Pedro Mora", "3212329043", "Medellin", R.drawable.mascotas))
        lista.add(Paseador("Luisa Giraldo", "3138923456", "Medellin", R.drawable.email))
        lista.add(Paseador("Francisco Sanchez", "3205678923", "Cali", R.drawable.persona))
        lista.add(Paseador("Juanita Rojas", "3129085433", "Bogotá", R.drawable.info))
        lista.add(Paseador("Fernando Mora", "3212329043", "Medellin", R.drawable.ninio))
        lista.add(Paseador("Wilson Giraldo", "3138923456", "Medellin", R.drawable.direccion))
        lista.add(Paseador("Monica Sanchez", "3205678923", "Cali", R.drawable.password))
        binding.listaPaseadores.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter= RecyclerPaseador(lista)
        }

    }

}