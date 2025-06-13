package com.littlelemon.littlelemon

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bumptech.glide.integration.compose.GlideImage
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MenuItem(menuItemsLiveData: LiveData<List<MenuItemRoom>>) {
    val menuItems by menuItemsLiveData.observeAsState(emptyList())

    var searchPhrase by remember { mutableStateOf("") }
    var orderMenuItems by remember { mutableStateOf("")}
    val dbmenuItems = if (searchPhrase.isNotBlank()) {
        val filteredItems = menuItems.filter {
            it.title.contains(searchPhrase, ignoreCase = true)
        }
        if (orderMenuItems.isNotBlank() ) filteredItems.filter { orderMenuItems == it.category } else filteredItems
    } else {
        if (orderMenuItems.isNotBlank()) menuItems.filter {orderMenuItems == it.category} else menuItems
    }


    OutlinedTextField(
        value = searchPhrase,
        onValueChange = { searchPhrase = it },
        label = { Text("Enter Search Phrase") },
        leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            focusedLabelColor =Color.White,
            cursorColor = Color.White,
        )
    )

    Row (modifier = Modifier.horizontalScroll(rememberScrollState()).background(Color.White)){
    menuItems.distinctBy { it.category }. forEach { item ->
        Button(
            onClick = { orderMenuItems = if (orderMenuItems == item.category) "" else item.category
            },
            colors = ButtonDefaults.buttonColors(containerColor =  Color.LightGray),
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(item.category)
        }
    }
}

    Column (Modifier.verticalScroll(rememberScrollState()).background(Color.White)){
        dbmenuItems.forEach { item ->

            MenuItems(item)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(menuItem: MenuItemRoom) {
    Row(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()) {

        Column (modifier = Modifier.weight(2f)){
            Text(text = menuItem.title,
                    textAlign = TextAlign.Left,
                fontSize=16.sp,
                fontWeight = FontWeight.Bold

               )
            Text(text = menuItem.description,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top=10.dp))
            Text(text ="$${menuItem.price}",
                color = Color.Gray,
                modifier = Modifier.padding(top=10.dp))
        }


        GlideImage(
            model =  menuItem.image,
            contentDescription = menuItem.title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .requiredSize(width = 120.dp, height = 150.dp)
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))

        )

    }
    HorizontalDivider(
        modifier = Modifier
            .padding(vertical = 2.dp),
        color = MaterialTheme.colorScheme.outline,
        thickness = 1.dp
    )
}


