package com.example.pokergameui

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.*
import java.net.Socket
import java.net.SocketException

class TCPClient(private val host: String, private val port: Int) {
    private var socket: Socket? = null
    private var outputStream: OutputStream? = null
    private var inputStream: InputStream? = null

    // Connect to the server
    suspend fun connect(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                socket = Socket(host, port)
                outputStream = socket?.getOutputStream()
                inputStream = socket?.getInputStream()
                Log.d("TCPClient", "Connected to server: $host:$port")
                true
            } catch (e: Exception) {
                Log.e("TCPClient", "Connection error: ${e.localizedMessage}")
                false
            }
        }
    }

    // Send data to the server
    suspend fun send(data: ByteArray): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                if (outputStream == null) {
                    Log.e("TCPClient", "OutputStream is null, cannot send data")
                    false
                }

                if (socket == null || socket?.isClosed == true) {
                    Log.e("TCPClient", "Socket is closed or null, cannot send data")
                    false
                }
                Log.d("TCPClient", "Sending data of size: ${data.size} bytes")
                outputStream?.write(data, 0, data.size)
                outputStream?.flush()
                Log.d("TCPClient", "Data sent successfully")
                true
            } catch (e: IOException) {
                Log.e("TCPClient", "IO error while sending data: ${e.localizedMessage}")
                e.printStackTrace()
                false
            } catch (e: SocketException) {
                Log.e("TCPClient", "Socket error while sending data: ${e.localizedMessage}")
                e.printStackTrace()
                false
            } catch (e: Exception) {
                Log.e("TCPClient", "Unexpected error while sending data: ${e.localizedMessage}")
                e.printStackTrace()
                false
            }
        }
    }

    // Receive data from the server
    suspend fun receive(): ByteArray? {
        return withContext(Dispatchers.IO){
        try {
            val buffer = ByteArray(4096)
            val bytesRead = inputStream?.read(buffer)
            if (bytesRead == -1) {
                Log.e("TCPClient", "End of stream reached")
                return@withContext null
            }
            Log.d("TCPClient", "Received data of size: $bytesRead bytes")
            buffer.copyOf(bytesRead!!)
        } catch (e: Exception) {
            Log.e("TCPClient", "Receive error: ${e.localizedMessage}")
            null
        }}
    }

    // Close the connection
    suspend fun disconnect() {
        withContext(Dispatchers.IO) {
            try {
                outputStream?.close()
                inputStream?.close()
                socket?.close()
                Log.d("TCPClient", "Disconnected from server")
            } catch (e: Exception) {
                Log.e("TCPClient", "Disconnect error: ${e.localizedMessage}")
            }
        }
    }
}

object TCPConnectionManager {
    private var tcpClient: TCPClient? = null

    suspend fun connect(host: String, port: Int): Boolean {
        return withContext(Dispatchers.IO) {
            if (tcpClient == null) {
                tcpClient = TCPClient(host, port)
            }
            tcpClient?.connect() ?: false
        }
    }

    suspend fun send(data: ByteArray): Boolean {
        return withContext(Dispatchers.IO) {
            tcpClient?.send(data) ?: false
        }
    }

    suspend fun receive(): ByteArray? {
        return withContext(Dispatchers.IO) {
            tcpClient?.receive()
        }
    }

    suspend fun disconnect() {
        withContext(Dispatchers.IO) {
            tcpClient?.disconnect()
            tcpClient = null
        }
    }
}
