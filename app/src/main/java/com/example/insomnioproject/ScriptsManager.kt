package com.example.insomnioproject

import com.google.gson.annotations.JsonAdapter


object ScriptsManager {

    data class Script(
        val id: Int,
        val name: String,
        val status: String
    )
}