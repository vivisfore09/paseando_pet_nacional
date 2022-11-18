package com.unal.paseando_pet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.StateSet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.unal.paseando_pet.databinding.FragmentMascotasBinding


class MascotasFragment : Fragment(R.layout.fragment_mascotas) {


    private var _binding : FragmentMascotasBinding? = null
    private val binding get() = _binding!!
    private var fab: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMascotasBinding.inflate(inflater)
        var  view: ConstraintLayout = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var intent = Intent(context, RegistrarMascota::class.java)
       /* if(arguments != null){
            val email = requireArguments().getString("email")
            intent.putExtra("email",email)
        }*/
        fab = binding.agregar
        fab!!.setOnClickListener {
            startActivity(intent)
        }
    }

}