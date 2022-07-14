package edu.uchicago.gerber.books.screens


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.uchicago.gerber.books.presentation.screens.contact.ContactScreen
import edu.uchicago.gerber.books.presentation.screens.details.DetailsScreen
import edu.uchicago.gerber.books.presentation.screens.favorites.FavoritesScreen
import edu.uchicago.gerber.books.presentation.screens.search.SearchScreen
import edu.uchicago.gerber.books.presentation.navagation.Screen
import edu.uchicago.gerber.books.presentation.viewmodels.BookViewModel


@Composable
fun Navigation(
    navController: NavHostController
) {

    val bookViewModel: BookViewModel = hiltViewModel()
    NavHost(navController, startDestination = Screen.Search.route) {
        composable(Screen.Search.route) {
            SearchScreen(bookViewModel = bookViewModel, navController = navController)

        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(navController)
        }

        composable(Screen.Contact.route) {
            ContactScreen(navController)
        }

        composable(Screen.Detail.route) { backStackEntry ->
            DetailsScreen(navController = navController, bookViewModel = bookViewModel)
        }
    }
}

