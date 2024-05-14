package com.nikhilproject.registrationformassignment.ui

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.nikhilproject.registrationformassignment.Constant.Companion.CONFIRM_PASSWORD_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.EMAIL_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.FIRST_NAME_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.GENDER_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.LAST_NAME_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.PASSWORD_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.PHONE_NUMBER_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.PROFILE_PHOTO_KEY
import com.nikhilproject.registrationformassignment.MainActivity
import com.nikhilproject.registrationformassignment.R
import com.nikhilproject.registrationformassignment.composeutils.EditFieldsWithLeadingIcon
import com.nikhilproject.registrationformassignment.composeutils.GenderSelection
import com.nikhilproject.registrationformassignment.composeutils.PasswordTextField
import com.nikhilproject.registrationformassignment.composeutils.TextBox
import com.nikhilproject.registrationformassignment.composeutils.ToolbarWithBackButton
import com.nikhilproject.registrationformassignment.model.BasicPageData
import com.nikhilproject.registrationformassignment.util.Permissions
import com.nikhilproject.registrationformassignment.util.Permissions.Companion.hasRequiredPermission
import com.nikhilproject.registrationformassignment.util.Utils.Companion.confirmPasswordValidation
import com.nikhilproject.registrationformassignment.util.Utils.Companion.emailValidation
import com.nikhilproject.registrationformassignment.util.Utils.Companion.firstAndLastNameValidation
import com.nikhilproject.registrationformassignment.util.Utils.Companion.mobileNumberValidation
import com.nikhilproject.registrationformassignment.util.Utils.Companion.passwordValidation
import com.nikhilproject.registrationformassignment.util.Utils.Companion.showErrorToastMessage
import com.nikhilproject.registrationformassignment.viewmodel.RegistrationViewModel


