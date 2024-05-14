package com.nikhilproject.registrationformassignment.model

import android.net.Uri
import com.nikhilproject.registrationformassignment.composeutils.Gender

data class BasicPageData(
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val gender: Gender = Gender.Male,
    val password: String = "",
    val confirmPassword: String = "",
    val selectedImageUri: Uri? = null
)
