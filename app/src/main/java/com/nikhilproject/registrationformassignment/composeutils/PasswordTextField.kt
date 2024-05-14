package com.nikhilproject.registrationformassignment.composeutils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nikhilproject.registrationformassignment.R

@Composable
fun PasswordTextField(
    text: String,
    placeHolder: String,
    onChange: (String) -> Unit,
    validation: (String) -> Boolean = { false }
) {
    val focusManager = LocalFocusManager.current
    val showPassword = remember { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "Description" },
            value = text,
            onValueChange = {
                isValid = validation(it)
                onChange.invoke(it)
            },
            placeholder = {
                Text(
                    text = placeHolder,
                    color = if (isSystemInDarkTheme()) Color.White else Color(0xFF293264),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            singleLine = true,
            isError = !isValid,
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.lock),
                    contentDescription = "password Lock",
                    colorFilter = if (isSystemInDarkTheme()) ColorFilter.tint(Color.White) else ColorFilter.tint(
                        Color(0xFF293264)
                    )
                )
            },
            trailingIcon = {
                Image(
                    painter = painterResource(id = if (showPassword.value) R.drawable.pass_visibility else R.drawable.visibility_off),
                    contentDescription = "password visibility",
                    colorFilter = if (isSystemInDarkTheme()) ColorFilter.tint(Color.White) else ColorFilter.tint(
                        Color(0xFF293264)
                    ),
                    modifier = Modifier.clickable {
                        showPassword.value = !showPassword.value
                    })
            },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                focusedBorderColor = if (isSystemInDarkTheme()) Color.White else Color(0xFF293264),
                unfocusedBorderColor = if (isSystemInDarkTheme()) Color.White else Color(0xFF293264),
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}