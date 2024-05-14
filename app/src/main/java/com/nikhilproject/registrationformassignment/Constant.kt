package com.nikhilproject.registrationformassignment

import androidx.datastore.preferences.core.stringPreferencesKey

class Constant {
    companion object {

        // Basic page keys
        val FIRST_NAME_KEY = stringPreferencesKey("first_name")
        val LAST_NAME_KEY = stringPreferencesKey("last_name")
        val PHONE_NUMBER_KEY = stringPreferencesKey("phone_number")
        val EMAIL_KEY = stringPreferencesKey("email")
        val GENDER_KEY = stringPreferencesKey("gender")
        val PASSWORD_KEY = stringPreferencesKey("password")
        val CONFIRM_PASSWORD_KEY = stringPreferencesKey("confirm_password")
        val PROFILE_PHOTO_KEY = stringPreferencesKey("profile_photo")

        // Professional Page keys
        val EDUCATION_KEY = stringPreferencesKey("education")
        val YEAR_OF_PASSING_KEY = stringPreferencesKey("year_of_passing")
        val GRADE_KEY = stringPreferencesKey("grade")
        val EXPERIENCE_KEY = stringPreferencesKey("experience")
        val DESIGNATION_KEY = stringPreferencesKey("designation")
        val DOMAIN_KEY = stringPreferencesKey("domain")

        // Address Page keys
        val ADDRESS_KEY = stringPreferencesKey("address")
        val LANDMARK_KEY = stringPreferencesKey("landmark")
        val CITY_KEY = stringPreferencesKey("city")
        val STATE_KEY = stringPreferencesKey("state")
        val PIN_CODE_KEY = stringPreferencesKey("pin_code")


    }
}