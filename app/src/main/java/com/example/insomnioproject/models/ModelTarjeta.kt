package com.example.insomnioproject.models

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.insomnioproject.ScriptsManager
import com.example.insomnioproject.scriptList
import com.example.myapplication.network.apiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun scriptInfo(scrip: ScriptsManager.Script, navController: NavController) {
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
                    Button(onClick = { stopScript(scrip.name) } ) {
                        Text("Stop")
                    }
                } else {
                    Button(onClick = { startScript(scrip.name)  } ) {
                        Text("Start")
                    }
                }

                Button(onClick = {
                    if(scrip.logs.isEmpty()){
                        Toast.makeText(navController.context, "No logs", Toast.LENGTH_SHORT).show()
                    } else{
                        navController.navigate("log/${scrip.id}")
                    }
                })
                {
                    Text("Log")
                }
                Button(onClick = {
                    if(scrip.errors.isEmpty()){
                        Toast.makeText(navController.context, "No errors", Toast.LENGTH_SHORT).show()
                    } else{
                        navController.navigate("error/${scrip.id}")
                    }
                })
                {
                    Text("Errors")
                }
            }
        }
    }
}


private fun startScript(name: String) {
    Log.i("Script", "Starting script $name")
    CoroutineScope(Dispatchers.IO).launch {
        scriptList = apiService.startScript(name)
    }
}

private fun stopScript(name: String) {
    Log.i("Script", "Starting script $name")
    CoroutineScope(Dispatchers.IO).launch {
        scriptList = apiService.stopScript(name)
    }
}



@Preview(showBackground = true)
@Composable
fun scriptInfoPreview() {
    val script = ScriptsManager.Script("1", "Script 1", "ON", listOf("Script 1 description"), listOf("Script 1 description"))
    val navController = rememberNavController()
    scriptInfo(script, navController)
}