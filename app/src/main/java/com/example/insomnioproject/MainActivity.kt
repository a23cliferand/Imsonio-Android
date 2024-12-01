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
import androidx.room.Room
import com.example.insomnioproject.SocketManager.connectSocket
import com.example.insomnioproject.rooms.AppDatabase
import com.example.insomnioproject.rooms.PerfilDao
import com.example.insomnioproject.ui.theme.InsomnioProjectTheme
import com.example.myapplication.network.apiService
import kotlinx.coroutines.runBlocking

var scriptList by mutableStateOf(loadScripts())


var perfilList: List<PerfilsManager.Perfil> = listOf()

class MainActivity : ComponentActivity() {

    private lateinit var perfilDao: PerfilDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectSocket()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database"
        ).build()

        Log.i("Lidl", "Database: $db")

        perfilDao = db.perfilDao()

        runBlocking {
            perfilList = perfilDao.getAllPerfiles().map {
                PerfilsManager.Perfil(it.id, it.label, it.host, it.port, it.isDefault)
            }
            Log.i("Lidl", "Perfiles: $perfilList")
        }

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
                    composable("perfils") { backStackEntry ->
                        PerfilActivityContent(perfilList, navController)
                    }
                    composable("createPerf") { backStackEntry ->
                        CreatePerfilActivityContent(navController)
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

    private fun updateDatabaseWithPerfiles(perfiles: List<PerfilsManager.Perfil>) {
        runBlocking {
            perfilDao.deleteAll()
            perfilDao.insertAll(perfiles.map {
                PerfilsManager.Perfil(it.id, it.label, it.host, it.port, it.isDefault)
            })
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