@Composable
fun BasicInfoPage(navController: NavHostController, viewModel: RegistrationViewModel) {

    val context = LocalContext.current
    val activityContext = LocalContext.current as MainActivity
    val basicPageFormDetails = viewModel.basicPageFormData.value

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            viewModel.setBasicFormDetails(basicPageFormDetails.copy(selectedImageUri = uri))
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { bitmap: Bitmap? ->
            bitmap?.let { imageBitmap ->
                val resolver = context.contentResolver
                val contentValues = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, "profile_image.jpg")
                    put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                }
                val imageUri =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                imageUri?.let { uri ->
                    resolver.openOutputStream(uri)?.use { outputStream ->
                        val uri =
                            if (!imageBitmap.compress(
                                    Bitmap.CompressFormat.JPEG,
                                    90,
                                    outputStream
                                )
                            ) {
                                // Compression failed
                                null
                            } else {
                                uri
                            }
                        viewModel.setBasicFormDetails(basicPageFormDetails.copy(selectedImageUri = uri))
                    }
                }
            }
        }
    )

    Column {
        ToolbarWithBackButton(
            title = stringResource(id = R.string.register_title),
            onBackClick = {},
            isBackButtonShown = false
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = rememberAsyncImagePainter(
                    model = basicPageFormDetails.selectedImageUri ?: ContextCompat.getDrawable(
                        context,
                        R.drawable.upload_profile_img
                    )
                ),
                contentDescription = "profile upload image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(1.dp, color = Color(0xFF293264), CircleShape)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        if (hasRequiredPermission(context)) {
                            val options = arrayOf("Take Photo", "Choose from Gallery")
                            AlertDialog
                                .Builder(context)
                                .setTitle("Select Option")
                                .setItems(options) { dialog, which ->
                                    when (which) {
                                        0 -> cameraLauncher.launch(null)
                                        1 -> galleryLauncher.launch("image/*")
                                    }
                                    dialog.dismiss()
                                }
                                .show()
                        } else {
                            ActivityCompat.requestPermissions(
                                activityContext, Permissions.CAMERA_PERMISSION, 0
                            )
                            Toast
                                .makeText(context, "Please allow permissions", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            )

            TextBox(text = stringResource(id = R.string.first_name))

            EditFieldsWithLeadingIcon(
                placeHolder = stringResource(id = R.string.first_name),
                leadingIcon = R.drawable.person,
                text = basicPageFormDetails.firstName,
                onChange = {
                    viewModel.setBasicFormDetails(basicPageFormDetails.copy(firstName = it))
                },
                validation = firstAndLastNameValidation
            )

            TextBox(text = stringResource(R.string.last_name))
            EditFieldsWithLeadingIcon(
                placeHolder = stringResource(R.string.last_name),
                leadingIcon = R.drawable.person,
                text = basicPageFormDetails.lastName,
                onChange = {
                    viewModel.setBasicFormDetails(basicPageFormDetails.copy(lastName = it))
                },
                validation = firstAndLastNameValidation
            )

            TextBox(text = stringResource(id = R.string.phone_number))
            EditFieldsWithLeadingIcon(
                placeHolder = stringResource(id = R.string.phone_number),
                leadingIcon = R.drawable.phone,
                text = basicPageFormDetails.phoneNumber,
                onChange = {
                    viewModel.setBasicFormDetails(basicPageFormDetails.copy(phoneNumber = it))
                },
                validation = mobileNumberValidation
            )

            TextBox(text = stringResource(id = R.string.email))
            EditFieldsWithLeadingIcon(
                placeHolder = stringResource(id = R.string.email),
                leadingIcon = R.drawable.email,
                text = basicPageFormDetails.email,
                onChange = {
                    viewModel.setBasicFormDetails(basicPageFormDetails.copy(email = it))
                },
                validation = emailValidation
            )

            GenderSelection {
                viewModel.setBasicFormDetails(basicPageFormDetails.copy(gender = it))
            }

            Text(
                text = stringResource(id = R.string.password),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            PasswordTextField(
                text = basicPageFormDetails.password,
                placeHolder = stringResource(id = R.string.password),
                onChange = {
                    viewModel.setBasicFormDetails(basicPageFormDetails.copy(password = it))
                },
                validation = passwordValidation
            )

            Text(
                text = stringResource(id = R.string.confirm_password),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            PasswordTextField(
                text = basicPageFormDetails.confirmPassword,
                placeHolder = stringResource(id = R.string.confirm_password),
                onChange = {
                    viewModel.setBasicFormDetails(basicPageFormDetails.copy(confirmPassword = it))
                },
                validation = passwordValidation
            )


            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    basicPageValidation(
                        basicPageFormDetails = basicPageFormDetails,
                        context = context,
                        navController = navController,
                        viewModel = viewModel
                    )
                },
                shape = RectangleShape
            ) {
                Text(
                    text = stringResource(id = R.string.next),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

private fun basicPageValidation(
    basicPageFormDetails: BasicPageData,
    context: Context,
    navController: NavHostController,
    viewModel: RegistrationViewModel
) {
    var isValid = true

    if (!firstAndLastNameValidation(basicPageFormDetails.firstName)
    ) {
        showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.first_name_error_message)
        )
        isValid = false

    } else if (!firstAndLastNameValidation(basicPageFormDetails.lastName)
    ) {
        showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.last_name_error_message)
        )
        isValid = false
    } else if (!emailValidation(basicPageFormDetails.email)) {
        showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.email_error_message)
        )
        isValid = false
    } else if (!mobileNumberValidation(basicPageFormDetails.phoneNumber)) {
        showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.phone_number_error_message)
        )
        isValid = false
    } else if (!passwordValidation(basicPageFormDetails.password)) {
        showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.password_error_message)
        )
        isValid = false
    } else if (!passwordValidation(basicPageFormDetails.confirmPassword)) {
        showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.password_error_message)
        )
        isValid = false
    } else if (!confirmPasswordValidation(
            basicPageFormDetails.password,
            basicPageFormDetails.confirmPassword
        )
    ) {
        showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.password_and_confirm_password_error_message)
        )
        isValid = false
    }

    if (isValid) {
        viewModel.saveDetails(value = basicPageFormDetails.firstName, key = FIRST_NAME_KEY)
        viewModel.saveDetails(value = basicPageFormDetails.lastName, key = LAST_NAME_KEY)
        viewModel.saveDetails(value = basicPageFormDetails.email, key = EMAIL_KEY)
        viewModel.saveDetails(value = basicPageFormDetails.phoneNumber, key = PHONE_NUMBER_KEY)
        viewModel.saveDetails(value = basicPageFormDetails.password, key = PASSWORD_KEY)
        viewModel.saveDetails(
            value = basicPageFormDetails.confirmPassword,
            key = CONFIRM_PASSWORD_KEY
        )
        viewModel.saveDetails(
            value = basicPageFormDetails.selectedImageUri.toString(),
            key = PROFILE_PHOTO_KEY
        )
        viewModel.saveDetails(value = basicPageFormDetails.gender.toString(), key = GENDER_KEY)

        navController.navigate("professionInfo")
    }
}