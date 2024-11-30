package com.example.insomnioproject

import androidx.room.Entity
import androidx.room.PrimaryKey

object PerfilsManager {

    @Entity(tableName = "perfiles")
    data class Perfil(
        @PrimaryKey val id: String,
        val label: String,
        val host: String,
        val port: Int,
        var isDefault: Boolean
    )
}