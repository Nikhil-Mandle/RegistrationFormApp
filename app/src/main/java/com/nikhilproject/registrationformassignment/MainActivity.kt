package com.nikhilproject.registrationformassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nikhilproject.registrationformassignment.ui.BasicInfoPage
import com.nikhilproject.registrationformassignment.ui.ProfessionInfoPage
import com.nikhilproject.registrationformassignment.ui.YourAddress
import com.nikhilproject.registrationformassignment.ui.theme.RegistrationFormAssignmentTheme
import com.nikhilproject.registrationformassignment.util.Permissions
import com.nikhilproject.registrationformassignment.viewmodel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: RegistrationViewModel by viewModels<RegistrationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!Permissions.hasRequiredPermission(context = applicationContext)) {
            ActivityCompat.requestPermissions(
                this, Permissions.CAMERA_PERMISSION, 0
            )
        }

        setContent {
            val navController = rememberNavController()

            RegistrationFormAssignmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn {
                        item {
                            NavHost(navController = navController, startDestination = "basicInfo") {
                                composable("basicInfo") { BasicInfoPage(navController, viewModel) }
                                composable("professionInfo") { ProfessionInfoPage(navController, viewModel) }
                                composable("addressInfo") { YourAddress(navController, viewModel) }
                            }
                        }
                    }
                }
            }
        }
    }
}