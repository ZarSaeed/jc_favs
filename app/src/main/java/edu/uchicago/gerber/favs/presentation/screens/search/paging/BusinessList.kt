package edu.uchicago.gerber.favs.presentation.search.widgets

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import edu.uchicago.gerber.favs.presentation.navagation.Screen
import edu.uchicago.gerber.favs.presentation.viewmodels.FavViewModel
import edu.uchicago.gerber.favs.presentation.widgets.BusinessRow


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BusinessList(favViewModel: FavViewModel, navController: NavController) {

    val res = favViewModel.searchState.value.data?.collectAsLazyPagingItems()

    return LazyColumn {
        items(res!!) { business ->
            BusinessRow(item = business!!) {
                favViewModel.setBusiness(business)
                navController.navigate(
                    route = Screen.Detail.route
                )
            }
        }

        res.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        ProgBar()
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        ProgBar()
                    }
                }
                loadState.prepend is LoadState.Loading -> {
                    item {
                        ProgBar()
                    }
                }
            }
        }
    }
}

@Composable
fun ProgBar(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(12.dp)
                .align(
                    Alignment.Center
                )
        )
    }
}