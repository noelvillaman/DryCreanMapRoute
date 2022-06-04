package com.software.namalliv.drycreanmaproute.util

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Build.VERSION_CODES.Q
import androidx.fragment.app.Fragment
import com.software.namalliv.drycreanmaproute.util.Constants.PERMISSION_BACKGROUDN_LOCATION_REQUEST_CODE
import com.software.namalliv.drycreanmaproute.util.Constants.PERMISSION_LOCATION_REQUEST_CODE
import pub.devrel.easypermissions.EasyPermissions
import kotlin.jvm.internal.Ref


object Permissions {

    fun hasLocationPermission(context: Context) =
        EasyPermissions.hasPermissions(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )


    fun requestLocationPermission(fragment : Fragment){
        EasyPermissions.requestPermissions(fragment,
        "This app cannot work without thiss permission",
        PERMISSION_LOCATION_REQUEST_CODE,
        Manifest.permission.ACCESS_FINE_LOCATION)
    }

    fun hasBackgroundLocationPermission(context: Context):Boolean{
        if (Build.VERSION.SDK_INT> Q){
            return EasyPermissions.hasPermissions(
                context,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
        return true
    }

    fun requestBackgroundLocationPermission(fragment: Fragment){
        if (Build.VERSION.SDK_INT> Q){
            return EasyPermissions.requestPermissions(
                fragment,
                "We need this",
                PERMISSION_BACKGROUDN_LOCATION_REQUEST_CODE,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
    }
}