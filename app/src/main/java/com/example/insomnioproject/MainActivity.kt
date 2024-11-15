package com.example.insomnioproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.insomnioproject.network.WebSocketClient
import com.example.insomnioproject.ui.theme.InsomnioProjectTheme
import com.example.myapplication.network.apiService
import kotlinx.coroutines.runBlocking


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WebSocketClient.connect(this@MainActivity)
        enableEdgeToEdge()
        setContent {
            InsomnioProjectTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "general") {
                    composable("general") {
                        var scriptList: List<ScriptsManager.Script> = loadScripts()

                        if(scriptList.isEmpty()){
                            Log.i("Lidl", "No hi ha scripts")
                        }
                        else {
                            GeneralActivityContent(scriptList)
                        }
                    }

                }
            }
        }
    }

    private fun loadScripts(): List<ScriptsManager.Script> {
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
}