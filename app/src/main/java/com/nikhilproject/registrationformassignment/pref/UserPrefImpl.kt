package com.nikhilproject.registrationformassignment.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class UserPrefImpl(private val dataStore: DataStore<Preferences>) : UserPref {

    override fun getValue(key: Preferences.Key<String>): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[key] ?: ""
        }
    }
    override suspend fun saveValue(key: Preferences.Key<String>, value: String) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}