package com.example.pokergameui.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun InputLabel(
    modifier: Modifier = Modifier,
    value: TextFieldValue = TextFieldValue(""),
    onValueChange : (TextFieldValue)->Unit = {},
    label: String = "",
    placeholder: String = "",
    hidden: Boolean = false
) {
    Column() {
        Text(
            text = "${label}:",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 16.sp
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            visualTransformation = if (hidden) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = modifier.border(
                BorderStroke(1.dp, Color.DarkGray),
                shape = RoundedCornerShape(10.dp)
            ),
            shape = RoundedCornerShape(1.dp),
            placeholder = { Text(placeholder, fontSize = 16.sp) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InputLabelPreview() {
//    InputLabel(Modifier, "Email")
}


