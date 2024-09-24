package com.barisscebeci.tadal.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.barisscebeci.tadal.R
import com.barisscebeci.tadal.data.model.Food
import com.barisscebeci.tadal.ui.theme.KanitFont
import com.barisscebeci.tadal.ui.theme.SepetRenk
import com.barisscebeci.tadal.ui.theme.turuncuRenk
import com.barisscebeci.tadal.presentation.ui.components.BottomBar
import com.barisscebeci.tadal.presentation.viewmodel.FavoritesViewModel
import com.barisscebeci.tadal.ui.theme.NameRenk

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favorites(navController: NavController) {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val favoriteFoods by viewModel.favoriteFoods.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Favorilerim",
                        textAlign = TextAlign.Center,
                        fontFamily = KanitFont,
                        fontSize = 24.sp,
                        color = SepetRenk,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
            if (favoriteFoods.isEmpty()) {
                Text(
                    text = "Henüz favori yemeğiniz yok.",
                    modifier = Modifier.weight(1f)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(favoriteFoods.size) { index ->
                        val food = favoriteFoods[index]
                        FavoriteItemRow(food, navController, viewModel)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun FavoriteItemRow(
    food: Food,
    navController: NavController,
    viewModel: FavoritesViewModel
) {
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
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate("detailsPage/${food.id}") }
                .padding(8.dp),
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = food.imageUrl,
                    contentDescription = food.name,
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
                        text = food.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier,
                        color = NameRenk
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${food.price} ₺",
                        fontSize = 18.sp,
                        fontFamily = KanitFont,
                        color = turuncuRenk
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        viewModel.toggleFavorite(food)
                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.delete_resim),
                        contentDescription = "Favorilerden Kaldır",
                        tint = SepetRenk,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }

    }
}

