package com.littlelemon.littlelemon

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun Onboarding (sharedPreferences: SharedPreferences, navController: NavController) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current


    fun validate () {
        if (firstName.isBlank() || lastName.isBlank() ||  email.isBlank()) {
            Toast.makeText(context, "Registration unsuccessful. Please enter all data.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Registration successful!.", Toast.LENGTH_SHORT).show()
            sharedPreferences.edit()
                .putString("firstName", firstName)
                .putString("lastName", lastName)
                .putString("email", email)
                .apply()
            navController.navigate(Home.route)
        }
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Image (
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Header",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(bottom= 16.dp, top = 20.dp)
                .fillMaxWidth()
                .height(50.dp)

        )
        Text (
            "Let's get to know you",
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color(0xFF495E57))
                .fillMaxWidth()
                .padding(16.dp)
        )
        Text (
            "Personal information",
            modifier = Modifier.padding(16.dp),
        )
        OutlinedTextField (
            value = firstName,
            onValueChange = {firstName = it},
            label = { Text("First Name", fontSize=12.sp) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF495E57),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF495E57),
                cursorColor = Color(0xFF495E57),
            ))

        OutlinedTextField (
            value = lastName,
            onValueChange = {lastName = it},
            label = { Text("Last Name", fontSize=12.sp) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF495E57),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF495E57),
                cursorColor = Color(0xFF495E57),
            )
        )
        OutlinedTextField (
            value = email,
            onValueChange = {email = it},
            label = { Text("Email", fontSize=12.sp) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF495E57),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(0xFF495E57),
                        cursorColor = Color(0xFF495E57),
                    )
        )
       
        Button (
            onClick = {validate()},
            colors = ButtonDefaults.buttonColors(
                containerColor =  Color(0xFFF4CE14),
                contentColor = Color.Black
        ),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        { Text("Register") }
    }
}

