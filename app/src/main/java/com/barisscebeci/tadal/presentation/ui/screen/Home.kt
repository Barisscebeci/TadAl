package com.barisscebeci.tadal.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.barisscebeci.tadal.presentation.ui.components.BottomBar
import com.barisscebeci.tadal.presentation.ui.components.ProductComponent
import com.barisscebeci.tadal.presentation.viewmodel.FoodViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.barisscebeci.tadal.R
import com.barisscebeci.tadal.ui.theme.KanitFont
import com.barisscebeci.tadal.ui.theme.TadRenk
import com.barisscebeci.tadal.ui.theme.turuncuRenk

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController,
    viewModel: FoodViewModel = viewModel(),
) {
    val foodList by viewModel.foodList.collectAsState()
    // kullanıcı oturum açtı mı kontrolü yapılacak
    val isUserLoggedIn by viewModel.isUserLoggedIn.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,

                        ) {
                        Text(
                            text = "TAD",
                            fontFamily = KanitFont,
                            color = TadRenk,

                        )
                        Text(
                            text = "AL",
                            fontFamily = KanitFont,
                            color = turuncuRenk
                        )
                    }
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.menu_resim),
                        contentDescription = "Menu",
                        modifier = Modifier.padding(start = 8.dp)
                    )

                },

                actions = {
                    Icon(
                        painter = painterResource(id = R.drawable.kullanici_resim),
                        contentDescription = "Kullanıcı",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable {
                                if (isUserLoggedIn) {
                                    navController.navigate("userProfile")
                                } else {
                                    navController.navigate("userAccount")
                                }
                            }
                    )
                }
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->
        Spacer(modifier = Modifier.height(32.dp))
        LazyVerticalGrid( //LazyColumn yerine LazyVerticalGrid kullanıyoruz bunun sebebi yatayda ikili bir sütun modeli oluşturmak için
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(foodList) { food ->
                ProductComponent(navController = navController, food = food)
            }
        }
    }
}

