package com.example.insomnioproject

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.example.insomnioproject.models.scriptInfo




@Composable
fun GeneralActivityContent(scriptList: List<ScriptsManager.Script>) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                    Log.i("Carrefour", scriptList.toString())
                    scriptList.forEach { script ->
                        scriptInfo(script)
                        Log.i("ScriptList", com.example.insomnioproject.scriptList.toString())
                    }
                }
            }
        )
}