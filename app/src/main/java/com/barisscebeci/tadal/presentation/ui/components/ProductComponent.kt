package com.barisscebeci.tadal.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.barisscebeci.tadal.data.model.Food
import com.barisscebeci.tadal.ui.theme.KanitFont
import com.barisscebeci.tadal.presentation.ui.components.icon.AddToCartIcon
import com.barisscebeci.tadal.presentation.ui.components.icon.DeliveryIcon
import com.barisscebeci.tadal.presentation.ui.components.icon.FavoriteIcon
import com.barisscebeci.tadal.presentation.viewmodel.CartViewModel
import com.barisscebeci.tadal.presentation.viewmodel.FavoritesViewModel
import com.barisscebeci.tadal.ui.theme.SepetRenk

@Composable
fun ProductComponent(navController: NavController, food: Food) {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val isFavorite by viewModel.isFavorite(food).collectAsState(initial = false)
    val cartViewModel: CartViewModel = hiltViewModel()

    Row(
        modifier = Modifier
            .width(200.dp)
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("detailsPage/${food.id}")
                    }
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                )
            {

                Box {
                    AsyncImage(
                        model = food.imageUrl,
                        contentDescription = food.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .background(
                                Color.White, shape = RoundedCornerShape(16.dp),
                            )
                    ) {
                        FavoriteIcon(
                            food = food,
                            isFavorite = isFavorite,
                            onFavoriteClick = { viewModel.toggleFavorite(it) }
                        )
                    }

                }
                Text(
                    text = food.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = KanitFont,
                    color = SepetRenk
                )
                DeliveryIcon()
                AddToCartIcon(
                    food = food,
                    onAddToCart = { selectedFood ->
                        cartViewModel.addToCart(selectedFood, 1)
                    }
                )
            }
        }
    }

}




