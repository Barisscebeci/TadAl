package com.barisscebeci.tadal.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.barisscebeci.tadal.R
import com.barisscebeci.tadal.ui.theme.KanitFont
import com.barisscebeci.tadal.ui.theme.SepetRenk
import com.barisscebeci.tadal.presentation.viewmodel.DetailsViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.barisscebeci.tadal.ui.theme.turuncuRenk
import com.barisscebeci.tadal.presentation.ui.assets.LottiePopup
import com.barisscebeci.tadal.presentation.ui.components.ShowLoginPrompt
import com.barisscebeci.tadal.presentation.ui.components.StarRating
import com.barisscebeci.tadal.presentation.ui.components.icon.FavoriteIcon
import com.barisscebeci.tadal.presentation.viewmodel.FavoritesViewModel
import com.barisscebeci.tadal.ui.theme.NameRenk
import com.barisscebeci.tadal.ui.theme.TeslimatRenk
import com.barisscebeci.tadal.ui.theme.indirimRenk
import com.barisscebeci.tadal.ui.theme.sureRenk

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsPage(navController: NavController, foodId: Int) {
    val viewModel: DetailsViewModel = hiltViewModel()
    val selectedFood by viewModel.selectedFood.collectAsState()
    val viewModelFav: FavoritesViewModel = hiltViewModel()
    var quantity by remember { mutableStateOf(1) }
    val rating by viewModel.rating.collectAsState()
    var showLottie by remember { mutableStateOf(false) }
    var showLoginPrompt by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Ürün Detayı",
                            modifier = Modifier.padding(8.dp),
                            textAlign = TextAlign.Center,
                            fontFamily = KanitFont,
                            fontSize = 24.sp,
                            color = SepetRenk,
                        )
                    }
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
                    selectedFood?.let { food ->
                        val isFavorite by viewModelFav.isFavorite(food)
                            .collectAsState(initial = false)
                        FavoriteIcon(
                            food = food,
                            isFavorite = isFavorite,
                            onFavoriteClick = { viewModelFav.toggleFavorite(it) }
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            selectedFood?.let { food ->
                StarRating(
                    rating = rating,
                ) { newRating ->
                    if (viewModel.isUserLoggedIn()) {
                        viewModel.setRating(food.id, newRating)
                    } else {
                        showLoginPrompt = true
                    }
                }
                if (showLoginPrompt) {
                    ShowLoginPrompt(
                        onDismiss = { showLoginPrompt = false },
                        onLogin = {
                            showLoginPrompt = false
                            navController.navigate("userAccount")
                        }
                    )
                }

                AsyncImage(
                    model = food.imageUrl,
                    contentDescription = food.imageName,
                    modifier = Modifier
                        .size(300.dp)
                        .padding(4.dp)

                )

                Text(
                    text = food.name,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 8.dp)
                        .offset(y = (-32).dp),
                    fontFamily = KanitFont,
                    fontSize = 36.sp,
                    color = NameRenk,
                )
                Text(
                    text = "${food.price} ₺",
                    modifier = Modifier
                        .padding(8.dp)
                        .offset(y = (-32).dp),
                    fontFamily = KanitFont,
                    fontSize = 36.sp,
                    color = SepetRenk,
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(8.dp)
                        .offset(y = (-32).dp)
                ) {
                    IconButton(
                        onClick = {
                            if (quantity > 1) {
                                quantity--
                            }
                        },
                        modifier = Modifier
                            .size(64.dp)
                            .background(
                                if (quantity > 1) turuncuRenk else Color.Gray,
                                shape = MaterialTheme.shapes.small
                            ),
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White,
                            disabledContainerColor = Color.Gray,
                            disabledContentColor = Color.LightGray
                        ),
                        enabled = quantity > 1
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.azalt_resim),
                            contentDescription = "Azalt",
                            modifier = Modifier.size(32.dp),
                            tint = Color.White
                        )
                    }
                    Text(
                        text = quantity.toString(),
                        modifier = Modifier.padding(horizontal = 32.dp),
                        fontSize = 48.sp,
                        fontFamily = KanitFont,
                        color = NameRenk,
                        textAlign = TextAlign.Center
                    )
                    IconButton(
                        onClick = {
                            quantity++
                        },
                        modifier = Modifier
                            .size(64.dp)
                            .background(turuncuRenk, shape = MaterialTheme.shapes.small),
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White,
                            disabledContainerColor = Color.Gray,
                            disabledContentColor = Color.LightGray
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.arttir_resim),
                            contentDescription = "",
                            modifier = Modifier.size(32.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }

                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Ücretsiz Teslimat",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray,
                        modifier = Modifier
                            .background(TeslimatRenk, shape = MaterialTheme.shapes.small)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                    Text(
                        text = "İndirim %10",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Red,
                        modifier = Modifier
                            .background(indirimRenk, shape = MaterialTheme.shapes.small)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                    Text(
                        text = "25-35 dk",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Blue,
                        modifier = Modifier
                            .background(sureRenk, shape = MaterialTheme.shapes.small)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }


                // Toplam fiyat ve Sepete Ekleme İşlemleri
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    val totalPrice = food.price.toInt() * quantity
                    Text(
                        text = "$totalPrice ₺",
                        modifier = Modifier,
                        fontSize = 36.sp,
                        fontFamily = KanitFont,

                        )
                    Button(
                        onClick = {
                            viewModel.addToCart(food, quantity)
                            showLottie = true

                        },
                        modifier = Modifier
                            .width(200.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SepetRenk,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Sepete Ekle")
                    }
                }
                if (showLottie) {
                    LottiePopup(
                        "addtocart.json",
                        onDismiss = { showLottie = false }
                    )
                    LaunchedEffect(Unit) {
                        kotlinx.coroutines.delay(4000)
                        navController.navigate("cartPage")
                    }
                }
            } ?: run {
                CircularProgressIndicator()
            }
        }
    }
}
