package com.example.pokergameui

import android.util.Log
import com.daveanthonythomas.moshipack.MoshiPack
import org.junit.Test
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ProtocolTest {
    @Test
    fun testEncodePacket() {
        val encodedPacket = Protocol.encode(1, 100, LoginRequest("admin", "admin"))
        assert(encodedPacket != null)
        if (encodedPacket != null) {
            val decodedPacket = Protocol.decode<LoginRequest>(encodedPacket.array())
            assert(decodedPacket != null)
            assert(decodedPacket?.header?.packetType == 100.toShort())
            assert(decodedPacket?.payload?.user == "admin")
            assert(decodedPacket?.payload?.pass == "admin")
        }
    }
}