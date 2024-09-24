package com.barisscebeci.tadal.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barisscebeci.tadal.ui.theme.AlertContainerRenk
import com.barisscebeci.tadal.ui.theme.AlertTitleRenk
import com.barisscebeci.tadal.ui.theme.SepetRenk

@Composable
fun ShowLoginPrompt(
    onDismiss: () -> Unit,
    onLogin: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Giriş Yapmanız Gerekiyor", style = TextStyle(fontSize = 20.sp)) },
        text = { Text(text = "Bu işlemi yapabilmek için giriş yapmanız gerekmektedir.") },
        confirmButton = {
            TextButton(onClick = onLogin) {
                Text(
                    text = "Giriş Yap",
                    color = Color.Black,
                )

            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    "İptal",
                    color = Color.Black,
                )
            }
        },
        containerColor = AlertContainerRenk,
        titleContentColor = AlertTitleRenk,
        textContentColor = Color.Black,
    )
}