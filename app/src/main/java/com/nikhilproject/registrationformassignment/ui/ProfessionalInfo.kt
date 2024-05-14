package com.nikhilproject.registrationformassignment.ui

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nikhilproject.registrationformassignment.Constant.Companion.DESIGNATION_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.DOMAIN_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.EDUCATION_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.EXPERIENCE_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.GRADE_KEY
import com.nikhilproject.registrationformassignment.Constant.Companion.YEAR_OF_PASSING_KEY
import com.nikhilproject.registrationformassignment.R
import com.nikhilproject.registrationformassignment.composeutils.EditFieldsWithoutLeadingIcon
import com.nikhilproject.registrationformassignment.composeutils.ExposedDropdownMenuBox
import com.nikhilproject.registrationformassignment.composeutils.TextBox
import com.nikhilproject.registrationformassignment.composeutils.ToolbarWithBackButton
import com.nikhilproject.registrationformassignment.model.ProfessionPageData
import com.nikhilproject.registrationformassignment.util.Designation
import com.nikhilproject.registrationformassignment.util.Domain
import com.nikhilproject.registrationformassignment.util.EducationOption
import com.nikhilproject.registrationformassignment.util.Utils
import com.nikhilproject.registrationformassignment.util.Utils.Companion.designationValidation
import com.nikhilproject.registrationformassignment.util.Utils.Companion.domainValidation
import com.nikhilproject.registrationformassignment.util.Utils.Companion.educationValidation
import com.nikhilproject.registrationformassignment.util.Utils.Companion.experienceValidation
import com.nikhilproject.registrationformassignment.util.Utils.Companion.gradeValidation
import com.nikhilproject.registrationformassignment.util.Utils.Companion.yearOfPassingValidation
import com.nikhilproject.registrationformassignment.util.YearOfPassing
import com.nikhilproject.registrationformassignment.viewmodel.RegistrationViewModel

