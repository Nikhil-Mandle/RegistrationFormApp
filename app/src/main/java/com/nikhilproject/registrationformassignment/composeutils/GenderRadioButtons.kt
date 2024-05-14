package com.nikhilproject.registrationformassignment.composeutils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nikhilproject.registrationformassignment.R


@Composable
fun GenderSelection(onGenderSelected: (Gender) -> Unit) {
    var selectedGender by remember { mutableStateOf(Gender.Male) }

    Column {
        Text(
            text = stringResource(id = R.string.gender),
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RadioButton(
                selected = selectedGender == Gender.Male,
                onClick = {
                    selectedGender = Gender.Male
                    onGenderSelected(Gender.Male)
                },
                colors = RadioButtonDefaults.colors(selectedColor = Color.Gray)
            )
            Text(
                text =  stringResource(id = R.string.female),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )

            RadioButton(
                selected = selectedGender == Gender.Female,
                onClick = {
                    selectedGender = Gender.Female
                    onGenderSelected(Gender.Female)
                },
                colors = RadioButtonDefaults.colors(selectedColor = Color.Gray)
            )
            Text(
                text = stringResource(id = R.string.male),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}

enum class Gender { Male, Female }