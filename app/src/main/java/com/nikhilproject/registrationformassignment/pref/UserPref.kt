package com.nikhilproject.registrationformassignment.pref

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow

val USER_KEY = stringPreferencesKey("user_name")



interface UserPref {

    fun getValue(key: Preferences.Key<String>): Flow<String>

    suspend fun saveValue(key: Preferences.Key<String>, value: String)

}