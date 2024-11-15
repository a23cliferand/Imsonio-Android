package com.example.myapplication.network
import com.example.insomnioproject.ScriptsManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


import retrofit2.http.GET

val BASE_URL = "http://10.0.2.2:3000"
//val retrofit = Retrofit.Builder()
//    .baseUrl(BASE_URL)
//    .addConverterFactory(GsonConverterFactory.create())
//    .build()
//val apiService = retrofit.create(Interface::class.java)

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val apiService = retrofit.create(Interface::class.java)

interface Interface {
    //GET tots els productes
    @GET("/getInfo")
    suspend fun getScriptsData(): List<ScriptsManager.Script>

}

