package com.example.pokergameui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daveanthonythomas.moshipack.MoshiPack
import com.example.pokergameui.ui.theme.InputLabel
import com.example.pokergameui.ui.theme.Blue
import com.example.pokergameui.ui.theme.Dark
import com.example.pokergameui.ui.theme.PokerGameUITheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.BufferedSource
import kotlin.reflect.jvm.internal.impl.builtins.UnsignedType

class SignInActivity : ComponentActivity() {
    private fun handle_signin(username: String, password: String): Unit {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val msg = Protocol.encode(1, 100, LoginRequest(username, password))
                if (msg == null) {
                    Log.e("handle-signin", "failed to encode message")
                    return@launch
                }

                val success = withContext(Dispatchers.IO) {
                    TCPConnectionManager.send(msg)
                }

                if (!success) {
                    Log.e("handle-signin", "failed to send message")
                    return@launch
                }

                val response = withContext(Dispatchers.IO) {
                    TCPConnectionManager.receive()
                }

                if (response == null) {
                    Log.e("handle-signin", "failed to receive response")
                    return@launch
                }

                val status = Protocol.decode<BaseResponse>(response)

                if (status == null) {
                    Log.e("handle-signin", "failed to decode response")
                    return@launch
                }

                val header = status.header
                val payload = status.payload

                if (header == null || payload == null) {
                    Log.e("handle-signin", "failed to decode response")
                    return@launch
                }

                if (payload.res == 101) {
                    Log.d("handle-signin", "login success")
                    return@launch
                } else {
                    Log.e("handle-signin", "login failed")
                }


            } catch (e: Exception) {
                Log.e("handle-signin", "error ${e.localizedMessage}")
            }
        }
        // Send it to servver then get response and check response
        startActivity(Intent(this@SignInActivity, LobbyActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokerGameUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        SignInHeader()
                        Spacer(modifier = Modifier.height(24.dp))
                        SignInForm(
                            modifier = Modifier.fillMaxWidth(),
                            handleSignIn = ::handle_signin,
                            handleForgetPassWord = {
                                startActivity(
                                    Intent(
                                        this@SignInActivity,
                                        LobbyActivity::class.java
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SignInBody(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        SignInHeader()
        Spacer(modifier = Modifier.height(24.dp))
        SignInForm(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun SignInHeader(modifier: Modifier = Modifier) {
    Surface(color = Dark, contentColor = Color.White) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sign in to your Account",
                fontSize = 28.sp,
                lineHeight = 36.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Don't have an account? ",
                    fontSize = 14.sp,
                    color = Color.White
                )
                Text(
                    text = "Sign up",
                    color = Blue,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        // Navigate to Sign-Up screen logic

                    }
                )
            }
        }
    }
}


@Composable
fun SignInForm(
    modifier: Modifier = Modifier,
    handleSignIn: (String, String) -> Unit = { _, _ -> },
    handleForgetPassWord: () -> Unit = {}
) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }


    Column(
        modifier = modifier
            .padding(vertical = 16.dp)
    ) {
        InputLabel(
            onValueChange = { username = it },
            value = username,
            label = "Email",
            placeholder = "Enter your email",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        InputLabel(
            label = "Password",
            value = password,
            onValueChange = { password = it },
            placeholder = "Enter your password",
            hidden = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Blue),
            onClick = { handleSignIn(username.text, password.text) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Login",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Forgot Password?",
            color = Blue,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable {
                    handleForgetPassWord()
                }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8")
@Composable
fun SignInPreview() {
    PokerGameUITheme {
        SignInBody()
    }
}
