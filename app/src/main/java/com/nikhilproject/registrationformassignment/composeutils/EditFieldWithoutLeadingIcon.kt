package com.nikhilproject.registrationformassignment.composeutils

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun EditFieldsWithoutLeadingIcon(
    text: String,
    placeHolder: String,
    onChange: (String) -> Unit = {},
    validation: (String) -> Boolean = {false}
) {
    val focusManager = LocalFocusManager.current
    var isValid by remember { mutableStateOf(true) }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
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
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Start
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
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
            focusedBorderColor = if (isSystemInDarkTheme()) Color.White else Color(0xFF293264),
            unfocusedBorderColor = if (isSystemInDarkTheme()) Color.White else Color(0xFF293264),
        ),
        singleLine = true,
    )
}
