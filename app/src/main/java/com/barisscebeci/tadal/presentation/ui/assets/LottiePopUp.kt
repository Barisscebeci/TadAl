package com.barisscebeci.tadal.presentation.ui.assets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

@Composable
fun LottiePopup(assetName: String,onDismiss: () -> Unit) {
    // 4 saniye sonra dialog'u kapat
    LaunchedEffect(Unit) {
        delay(4000) // 4 saniye bekle
        onDismiss() // Dialog'u kapat
    }

    Dialog(onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)

    ) {
        Card(
            modifier = Modifier
                .size(300.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        ) {

            Box(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Lottie animasyonu
                val composition by rememberLottieComposition(LottieCompositionSpec.Asset(assetName))
                LottieAnimation(
                    composition = composition,
                    iterations = 1 // Animasyon bir kere oynatılır
                )
            }
        }
    }
}