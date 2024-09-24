package com.barisscebeci.tadal.presentation.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barisscebeci.tadal.ui.theme.KanitFont
import com.barisscebeci.tadal.ui.theme.SepetRenk
import com.barisscebeci.tadal.presentation.ui.assets.LottiePopup
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserAccount(
    navController: NavController,
    onLoginClick: (usernameOrEmail: String, password: String) -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    var usernameOrEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showLottie by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                    Text(
                        text = "Kullanıcı Girişi",
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = KanitFont,
                        fontSize = 24.sp,
                        color = SepetRenk,

                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Geri")
                    }
                },
                actions = {
                    Spacer(modifier = Modifier.width(48.dp))
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp), // Genel padding ekleyelim
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // İçeriği ortalayalım
        ) {

            Text(
                text = "Hoşgeldiniz",
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                fontFamily = KanitFont,
                color = SepetRenk,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            // Email TextField
            OutlinedTextField(
                value = usernameOrEmail,
                onValueChange = { usernameOrEmail = it },
                label = { Text("Kullanıcı Adı veya E-posta") },
                placeholder = { Text("Kullanıcı adı veya e-posta giriniz") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            )
            Spacer(modifier = Modifier.height(8.dp))


            // Şifre TextField
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Şifre") },
                placeholder = { Text("Şifrenizi girin") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Giriş Yap Butonu
            Button(
                onClick = {
                    onLoginClick(usernameOrEmail, password)
                    showLottie = true

                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(SepetRenk),
            ) {
                Text("Giriş Yap")
            }
            if (showLottie) {
                LottiePopup(
                    "login.json",
                    onDismiss = { showLottie = false }
                )
                LaunchedEffect(Unit) {
                    delay(4000)
                    navController.navigate("userProfile")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Şifremi Unuttum ve Üye Ol Linkleri
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Şifremi Unuttum",
                    modifier = Modifier.clickable { onForgotPasswordClick() },
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Üye Ol",
                    modifier = Modifier.clickable { onRegisterClick() },
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

