package com.littlelemon.littlelemon

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.littlelemon.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                contentType = ContentType.Text.Plain,
                json = Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }


    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").fallbackToDestructiveMigration().build()
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val sharedPreferences = getSharedPreferences("LittleLemon", MODE_PRIVATE)
        val menuItemsLiveData = database.menuItemDao().getAll()

        setContent {
            LittleLemonTheme {

               MyNavigation(sharedPreferences=sharedPreferences, menuItemsLiveData=menuItemsLiveData)

            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                val menuItemsNetwork = fetchMenu()
                val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
                database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
            }
        }

    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        return httpClient
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetwork>()
            .menu
    }

}

@Composable
fun MyNavigation(sharedPreferences: SharedPreferences,  menuItemsLiveData: LiveData<List<MenuItemRoom>>) {
    val navController = rememberNavController()
    NavigationComposable(navController = navController, sharedPreferences = sharedPreferences,  menuItemsLiveData=menuItemsLiveData)
}


