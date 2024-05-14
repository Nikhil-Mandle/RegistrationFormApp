package com.nikhilproject.registrationformassignment.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class Permissions {
    companion object {
        val CAMERA_PERMISSION = arrayOf(
            Manifest.permission.CAMERA
        )

        fun hasRequiredPermission(context: Context): Boolean {
            return CAMERA_PERMISSION.all {
                ContextCompat.checkSelfPermission(
                    context,
                    it,
                ) == PackageManager.PERMISSION_GRANTED
            }
        }


    }
}