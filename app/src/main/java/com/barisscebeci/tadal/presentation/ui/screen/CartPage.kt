package com.barisscebeci.tadal.presentation.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.barisscebeci.tadal.R
import com.barisscebeci.tadal.data.model.CartItem
import com.barisscebeci.tadal.ui.theme.KanitFont
import com.barisscebeci.tadal.ui.theme.SepetRenk
import com.barisscebeci.tadal.ui.theme.turuncuRenk
import com.barisscebeci.tadal.presentation.ui.components.BottomBar
import com.barisscebeci.tadal.presentation.viewmodel.CartViewModel
import com.barisscebeci.tadal.ui.theme.NameRenk


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartPage(navController: NavController) {
    val viewModel: CartViewModel = hiltViewModel()
    val cartItems = viewModel.cartItems.collectAsState()
    val totalPrice = viewModel.totalPrice.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Sepetim",
                        textAlign = TextAlign.Center,
                        fontFamily = KanitFont,
                        fontSize = 24.sp,
                        color = SepetRenk,
                        modifier = Modifier.fillMaxWidth()
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
                    Spacer(modifier = Modifier.width(48.dp))
                }
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (cartItems.value.isEmpty()) {
                Text(
                    text = "Sepetinizde ürün bulunmamaktadır.",
                    modifier = Modifier.weight(1f)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(cartItems.value) { item ->
                        CartItemRow(item, viewModel)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-16).dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Gönderim Ücreti",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        fontFamily = KanitFont,
                        modifier = Modifier.padding(start = 20.dp),
                        color = Color.Gray
                    )
                    Text(
                        text = "0 ₺",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        fontFamily = KanitFont,
                        modifier = Modifier.padding(end = 20.dp),
                        color = Color.Gray
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-8).dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Toplam:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        fontFamily = KanitFont,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    Text(
                        text = "${totalPrice.value} ₺",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        fontFamily = KanitFont,
                        modifier = Modifier.padding(end = 20.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 20.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = turuncuRenk,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "SEPETİ ONAYLA",
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                    )
                }
            }
        }
    }
}

@Composable
fun CartItemRow(cartItem: CartItem, viewModel: CartViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = cartItem.imageUrl,
                    contentDescription = cartItem.yemek_adi,
                    modifier = Modifier
                        .size(108.dp)
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "${cartItem.yemek_adi}",
                        fontSize = 18.sp,
                        fontFamily = KanitFont,
                        modifier = Modifier,
                        color = NameRenk,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Row {
                        Text(
                            text = "Fiyat   ",
                            fontSize = 14.sp,
                            fontFamily = KanitFont,
                            color = Color.Black
                        )
                        Text(
                            text = "${cartItem.yemek_fiyat} ₺",
                            fontSize = 14.sp,
                            fontFamily = KanitFont,
                            color = turuncuRenk
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Adet    ${cartItem.yemek_siparis_adet}",
                        fontSize = 14.sp,
                        fontFamily = KanitFont,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = {
                            viewModel.removeItemCompletely(cartItem)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.delete_resim),
                            contentDescription = "Sil",
                            tint = SepetRenk,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "${cartItem.yemek_fiyat * cartItem.yemek_siparis_adet} ₺",
                        fontSize = 16.sp,
                        fontFamily = KanitFont,
                        color = turuncuRenk
                    )
                }
            }
        }
    }
}