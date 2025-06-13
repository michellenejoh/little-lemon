package com.littlelemon.littlelemon

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationComposable (navController: NavHostController, sharedPreferences: SharedPreferences, menuItemsLiveData: LiveData<List<MenuItemRoom>>) {

    NavHost(
        navController = navController,
        startDestination = if (sharedPreferences.all.isEmpty()) Onboarding.route else Home.route
    ) {
        composable(Home.route) {
            Home(navController = navController, menuItemsLiveData =menuItemsLiveData )
        }
        composable(Profile.route) {
            Profile(sharedPreferences = sharedPreferences, navController = navController)
        }
        composable(Onboarding.route) {
            Onboarding(sharedPreferences = sharedPreferences, navController = navController)
        }

    }
}