package com.barisscebeci.tadal.presentation.ui.screen.auth.register

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.barisscebeci.tadal.presentation.ui.screen.auth.RegisterPage
import kotlinx.coroutines.launch

@Composable
fun RegisterPageMethod(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    RegisterPage(
        navController = navController,
        onRegisterClick = { email, username, password ->
            scope.launch {
                try {
                    HandleRegister(email, username, password, navController)
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "Bir hata oluştu.${e.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    )
}