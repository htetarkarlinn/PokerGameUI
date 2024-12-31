package com.example.pokergameui

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class TcpService : Service() {

    private val tcpClient = TcpClient("127.0.0.1", 8080)

    inner class LocalBinder : Binder() {
        fun getService(): TcpService = this@TcpService
    }
    


    override fun onCreate() {
        super.onCreate()
        tcpClient.connect()
    }

    fun sendMessage(message: String) {
        tcpClient.sendMessage(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        tcpClient.disconnect()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
