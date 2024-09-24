package com.barisscebeci.tadal.presentation.ui.screen.auth.firebase

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.barisscebeci.tadal.presentation.ui.screen.auth.UserSession
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@Composable
fun AppEntryPoint() {
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val currentUser = auth.currentUser

    LaunchedEffect(currentUser) {
        if (currentUser != null && UserSession.username == null) {
            try {
                val docRef = firestore.collection("users").document(currentUser.uid)
                val snapshot = docRef.get().await()
                val storedUsername = snapshot.getString("username") ?: ""

                UserSession.username = storedUsername
            } catch (e: Exception) {
                Toast.makeText(context, "Kullanıcı bilgileri alınamadı.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}