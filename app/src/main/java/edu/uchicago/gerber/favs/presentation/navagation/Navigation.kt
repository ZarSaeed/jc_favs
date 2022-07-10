package edu.uchicago.gerber.favs.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.uchicago.gerber.favs.presentation.screens.contact.ContactScreen
import edu.uchicago.gerber.favs.presentation.screens.details.DetailsScreen
import edu.uchicago.gerber.favs.presentation.screens.favorites.FavoritesScreen
import edu.uchicago.gerber.favs.presentation.screens.search.SearchScreen
import edu.uchicago.gerber.favs.presentation.navagation.Screen
import edu.uchicago.gerber.favs.presentation.viewmodels.FavViewModel


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    navController: NavHostController
) {

    val favViewModel: FavViewModel = hiltViewModel()
    NavHost(navController, startDestination = Screen.Search.route) {
        composable(Screen.Search.route) {
           // msBottomVisible.value = true
            SearchScreen(favViewModel = favViewModel, navController = navController)

        }
        composable(Screen.Favorites.route) {
           // msBottomVisible.value = true
            FavoritesScreen(navController)
        }

        composable(Screen.Contact.route) {
           // msBottomVisible.value = true
            ContactScreen(navController)
        }

        composable(Screen.Detail.route) { backStackEntry ->
          //  msBottomVisible.value = false
            DetailsScreen(navController = navController, favViewModel = favViewModel)
        }
    }
}

