package com.example.insomnioproject

object ScriptsManager {

    data class Script(
        val id: String,
        val name: String,
        val status: String,
        val logs: List<String>,
        val errors: List<String>
    )
}