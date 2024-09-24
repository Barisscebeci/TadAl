package com.barisscebeci.tadal.presentation.ui.screen.auth.login

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.barisscebeci.tadal.presentation.ui.screen.auth.UserAccount
import kotlinx.coroutines.launch

@Composable
fun UserAccountMethod(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    UserAccount(
        navController = navController,
        onLoginClick = { usernameOrEmail, password ->
            // Login işlemleri
            scope.launch {
                try {
                    HandleLogin(usernameOrEmail, password, navController)
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "${e.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        },
        onRegisterClick = {
            navController.navigate("register")
        },
        onForgotPasswordClick = {
        }

    )
}

