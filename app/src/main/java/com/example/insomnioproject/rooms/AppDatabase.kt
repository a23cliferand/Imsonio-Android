package com.example.insomnioproject.rooms

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.insomnioproject.PerfilsManager

@Database(entities = [PerfilsManager.Perfil::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun perfilDao(): PerfilDao

    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "database"
        ).build()
    }
}