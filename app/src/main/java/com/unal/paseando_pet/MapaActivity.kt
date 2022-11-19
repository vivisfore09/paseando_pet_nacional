package com.unal.paseando_pet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var mapa: GoogleMap
    lateinit var nombre: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)
        var nombre= intent.getStringExtra("nombre")
        crearFragment()

    }

    fun crearFragment(){
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(mapaCreado: GoogleMap) {
        mapa= mapaCreado
        crearMarker()
    }

    private fun crearMarker(){
        val punto= LatLng(4.686891572926365, -74.0523153751559)
        val marcador :MarkerOptions = MarkerOptions().position(punto).title(nombre)
        mapa.addMarker(marcador)
        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(punto,30f),3000, null)
    }
}