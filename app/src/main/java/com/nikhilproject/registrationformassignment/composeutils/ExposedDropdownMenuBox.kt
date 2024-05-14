package com.nikhilproject.registrationformassignment.composeutils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuBox(
    list: List<String>,
    onChange: (String) -> Unit = {},
    validation: (String) -> Boolean = {false}
) {

    val focusManager = LocalFocusManager.current
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(list[0]) }
    var isValid by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = {
                    selectedText = it
                    isValid = validation(it)
                    onChange.invoke(it)
                },
                isError = !isValid,
                placeholder = {
                    Text(
                        text = list[0],
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black
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
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .menuAnchor(),
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    focusedBorderColor = if (isSystemInDarkTheme()) Color.White else Color(0xFF293264),
                    unfocusedBorderColor = if (isSystemInDarkTheme()) Color.White else Color(0xFF293264),
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                list.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                fontWeight = FontWeight.Light,
                                fontSize = 14.sp
                            )
                        },
                        onClick = {
                            selectedText = item
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewExposedDropdownMenuBox() {
    val test = listOf("test1", "test2", "test3", "test4")
    ExposedDropdownMenuBox(list = test)
}