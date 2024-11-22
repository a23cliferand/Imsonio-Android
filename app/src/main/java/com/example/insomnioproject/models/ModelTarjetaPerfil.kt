package com.example.insomnioproject.models

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.graphics.*
import com.example.insomnioproject.PerfilsManager

@Composable
fun perfilInfo(perfil: PerfilsManager.Perfil, navController: NavController) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = perfil.label, style = MaterialTheme.typography.bodyLarge)
            Text(text = perfil.host, style = MaterialTheme.typography.bodyLarge)
            Text(text = perfil.port.toString(), style = MaterialTheme.typography.bodyLarge)
            if (perfil.isDefault == true) {
                Button(
                    onClick = { perfil.isDefault == false; Log.i("perfil", perfil.isDefault.toString()) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(76, 199, 64))
                ) {
                    Text("true")
                }
            } else {
                Button(
                    onClick = { perfil.isDefault == true; Log.i("perfil", perfil.isDefault.toString()) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("false")
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun perfilInfoPreview() {
    val perfil = PerfilsManager.Perfil("1", "NOMBRE", "192.168.1.1", 80, true)
    val navController = rememberNavController()
    perfilInfo(perfil, navController)
}