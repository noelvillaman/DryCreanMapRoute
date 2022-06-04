package com.software.namalliv.drycreanmaproute.ui

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.software.namalliv.drycreanmaproute.R
import com.software.namalliv.drycreanmaproute.databinding.FragmentMapsBinding
import com.software.namalliv.drycreanmaproute.util.ExtensionsFunctions.hide
import com.software.namalliv.drycreanmaproute.util.ExtensionsFunctions.show
import com.software.namalliv.drycreanmaproute.util.Permissions.hasBackgroundLocationPermission
import com.software.namalliv.drycreanmaproute.util.Permissions.requestBackgroundLocationPermission
import com.software.namalliv.drycreanmaproute.util.Permissions.requestLocationPermission
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.lang.Exception

class MapsFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
    EasyPermissions.PermissionCallbacks {

    private var _bindding: FragmentMapsBinding? = null
    private val binding get() = _bindding!!

    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        init()
    }

    private fun init() {
        setUIClickListeners()
    }

    private fun setUIClickListeners() {
        binding.bntStart.setOnClickListener {
            onStartButtonClicked()
        }
        binding.bntStop.setOnClickListener { }
        binding.btnReset.setOnClickListener { }
        binding.circle1.setOnClickListener {
            requireActivity().finish()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.isMyLocationEnabled = true
        map.setOnMyLocationButtonClickListener(this)
        val orlandoFl = LatLng(28.36474806416343, -81.30928845509652)
        map.addMarker(MarkerOptions().position(orlandoFl).title("Marker in Orlando"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(orlandoFl, 13f))
        map.uiSettings.apply {
            isTiltGesturesEnabled = false
            isZoomGesturesEnabled = false
            isRotateGesturesEnabled = false
            isCompassEnabled = false
            isScrollGesturesEnabled = false
            isZoomControlsEnabled = false
        }
        changeMapStyle(map)
    }

    private fun changeMapStyle(googleMap: GoogleMap) {
        try {
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    requireContext(),
                    R.raw.map_style
                )
            )
            if (!success) {
                Log.d("TAG", "Failed to add style")
            }
        } catch (e: Exception) {
            Log.d("TAG", e.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _bindding = null
    }

    private fun onStartButtonClicked() {
        if (hasBackgroundLocationPermission(requireContext())) {
            Log.d("MapsActivity", "Already Enabled")
        } else {
            requestLocationPermission(this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        onStartButtonClicked()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(requireActivity()).build().show()
        } else {
            requestBackgroundLocationPermission(this)
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        binding.tvMessage.animate().alpha(0f).duration = 1500
        lifecycleScope.launch {
            delay(2500)
            binding.tvMessage.hide()
            binding.bntStart.show()
        }
        return false
    }
}