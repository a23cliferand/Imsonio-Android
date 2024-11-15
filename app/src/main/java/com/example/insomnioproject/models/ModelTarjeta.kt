package com.example.insomnioproject.models

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.insomnioproject.ScriptsManager

@Composable
fun scriptInfo(scrip: ScriptsManager.Script) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = scrip.name + " Status: " + scrip.status, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                if(scrip.status == "ON") {
                    Button(onClick = { /* Acción del botón 1 */ } ) {
                        Text("Stop")
                    }
                } else {
                    Button(onClick = { /* Acción del botón 1 */ }) {
                        Text("Start")
                    }
                }

                Button(onClick = { /* Acción del botón 3 */ }) {
                    Text("Log")
                }
                Button(onClick = { /* Acción del botón 3 */ }) {
                    Text("Error")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun scriptInfo() {
    val script = ScriptsManager.Script(1, "Script 1", "ON")
    scriptInfo(script)
}