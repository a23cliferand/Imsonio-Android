package com.example.insomnioproject

import android.util.Log
import com.example.myapplication.network.BASE_URL
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter

object SocketManager {
    var socket: Socket? = null
    var connected=false
    init {
        socket = IO.socket("http://" + BASE_URL)
        println("Socket initialized")
    }

    private val changeCancionicas = Emitter.Listener { args ->
        scriptList = loadScripts()
        Log.i("chocho", scriptList.toString())
    }

    fun connectSocket() {
        socket?.connect()
        println("Socket connected")
        connected =true
        socket?.on(Socket.EVENT_CONNECT) { socket?.on("scripts", changeCancionicas) }
    }


    fun disconnectSocket() {
        socket?.disconnect()
        println("Socket disconnected")
        connected =false
    }
}