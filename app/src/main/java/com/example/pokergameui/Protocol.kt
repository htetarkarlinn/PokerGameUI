package com.example.pokergameui

import android.util.Log
import com.daveanthonythomas.moshipack.MoshiPack
import java.nio.ByteBuffer
import java.nio.ByteOrder

data class Header(val packetLen: Short, val protocolVer: Byte, val packetType: Short)
data class Packet<T>(val header: Header, val payload: T)
data class LoginRequest(val user: String, val pass: String)
data class BaseResponse(val res : Int)

class Protocol {
    companion object {
        const val LOGIN: Short = 100
        const val SIGNUP: Short = 200

        inline fun <reified T> encode(
            protocolVersion: Int,
            packetType: Int,
            payload: T
        ): ByteArray? {
            return try {
                val pVer = protocolVersion.toByte()
                val pType = packetType.toShort()
                val packed = MoshiPack.pack(payload).readByteArray()
                val packetLen: Short = (5 + packed.size).toShort()
                val buffer = ByteBuffer.allocate(packetLen.toInt()).order(ByteOrder.BIG_ENDIAN)
                buffer.putShort(packetLen)
                buffer.put(pVer)
                buffer.putShort(pType)
                buffer.put(packed)
                buffer.array()
            } catch (e: Exception) {
                Log.e("Protocol", "Encode error: ${e.localizedMessage}")
                null
            }
        }

        inline fun <reified T> decode(data: ByteArray): Packet<T>? {
            return try {
                val buffer = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN)
                val packetLen = buffer.getShort()
                val protocolVer = buffer.get()
                val packetType = buffer.getShort()
                // get the payload by skipping the header = 5 bytes
                val payload = MoshiPack.unpack<T>(data.copyOfRange(5, data.size))
                Packet(Header(packetLen, protocolVer, packetType), payload)
            } catch (e: Exception) {
                Log.e("Protocol", "Decode error: ${e.localizedMessage}")
                null
            }
        }
    }
}
