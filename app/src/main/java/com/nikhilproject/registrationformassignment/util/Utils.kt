package com.nikhilproject.registrationformassignment.util

import android.content.Context
import android.widget.Toast

class Utils {

    companion object {

        fun showErrorToastMessage(showErrorToast: Boolean = false, toastMessage: String, context: Context) {
            if (showErrorToast) {
                Toast.makeText(
                    context,
                    toastMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val firstAndLastNameValidation: (String) -> Boolean = {
            it.isNotBlank() && it.length > 3 && it.all { char -> char.isLetter() }
        }

        val emailValidation: (String) -> Boolean = { email ->
            // Regular expression for standard email validation
            val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
            email.matches(emailRegex)
        }

        val passwordValidation: (String) -> Boolean = { password ->
            val digitRegex = Regex(".*\\d.*") // At least one digit
            val specialCharRegex = Regex("[^A-Za-z0-9 ]") // Special character excluding space

            password.length >= 8 && // Minimum length of 8 characters
                    password.matches(digitRegex) && // Contains at least one digit
                    password.contains(specialCharRegex) // Contains at least one special character
        }

        val confirmPasswordValidation: (String, String) -> Boolean = { password, confirmPassword ->
            password == confirmPassword
        }

        val mobileNumberValidation: (String) -> Boolean = { mobileNumber ->
            val numberRegex = Regex("^[0-9]{10}$") // 10-digit number
            mobileNumber.matches(numberRegex)
        }

        // Professional page validations

        val educationValidation: (String?) -> Boolean = {education ->
            education != null
        }

        val yearOfPassingValidation: (String) -> Boolean = { year ->
            year.matches(Regex("[0-9]+"))
        }

        val domainValidation: (String?) -> Boolean = { domain ->
             domain != null && domain.matches(Regex("[a-zA-Z0-9]+"))
        }

        val designationValidation: (String?) -> Boolean = { designation ->
          designation != null && designation.matches(Regex("[a-zA-Z0-9]+"))
        }

        val gradeValidation: (String) -> Boolean = { grade ->
            grade.matches(Regex("[a-zA-Z0-9]+"))
        }

        val experienceValidation: (String) -> Boolean = { year ->
            year.matches(Regex("[0-9]+"))
        }

        // Address Page validations

        val addressValidation: (String) -> Boolean = { address ->
            (address.length ?: 0) > 3
        }

        val landmarkValidation: (String) -> Boolean = { landmark ->
            (landmark.length ?: 0) > 3
        }

        val zipCodeValidation: (String) -> Boolean = { zipCode ->
            zipCode.matches(Regex("[0-9]{6}")) ?: false
        }

        val cityValidation: (String) -> Boolean = { city ->
            city.matches(Regex("[a-zA-Z]+"))
        }

        val stateValidation: (String?) -> Boolean = { state ->
            state != null
        }


    }
}