package com.barisscebeci.tadal.presentation.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.barisscebeci.tadal.R
import com.barisscebeci.tadal.ui.theme.SepetRenk
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius


@Composable
fun BottomBar(navController: NavController) {

    // Retrieve the current back stack entry (current route)
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route


    // Map the routes to indices
    val items = listOf(
        BottomNavItem(R.drawable.home_resim, "home", Color.White),
        BottomNavItem(R.drawable.favorite_icon, "favorites", Color.White),
        BottomNavItem(R.drawable.arama_resim, "search", Color.White),
        BottomNavItem(R.drawable.sepet_resim, "cartPage", Color.White)
    )


    val selectedIndex = when (currentRoute) {
        "home" -> 0
        "favorites" -> 1
        "search" -> 2
        "cartPage" -> 3
        else -> 0
    }

    AnimatedNavigationBar(
        modifier = Modifier
            .padding(all = 12.dp)
            .navigationBarsPadding()
            .height(64.dp),
        selectedIndex = selectedIndex,
        cornerRadius = shapeCornerRadius(34.dp),
        ballAnimation = Parabolic(tween(300)),
        indentAnimation = Height(tween(300)),
        barColor = SepetRenk,
        ballColor = SepetRenk,
    ) {
        items.forEachIndexed { index, item ->
            val tint = if (index == selectedIndex) item.color else Color.Gray
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        // Only navigate if the selected index is different
                        if (selectedIndex != index) {
                            navController.navigate(item.route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        modifier = Modifier.size(26.dp),
                        painter = painterResource(id = item.iconRes),
                        contentDescription = "Bottom Navigation Item",
                        tint = if (selectedIndex == index) MaterialTheme.colorScheme.onPrimary
                        else MaterialTheme.colorScheme.inversePrimary
                    )
                }
            }
        }
    }
}