@Composable
fun ProfessionInfoPage(navController: NavHostController, viewModel: RegistrationViewModel) {

    val professionalFormDetails = viewModel.professionalFormDetails.value
    val context = LocalContext.current

    val educationOption = EducationOption.entries.map {
        when (it) {
            EducationOption.POST_GRADUATE -> "Post Graduate"
            EducationOption.GRADUATE -> "Graduate"
            EducationOption.HSC_DIPLOMA -> "HSC/Diploma"
            EducationOption.SSC -> "SSC"
        }
    }

    val yearOfPassing = YearOfPassing.entries.map {
        when (it) {
            YearOfPassing.Y_2024 -> "2024"
            YearOfPassing.Y_2023 -> "2023"
            YearOfPassing.Y_2022 -> "2022"
            YearOfPassing.Y_2021 -> "2021"
            YearOfPassing.Y_2020 -> "2020"
        }
    }

    val domainList = Domain.entries.map {
        when (it) {
            Domain.ANDROID_DEVELOPER -> "Android Developer"
            Domain.REACT_NATIVE_DEVELOPER -> "React Native Developer"
            Domain.IOS_DEVELOPER -> "iOS Developer"
            Domain.FLUTTER_DEVELOPER -> "Flutter Developer"
        }
    }

    val designationList = Designation.entries.map {
        when (it) {
            Designation.SOFTWARE_ENGINEER -> "Software Engineer"
            Designation.QA -> "QA"
            Designation.PROJECT_MANAGER -> "Project Manager"
            Designation.PRODUCT_MANAGER -> "Product Manager"
        }
    }

    Column {

        ToolbarWithBackButton(
            title = stringResource(id = R.string.educational_info),
            onBackClick = { navController.navigate("basicInfo") }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(15.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.Education),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(5.dp)
            )
            ExposedDropdownMenuBox(
                list = educationOption,
                onChange = {
                    viewModel.setProfessionalFormDetails(
                        professionalFormDetails.copy(
                            education = it
                        )
                    )
                },
                validation = educationValidation
            )

            Text(
                text = stringResource(id = R.string.year_of_passing),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(5.dp)
            )
            ExposedDropdownMenuBox(
                list = yearOfPassing,
                onChange = {
                    viewModel.setProfessionalFormDetails(
                        professionalFormDetails.copy(
                            yearOfPassing = it
                        )
                    )
                },
                validation = yearOfPassingValidation
            )

            TextBox(text = stringResource(id = R.string.grade))
            EditFieldsWithoutLeadingIcon(
                text = professionalFormDetails.grade,
                placeHolder = stringResource(id = R.string.grade),
                onChange = {
                    viewModel.setProfessionalFormDetails(professionalFormDetails.copy(grade = it))
                },
                validation = gradeValidation
            )

            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                modifier = Modifier
                    .height(2.dp),
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(10.dp))

            TextBox(text = stringResource(id = R.string.experience))
            EditFieldsWithoutLeadingIcon(
                text = professionalFormDetails.experience,
                placeHolder = stringResource(id = R.string.experience),
                onChange = {
                    viewModel.setProfessionalFormDetails(professionalFormDetails.copy(experience = it))

                },
                validation = experienceValidation
            )

            Text(
                text = stringResource(id = R.string.designation),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(5.dp)
            )
            ExposedDropdownMenuBox(
                list = designationList, onChange = {
                    viewModel.setProfessionalFormDetails(
                        professionalFormDetails.copy(
                            designation = it
                        )
                    )
                },
                validation = designationValidation
            )

            Text(
                text = stringResource(id = R.string.domain),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(5.dp)
            )
            ExposedDropdownMenuBox(
                list = domainList,
                onChange = {
                    viewModel.setProfessionalFormDetails(
                        professionalFormDetails.copy(
                            domain = it
                        )
                    )
                },
                validation = domainValidation
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .border(2.dp, color = Color(0xFF293264)),
                    onClick = { navController.navigate("basicInfo") },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text(
                        text = stringResource(id = R.string.previous),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.weight(.1f))

                Button(
                    modifier = Modifier
                        .weight(1f),
                    onClick = {
                        professionalPageValidation(
                            viewModel = viewModel,
                            professionalFormDetails = professionalFormDetails,
                            context = context,
                            navController = navController
                        )

                    },
                    shape = RectangleShape
                ) {
                    Text(
                        text = stringResource(id = R.string.next),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

private fun professionalPageValidation(
    viewModel: RegistrationViewModel,
    professionalFormDetails: ProfessionPageData,
    navController: NavHostController,
    context: Context
) {
    var isValid = true

    if (!educationValidation(professionalFormDetails.education)) {
        Utils.showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.education_error_message)
        )
        isValid = false
    } else if (yearOfPassingValidation(professionalFormDetails.yearOfPassing)) {
        Utils.showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.year_of_passing_error_message)
        )
        isValid = false
    } else if (domainValidation(professionalFormDetails.domain)) {
        Utils.showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.domain_error_message)
        )
        isValid = false
    } else if (designationValidation(professionalFormDetails.designation)) {
        Utils.showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.designation_error_message)
        )
        isValid = false
    } else if (gradeValidation(professionalFormDetails.grade)) {
        Utils.showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.grade_error_message)
        )
        isValid = false
    } else if (experienceValidation(professionalFormDetails.experience)) {
        Utils.showErrorToastMessage(
            showErrorToast = true,
            context = context,
            toastMessage = context.getString(R.string.experience_error_message)
        )
        isValid = false
    }

    if (isValid) {
        viewModel.saveDetails(
            value = professionalFormDetails.education,
            key = EDUCATION_KEY
        )
        viewModel.saveDetails(
            value = professionalFormDetails.grade,
            key = GRADE_KEY
        )
        viewModel.saveDetails(
            value = professionalFormDetails.experience,
            key = EXPERIENCE_KEY
        )
        viewModel.saveDetails(
            value = professionalFormDetails.yearOfPassing,
            key = YEAR_OF_PASSING_KEY
        )
        viewModel.saveDetails(
            value = professionalFormDetails.designation,
            key = DESIGNATION_KEY
        )
        viewModel.saveDetails(
            value = professionalFormDetails.domain,
            key = DOMAIN_KEY
        )
        navController.navigate("addressInfo")
    }
}
