package com.example.pokergameui

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.Socket
import java.net.SocketTimeoutException

class TcpClient(
    private val serverAddress: String,
    private val serverPort: Int,
    private val timeout: Int = 5000 // Optional timeout in milliseconds
) {
    private var socket: Socket? = null
    private var output: OutputStream? = null
    private var input: BufferedReader? = null

    /**
     * Connect to the server
     */
    fun connect(): Boolean {
        return try {
            socket = Socket(serverAddress, serverPort).apply {
                soTimeout = timeout
            }
            output = socket?.getOutputStream()
            input = BufferedReader(InputStreamReader(socket?.getInputStream()))
            true
        } catch (e: Exception) {
            println("Error connecting to server: ${e.message}")
            false
        }
    }

    /**
     * Send a message to the server
     */
    fun sendMessage(message: String): Boolean {
        return try {
            if (output != null) {
                output?.write("$message\n".toByteArray())
                output?.flush()
                true
            } else {
                println("Error: Not connected to the server.")
                false
            }
        } catch (e: Exception) {
            println("Error sending message: ${e.message}")
            false
        }
    }

    /**
     * Receive a response from the server
     */
    fun receiveMessage(): String? {
        return try {
            input?.readLine()
        } catch (e: SocketTimeoutException) {
            println("Timeout while waiting for response.")
            null
        } catch (e: Exception) {
            println("Error receiving message: ${e.message}")
            null
        }
    }

    /**
     * Disconnect from the server
     */
    fun disconnect() {
        try {
            input?.close()
            output?.close()
            socket?.close()
        } catch (e: Exception) {
            println("Error closing connection: ${e.message}")
        }
    }
}
