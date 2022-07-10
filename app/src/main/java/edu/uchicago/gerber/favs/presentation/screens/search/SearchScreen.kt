package edu.uchicago.gerber.favs.presentation.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.uchicago.gerber.favs.presentation.search.SearchOperation
import edu.uchicago.gerber.favs.presentation.search.widgets.BusinessList
import edu.uchicago.gerber.favs.presentation.widgets.BottomNavigationBar
import edu.uchicago.gerber.favs.screens.CustomTextField
import edu.uchicago.gerber.favs.presentation.viewmodels.FavViewModel



@Composable
fun SearchScreen(
    favViewModel: FavViewModel,
    navController: NavController,

    ) {
    val state = favViewModel.searchState.value
    val queryText = favViewModel.queryText.value
    val locationText = favViewModel.locationText.value

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        topBar = {
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 1.dp,

            ) {
            Text(
                text = "Search Places",
                modifier = Modifier
                    .fillMaxWidth(),
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

        }
    }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomTextField(
                title = "Query",
                placeHolder = "e.g. tapas",

                textState = queryText,
               // textState = "Sushi",
                onTextChange = favViewModel::setQueryText,
                keyboardType = KeyboardType.Text,
                ImeAction.Next,
                null
            )
            CustomTextField(
                title = "Location",
                placeHolder = "e.g. chicago",
                textState = locationText,
                onTextChange = favViewModel::setLocationText,
                keyboardType = KeyboardType.Text,
                ImeAction.Search,
                favViewModel::onSearch
            )

            Spacer(modifier = Modifier.height(10.dp))
            when (state.searchOperation) {
                SearchOperation.LOADING -> {
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
                SearchOperation.DONE -> {
                    BusinessList(favViewModel, navController)
                }
                else -> {
                    Box {}
                }
            }

        }
    }
}

