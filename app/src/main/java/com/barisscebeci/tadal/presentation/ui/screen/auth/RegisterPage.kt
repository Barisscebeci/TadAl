package com.barisscebeci.tadal.presentation.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barisscebeci.tadal.presentation.ui.assets.LottiePopup
import com.barisscebeci.tadal.ui.theme.KanitFont
import com.barisscebeci.tadal.ui.theme.SepetRenk
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(
    navController: NavController,
    onRegisterClick: (email: String, username: String, password: String) -> Unit
) {
    // Kayıt sayfası içeriği buraya gelecek
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showLottie by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                    Text(
                        text = "Kayıt Ol",
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = KanitFont,
                        fontSize = 24.sp,
                        color = SepetRenk,

                        )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Geri")
                    }
                },
                actions = {
                    Spacer(modifier = Modifier.width(48.dp))
                }
            )
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hoşgeldiniz",
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                fontFamily = KanitFont,
                color = SepetRenk,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-posta") },
                placeholder = { Text("E-posta adresinizi girin") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Kullanıcı Adı") },
                placeholder = { Text("Kullanıcı adınızı girin") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            )
            Spacer(modifier = Modifier.height(8.dp))

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

            Button(
                onClick = {
                    onRegisterClick(email, username, password)
                    showLottie = true
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(SepetRenk),
            ) {
                Text("Kayıt Ol")
            }
            if (showLottie) {
                LottiePopup(
                    "register.json",
                    onDismiss = { showLottie = false }
                )
                LaunchedEffect(Unit) {
                    delay(4000)
                    navController.navigate("userProfile")
                }
            }
        }
    }
}