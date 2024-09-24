package com.barisscebeci.tadal.presentation.ui.components.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.barisscebeci.tadal.R
import com.barisscebeci.tadal.ui.theme.turuncuRenk

@Composable
fun DeliveryIcon() {
    // Teslimat ikonunu ve teslimat süresini gösterir

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = R.drawable.delivery_resim),
            contentDescription = "Teslimat İkonu",
            modifier = Modifier
                .height(32.dp)
                .width(32.dp)
                .padding(start = 4.dp),
            colorFilter = ColorFilter.tint(turuncuRenk)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Ücretsiz Teslim",
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.wrapContentWidth()
        )
    }
}