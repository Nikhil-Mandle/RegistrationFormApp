package com.nikhilproject.registrationformassignment.composeutils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarWithBackButton(
    title: String,
    onBackClick: () -> Unit,
    isBackButtonShown: Boolean = true
) {
    Box(modifier = Modifier) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            },
            navigationIcon = {
                if (isBackButtonShown)
                    IconButton(onClick = { onBackClick() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
            }
        )
    }
}