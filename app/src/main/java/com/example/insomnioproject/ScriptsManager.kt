package com.example.insomnioproject

object ScriptsManager {

    data class Script(
        val id: Int,
        val name: String,
        val status: String,
        val logs: List<String>
    )
}