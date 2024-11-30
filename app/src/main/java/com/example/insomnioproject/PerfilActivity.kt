package com.example.insomnioproject

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.insomnioproject.models.scriptInfo




import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import com.example.insomnioproject.models.perfilInfo
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp



@Composable
fun PerfilActivityContent(perfilList: List<PerfilsManager.Perfil>, navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Button(onClick = { navController.navigate("general") }) {
                Text("Volver al General")
            }
            Button(
                onClick = { navController.navigate("createPerf") }
            ) {
                Text("Create Perfil")
            }
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Text(text = "Label", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Host", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Port", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Default", style = MaterialTheme.typography.bodyLarge)
                }
                LazyColumn {
                    items(perfilList) { perfil ->
                        perfilInfo(perfil, navController)
                    }
                }
            }
        }
    )
}