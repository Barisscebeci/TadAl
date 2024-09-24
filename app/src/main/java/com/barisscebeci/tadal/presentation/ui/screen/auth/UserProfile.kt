package com.barisscebeci.tadal.presentation.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.barisscebeci.tadal.R
import com.barisscebeci.tadal.presentation.ui.assets.LottiePopup
import com.barisscebeci.tadal.ui.theme.KanitFont
import com.barisscebeci.tadal.ui.theme.SepetRenk
import com.barisscebeci.tadal.ui.theme.turuncuRenk
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfile(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            email = currentUser.email ?: ""
            val docRef = firestore.collection("users").document(currentUser.uid)
            val snapshot = docRef.get().await()
            username = snapshot.getString("username") ?: ""
            address = snapshot.getString("address") ?: ""
            phone = snapshot.getString("phone") ?: ""
        } else {
            navController.navigate("userAccount") {
                popUpTo("userProfile") { inclusive = true }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Profilim",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = KanitFont,
                        fontSize = 24.sp,
                        color = SepetRenk,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Image(
                            painter = painterResource(id = R.drawable.kapat_resim),
                            contentDescription = "Kapat"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        auth.signOut()
                        navController.navigate("userAccount") {
                            popUpTo("userProfile") { inclusive = true }
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.cikis_resim),
                            contentDescription = "Çıkış",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            ProfileContent(
                username = username,
                email = email,
                address = address,
                phone = phone,
                paddingValues = paddingValues,
                navController = navController
            )
        }
    )
}

@Composable
fun ProfileContent(username: String, email: String, address: String, phone: String, paddingValues: PaddingValues,navController: NavController) {
    var showLottie by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Profile Image Placeholder
        AsyncImage(
            model = "",
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp)
                .background(SepetRenk, shape = RoundedCornerShape(60.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Welcome Text
        Text(
            text = "Hoşgeldin, $username",
            fontFamily = KanitFont,
            fontSize = 28.sp,
            color = turuncuRenk,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Profile Details Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                ProfileInfoRow(label = "E-posta", value = email)
                ProfileInfoRow(label = "Adres", value = address)
                ProfileInfoRow(label = "Telefon", value = phone)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = {
                FirebaseAuth.getInstance().signOut()
                showLottie = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(SepetRenk),
        ) {
            Text(
                text = "Çıkış Yap",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        if (showLottie) {
            LottiePopup(
                "logout.json",
                onDismiss = { showLottie = false }
            )
            LaunchedEffect(Unit) {
                delay(4000)
                navController.navigate("userAccount") {
                    popUpTo("userProfile") { inclusive = true }
                }
            }
        }
    }
}

@Composable
fun ProfileInfoRow(label: String, value: String) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = SepetRenk
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 4.dp))
    }
}