package com.barisscebeci.tadal.presentation.ui.components.icon

import android.widget.Toast

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.barisscebeci.tadal.R
import com.barisscebeci.tadal.data.model.Food
import com.barisscebeci.tadal.ui.theme.SepetRenk
import com.google.firebase.auth.FirebaseAuth

@Composable
fun FavoriteIcon(
    food: Food,
    isFavorite: Boolean,
    onFavoriteClick: (Food) -> Unit
) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val context = LocalContext.current
    IconButton(
        onClick = {
            if (currentUser != null) {
                onFavoriteClick(food)
            } else {
                // Kullanıcı giriş yapmamışsa uyarı göster
                Toast.makeText(
                    context,
                    "Favorilere eklemek için giriş yapmalısınız.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    ) {
        Icon(
            painter = painterResource(
                id = if (isFavorite) R.drawable.favorite2_resim else R.drawable.favorite_icon
            ),
            contentDescription = null,
            tint = SepetRenk,
        )

    }
}

