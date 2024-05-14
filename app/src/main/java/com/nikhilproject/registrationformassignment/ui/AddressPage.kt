package com.nikhilproject.registrationformassignment.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nikhilproject.registrationformassignment.Constant.Companion.ADDRESS_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.CITY_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.LANDMARK_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.PIN_CODE_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.STATE_KEY
import com.nikhilproject.registrationformassignment.R
import com.nikhilproject.registrationformassignment.composeutils.EditFieldsWithLeadingIcon
import com.nikhilproject.registrationformassignment.composeutils.ExposedDropdownMenuBox
import com.nikhilproject.registrationformassignment.composeutils.ToolbarWithBackButton
import com.nikhilproject.registrationformassignment.model.AddressFormData
import com.nikhilproject.registrationformassignment.util.STATE
import com.nikhilproject.registrationformassignment.util.Utils
import com.nikhilproject.registrationformassignment.util.Utils.Companion.addressValidation
import com.nikhilproject.registrationformassignment.util.Utils.Companion.cityValidation
import com.nikhilproject.registrationformassignment.util.Utils.Companion.landmarkValidation
import com.nikhilproject.registrationformassignment.util.Utils.Companion.stateValidation
import com.nikhilproject.registrationformassignment.util.Utils.Companion.zipCodeValidation
import com.nikhilproject.registrationformassignment.viewmodel.RegistrationViewModel

@Composable
fun YourAddress(navController: NavHostController, viewModel: RegistrationViewModel) {
    val context = LocalContext.current
    val addressFormDetails = viewModel.addressFormData.value


    val stateList = STATE.entries.map {
        when (it) {
            STATE.MAHARASHTRA -> "Maharashtra"
            STATE.GUJARAT -> "Gujarat"
            STATE.KARNATAKA -> "Karnataka"
            STATE.MADHYA_PRADESH -> "Madhya Pradesh"
            STATE.DELHI -> "Delhi"
            STATE.OTHERS -> "Others"
        }
    }

    Column {
        ToolbarWithBackButton(
            title = stringResource(id = R.string.your_address),
            onBackClick = { navController.navigate("professionInfo") }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            EditFieldsWithLeadingIcon(
                text = addressFormDetails.address,
                placeHolder = stringResource(id = R.string.address),
                leadingIcon = R.drawable.location,
                onChange = {
                    viewModel.setAddressFormDetails(addressFormDetails.copy(address = it))
                },
                validation = addressValidation
            )
            Spacer(modifier = Modifier.height(10.dp))

            EditFieldsWithLeadingIcon(
                text = addressFormDetails.landmark,
                placeHolder = stringResource(id = R.string.landMark),
                leadingIcon = R.drawable.location,
                onChange = {
                    viewModel.setAddressFormDetails(addressFormDetails.copy(landmark = it))
                },
                validation = landmarkValidation
            )
            Spacer(modifier = Modifier.height(10.dp))

            EditFieldsWithLeadingIcon(
                text = addressFormDetails.city,
                placeHolder = stringResource(id = R.string.city),
                leadingIcon = R.drawable.location,
                onChange = {
                    viewModel.setAddressFormDetails(addressFormDetails.copy(city = it))
                },
                validation = cityValidation
            )

            Spacer(modifier = Modifier.height(10.dp))

            Spacer(modifier = Modifier.height(10.dp))
            ExposedDropdownMenuBox(
                list = stateList, onChange = {
                    viewModel.setAddressFormDetails(
                        addressFormDetails.copy(
                            state = it
                        )
                    )
                },
                validation = stateValidation
            )

            Spacer(modifier = Modifier.height(10.dp))
            EditFieldsWithLeadingIcon(
                text = addressFormDetails.pinCode,
                placeHolder = stringResource(id = R.string.pin_code),
                leadingIcon = R.drawable.location,
                onChange = {
                    viewModel.setAddressFormDetails(addressFormDetails.copy(pinCode = it))
                },
                validation = zipCodeValidation
            )

            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    addressDetailsValidation(
                        context = context,
                        addressFormData = addressFormDetails,
                        viewModel = viewModel
                    )

                },
                shape = RectangleShape
            ) {
                Text(
                    text = stringResource(id = R.string.submit),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

private fun addressDetailsValidation(
    context: Context,
    viewModel: RegistrationViewModel,
    addressFormData: AddressFormData,
) {

    var isValid = true

    if (!addressValidation(addressFormData.address)) {
        Utils.showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.address_error_message)
        )
        isValid = false
    } else if (!cityValidation(addressFormData.city)) {
        Utils.showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.city_error_message)
        )
        isValid = false
    } else if (!zipCodeValidation(addressFormData.pinCode)) {
        Utils.showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.zip_code_error_message)
        )
        isValid = false
    } else if (!stateValidation(addressFormData.state)) {
        Utils.showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.state_error_message)
        )
        isValid = false
    } else if (!landmarkValidation(addressFormData.landmark)) {
        Utils.showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.landmark_error_message)
        )
        isValid = false
    }

    if (isValid) {
        viewModel.saveDetails(key = ADDRESS_KEY, value = addressFormData.address)
        viewModel.saveDetails(key = LANDMARK_KEY, value = addressFormData.landmark)
        viewModel.saveDetails(key = CITY_KEY, value = addressFormData.city)
        viewModel.saveDetails(key = PIN_CODE_KEY, value = addressFormData.pinCode)
        viewModel.saveDetails(key = STATE_KEY, value = addressFormData.state)
        Toast.makeText(context, "Data Submitted", Toast.LENGTH_SHORT).show()

        viewModel.clearFormData()
    }
}
