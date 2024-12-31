package com.example.pokergameui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokergameui.ui.theme.PokerGameUITheme

class StartActivity : ComponentActivity() {
    private var tcpService: TcpService? = null
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            tcpService = (service as TcpService.LocalBinder).getService()
            tcpService?.sendMessage("Hello from MainActivity!")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            tcpService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokerGameUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StartActivityBody(
                        modifier = Modifier.padding(innerPadding),
                        handleSignIn = {
                            startActivity(Intent(this, SignInActivity::class.java))
                        },
                        handleSignUp = {
                            startActivity(Intent(this, SignUpActivity::class.java))}
                    )
                }
            }
        }

        bindService(Intent(this, TcpService::class.java), connection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }
}

@Composable
fun StartActivityBody(modifier: Modifier = Modifier, handleSignIn: () -> Unit = {}, handleSignUp: () -> Unit = {}) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.start_screen_bg),
            contentDescription = "App background",
            modifier = Modifier.fillMaxSize().scale(1.15f)
        )
        Column(modifier = modifier.fillMaxWidth().padding(250.dp, 50.dp)) {
            Button(
                onClick = handleSignIn,
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = "Sign In", color = Color.Black)
            }
            Button(
                onClick = handleSignUp,
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = "Sign Up", color = Color.Black)
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:parent=pixel_8,orientation=landscape",
)
@Composable
fun GreetingPreview() {
    PokerGameUITheme {
        StartActivityBody()
    }
}