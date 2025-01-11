package com.example.pokergameui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokergameui.model.BaseResponse
import com.example.pokergameui.model.LoginRequest
import com.example.pokergameui.model.Protocol
import com.example.pokergameui.model.TCPConnectionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SigninViewModel : ViewModel() {
    fun signIn(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val msg = Protocol.encode(1, Protocol.LOGIN, LoginRequest(username, password)) ?: return@launch
            if(!TCPConnectionManager.send(msg)) {
                return@launch
            }

            val response = TCPConnectionManager.receive() ?: return@launch
            val status = Protocol.decode<BaseResponse>(response)
            if (status.header.packetLen == 0.toShort()) {
                return@launch
            }

            if (status.payload?.res == Protocol.LOGIN) {
                Log.d("handle-signin", "login success")
            } else {
                Log.e("handle-signin", "login failed")
            }
        }
    }
}