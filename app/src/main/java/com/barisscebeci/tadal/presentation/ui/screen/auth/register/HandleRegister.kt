package com.barisscebeci.tadal.presentation.ui.screen.auth.register

import android.util.Log
import androidx.navigation.NavController
import com.barisscebeci.tadal.presentation.ui.screen.auth.UserSession
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun HandleRegister(
    email: String,
    username: String,
    password: String,
    navController: NavController
) {
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()

    try {
        //Kullanıcı adı daha önce kullanılmış mı kontrolü yapalım
        val querySnapshot = firestore.collection("users")
            .whereEqualTo("username", username)
            .get()
            .await()

        //Kullanıcı eğer daha önce kullanılmış bir kullanıcı adı seçtiyse hata fırlatalım
        if (querySnapshot.documents.isNotEmpty()) {
            throw Exception("Bu kullanıcı adı daha önce alınmış")
        }

        auth.createUserWithEmailAndPassword(email, password).await()
        val user = auth.currentUser

        if (user != null) {
            val userMap = hashMapOf(
                "username" to username,
                "email" to email
            )
            //Kullanıcının girdiği mail ile kullanıcı adını firestore yazdırıyoruz
            firestore.collection("users").document(user.uid).set(userMap).await()

            //Kullanıcının ismini uygulamada kullanmak için bir değişkene atıyoruz
            UserSession.username = username

            Log.d("UserSession", "Username set to: ${UserSession.username}")


        }
    } catch (e: Exception) {
        if (e is FirebaseAuthUserCollisionException) {
            //Daha önce e-posta adresi başka bir kullanıcı tarafından kullanıldı mı kontrolü yapalım
            throw Exception("Bu e-posta adresi daha önce kullanılmış")
        } else {
            throw Exception("Bu kullanıcı adı daha önce alınmış. Lütfen başka bir kullanıcı adı seçiniz")
        }
    }
}