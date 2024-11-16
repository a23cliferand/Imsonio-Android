package com.example.insomnioproject

import WebSocketClient
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.insomnioproject.ui.theme.InsomnioProjectTheme
import com.example.myapplication.network.BASE_URL
import com.example.myapplication.network.apiService
import kotlinx.coroutines.runBlocking

var scriptList by mutableStateOf(loadScripts())

class MainActivity : ComponentActivity(), WebSocketClient.WebSocketEventListener {

    private lateinit var webSocketClient: WebSocketClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webSocketClient = WebSocketClient("ws://" + BASE_URL, this)
        webSocketClient.connect()

        setContent {
            InsomnioProjectTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "general") {
                    composable("general") {
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

    override fun onOpen() {
        runOnUiThread {
            Toast.makeText(this, "Conexión establecida", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMessage(message: String) {
        Log.i("Mensaje", message)
        runOnUiThread {
            Log.i("Mensaje", message)
            scriptList = loadScripts()
        }
    }

    override fun onClosing(code: Int, reason: String) {
        runOnUiThread {
            Toast.makeText(this, "Cerrando conexión: $reason", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClosed(code: Int, reason: String) {
        runOnUiThread {
            Toast.makeText(this, "Conexión cerrada: $reason", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onError(error: Throwable) {
        runOnUiThread {
            Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        webSocketClient.close()
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