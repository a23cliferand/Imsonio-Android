package com.example.myapplication.network
import com.example.insomnioproject.ScriptsManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


import retrofit2.http.GET
import retrofit2.http.Path

val BASE_URL = "10.0.2.2:3000"
//val retrofit = Retrofit.Builder()
//    .baseUrl(BASE_URL)
//    .addConverterFactory(GsonConverterFactory.create())
//    .build()
//val apiService = retrofit.create(Interface::class.java)

val retrofit = Retrofit.Builder()
    .baseUrl("http://" + BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val apiService = retrofit.create(Interface::class.java)

interface Interface {
    @GET("/getInfo")
    suspend fun getScriptsData(): List<ScriptsManager.Script>
    @GET("/runScript/{name}")
    suspend fun startScript(@Path("name") name: String): List<ScriptsManager.Script>
    @GET("/stopScript/{name}")
    suspend fun stopScript(@Path("name") name: String): List<ScriptsManager.Script>
}

