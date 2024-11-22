package com.example.insomnioproject.rooms

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.insomnioproject.PerfilsManager

@Dao
interface PerfilDao {
    @Insert
    suspend fun insertAll(perfiles: List<PerfilsManager.Perfil>)

    @Query("DELETE FROM perfiles")
    suspend fun deleteAll()

    @Query("SELECT * FROM perfiles")
    suspend fun getAllPerfiles(): List<PerfilsManager.Perfil>
}