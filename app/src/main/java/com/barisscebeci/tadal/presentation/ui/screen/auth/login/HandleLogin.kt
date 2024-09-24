package com.barisscebeci.tadal.presentation.ui.screen.auth.login

import android.util.Log
import androidx.navigation.NavController
import com.barisscebeci.tadal.presentation.ui.screen.auth.UserSession
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


suspend fun HandleLogin(
    usernameOrEmail: String,
    password: String,
    navController: NavController
) {
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()

    val email: String = if (usernameOrEmail.contains("@")) {
        //Regex ile kullanıcının email girmiş olduğunu tespit ettik
        usernameOrEmail
    } else {
        // diğer durum kullanıcı giriş yaparken kullanıcı adı girmiş firestoredan bunu bulalım
        val querySnapshot = firestore.collection("users")
            .whereEqualTo("username",usernameOrEmail)
            .get()
            .await()

        if (querySnapshot.documents.isNotEmpty()) {
            querySnapshot.documents[0].getString("email") ?: throw Exception("Kullanıcı bulunamadı")
        } else
        {
            throw Exception("Kullanıcı bulunamadı")
        }
    }

    try {
        auth.signInWithEmailAndPassword(email, password).await()
        val user = auth.currentUser

        if (user != null) {
            // Giriş başarılı ise kullanıcı adını buradan alacağız
            val docRef = firestore.collection("users").document(user.uid)
            val snapshot = docRef.get().await()
            val storedUsername = snapshot.getString("username") ?: ""

            UserSession.username = storedUsername
            Log.d("UserSession", "Username set to: ${UserSession.username}")

            navController.navigate("userProfile") {
                popUpTo("userProfile") { inclusive = true }
            }
        }

    } catch (e: Exception) {
        // Giriş başarısızsa, Kullanıcıyı yönlendirelim
        if (e is FirebaseAuthException) {
            when (e.errorCode) {
                "ERROR_WRONG_PASSWORD" -> {
                    throw Exception("Şifre hatalı. Lütfen tekrar deneyiniz.")
                } else -> {
                throw Exception("Bir hata oluştu. Lütfen tekrar deneyiniz.")
                }
            }
        } else {
            throw Exception("Bir hata oluştu. Lütfen tekrar deneyiniz.")
        }
    }
}