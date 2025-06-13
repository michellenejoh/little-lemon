package com.littlelemon.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController

@Composable
fun Home (navController: NavHostController, menuItemsLiveData: LiveData<List<MenuItemRoom>>) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp),

        ) {
            Image (
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Header",
                modifier = Modifier
                    .padding(end = 40.dp, start=40.dp, bottom = 0.dp)
                    .height(40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Icon",
                modifier = Modifier
                    .height(40.dp)
                    .padding(start=20.dp, bottom = 0.dp)
                    .clickable {
                        navController.navigate(Profile.route)
                    }
            )
        }
        Column ( Modifier.background(Color(0xFF495E57))){
            Text (
                "Little Lemon",
                color = Color(0xFFF4CE14),
                textAlign = TextAlign.Left,
                fontSize=35.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            )
            Text (
                "Chicago",
                color = Color.White,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            Row {
                Text (
                    "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                    textAlign = TextAlign.Left,
                    fontSize=18.sp,
                    modifier = Modifier.weight(2f)
                        .padding(start = 16.dp),
                            color = Color.White
                )
                Image (
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "hero",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(150.dp)
                        .padding(bottom = 30.dp, end = 10.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .weight(1f)
                )

            }
            MenuItem(menuItemsLiveData)
        }
    }
}
