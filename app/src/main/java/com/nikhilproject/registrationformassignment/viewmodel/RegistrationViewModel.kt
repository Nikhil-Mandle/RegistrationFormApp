package com.nikhilproject.registrationformassignment.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhilproject.registrationformassignment.model.AddressFormData
import com.nikhilproject.registrationformassignment.model.BasicPageData
import com.nikhilproject.registrationformassignment.model.ProfessionPageData
import com.nikhilproject.registrationformassignment.pref.UserPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val userPref: UserPref) : ViewModel() {

    private val _basicPageFormData = mutableStateOf(BasicPageData())
    val basicPageFormData: State<BasicPageData> = _basicPageFormData

    private val _professionalFormData = mutableStateOf(ProfessionPageData())
    val professionalFormDetails: State<ProfessionPageData> = _professionalFormData

    private val _addressFormData = mutableStateOf(AddressFormData())
    val addressFormData: State<AddressFormData> = _addressFormData

    fun setBasicFormDetails(basicPage: BasicPageData) {
        _basicPageFormData.value = basicPage
    }

    fun setProfessionalFormDetails(professionalPageData: ProfessionPageData) {
        _professionalFormData.value = professionalPageData
    }

    fun setAddressFormDetails(addressFormData: AddressFormData) {
        _addressFormData.value = addressFormData
    }

    fun clearFormData() {
        _basicPageFormData.value = BasicPageData()
        _professionalFormData.value = ProfessionPageData()
        _addressFormData.value = AddressFormData()
    }

    // DataStore Preferences
    fun getValueForKey(key: Preferences.Key<String>): Flow<String> {
        return userPref.getValue(key)
    }

    fun saveDetails(value: String, key: Preferences.Key<String>) {
        viewModelScope.launch {
            userPref.saveValue(key, value)
        }
    }
}