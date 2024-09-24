package com.barisscebeci.tadal.presentation.ui.components.icon

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barisscebeci.tadal.R
import com.barisscebeci.tadal.data.model.Food
import com.barisscebeci.tadal.ui.theme.SepetRenk


@Composable
fun AddToCartIcon(
    food: Food,
    onAddToCart: (Food) -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${food.price} ₺",
            modifier = Modifier.align(Alignment.CenterVertically),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        IconButton(
            onClick = {
                onAddToCart(food)
                Toast.makeText(context, "${food.name} sepete eklendi!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .background(SepetRenk)
                .size(24.dp)
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.arttir_resim),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )

        }
    }
}