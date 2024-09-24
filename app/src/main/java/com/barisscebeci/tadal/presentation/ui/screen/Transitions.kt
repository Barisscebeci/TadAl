package com.barisscebeci.tadal.presentation.ui.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.barisscebeci.tadal.presentation.ui.screen.auth.register.RegisterPageMethod
import com.barisscebeci.tadal.presentation.ui.screen.auth.login.UserAccountMethod
import com.barisscebeci.tadal.presentation.ui.screen.auth.UserProfile

@Composable
fun Transitions(navController: NavController) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home(viewModel = viewModel(), navController = navController)
        }
        composable(
            "detailsPage/{foodId}",
            arguments = listOf(navArgument("foodId") { type = NavType.IntType })
        ) { backStackEntry ->
            val foodId = backStackEntry.arguments?.getInt("foodId") ?: return@composable
            DetailsPage(navController = navController, foodId = foodId)
        }
        composable("cartPage") {
            CartPage(navController = navController)
        }
        composable("userAccount") {
            UserAccountMethod(navController = navController)
        }
        composable("userProfile") {
            UserProfile(navController = navController)
        }
        composable("register") {
            RegisterPageMethod(navController = navController)
        }
        composable("favorites") {
            Favorites(navController = navController)
        }
        composable("search") {
            Search(navController = navController)
        }
    }
}




