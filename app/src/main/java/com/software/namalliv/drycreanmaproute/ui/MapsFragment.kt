package com.software.namalliv.drycreanmaproute.ui

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.software.namalliv.drycreanmaproute.R
import java.lang.Exception

class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        val orlandoFl = LatLng(28.36474806416343, -81.30928845509652)
        googleMap.addMarker(MarkerOptions().position(orlandoFl).title("Marker in Orlando"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(orlandoFl, 13f))
        googleMap.uiSettings.apply {
            isTiltGesturesEnabled = true
        }
        changeMapStyle(googleMap)
    }

    private fun changeMapStyle(googleMap: GoogleMap) {
        try {
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    requireContext(),
                    R.raw.map_style
                )
            )
            if (!success){
                Log.d("TAG", "Failed to add style")
            }
        } catch (e: Exception){
            Log.d("TAG", e.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}