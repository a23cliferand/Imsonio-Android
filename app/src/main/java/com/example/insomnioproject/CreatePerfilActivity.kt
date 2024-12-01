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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp



@Composable
fun CreatePerfilActivityContent(navController: NavController) {
    var labelText by remember { mutableStateOf("") }
    var hostText by remember { mutableStateOf("") }
    var portText by remember { mutableStateOf("") }
    var isDefaultText by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Button(onClick = { navController.navigate("perfils") }) {
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
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = labelText,
                    onValueChange = { labelText = it },
                    label = { Text("Label") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = hostText,
                    onValueChange = { hostText = it },
                    label = { Text("Host") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = portText,
                    onValueChange = { portText = it },
                    label = { Text("Port") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Checkbox(
                    checked = false,
                    onCheckedChange = {isDefaultText = it}
                )
                Text("Default")
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        val newPerfil = PerfilsManager.Perfil(
                            id = (perfilList.size + 1).toString(),
                            label = labelText,
                            host = hostText,
                            port = portText.toInt(),
                            isDefault = isDefaultText
                        )

                        perfilList = perfilList + newPerfil
                        navController.navigate("perfils")
                    }
                ) {
                    Text("Save Perfil")
                }
            }
        }
    )
}