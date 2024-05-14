package com.nikhilproject.registrationformassignment.composeutils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TextBox(text: String){
    Text(
        text = text,
        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = Modifier
    )
}