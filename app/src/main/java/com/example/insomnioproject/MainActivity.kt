package com.example.insomnioproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.insomnioproject.SocketManager.connectSocket
import com.example.insomnioproject.ui.theme.InsomnioProjectTheme
import com.example.myapplication.network.apiService
import kotlinx.coroutines.runBlocking

var scriptList by mutableStateOf(loadScripts())

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectSocket()

        setContent {
            InsomnioProjectTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "general") {
                    composable("general") {
                        if (scriptList.isEmpty()) {
                            Log.i("Lidl", "No hi ha scripts")
                        } else {
                            GeneralActivityContent(scriptList, navController)
                        }
                    }
                    composable("log/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")
                        LogActivityContent(scriptList, id, navController)
                    }
                    composable("error/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")
                        ErrorActivityContent(scriptList, id, navController)
                    }
                }
            }
        }
    }

}

fun loadScripts(): List<ScriptsManager.Script> {
    var scriptList: List<ScriptsManager.Script> = emptyList()

    runBlocking {
        try {
            scriptList = apiService.getScriptsData()
            Log.i("inicio", scriptList.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Error", e.toString())
        }
    }
    return scriptList
}