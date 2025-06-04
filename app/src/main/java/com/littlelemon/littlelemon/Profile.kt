package com.littlelemon.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Profile(navController: NavHostController, sharedPreferences: SharedPreferences) {
    val firstName = sharedPreferences.getString("firstName", "") ?: ""
    val lastName = sharedPreferences.getString("lastName", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image (
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Header",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(bottom= 16.dp, top = 16.dp)
                .fillMaxWidth()
                .height(100.dp)

        )
        Text (
            "Profile information",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom= 16.dp, top = 16.dp)
        )
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp),
           ){
            Text("First Name: $firstName")
            Text("Last Name: $lastName")
            Text("Email: $email")

        }

        Button (
            onClick = {navController.navigate(Onboarding.route)
                sharedPreferences.edit().clear().apply()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor =  Color(0xFFF4CE14),
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        { Text("Log out") }
    }
}

