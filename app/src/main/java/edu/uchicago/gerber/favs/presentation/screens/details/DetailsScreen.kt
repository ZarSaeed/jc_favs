package edu.uchicago.gerber.favs.presentation.screens.details


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage
import edu.uchicago.gerber.favs.R
import edu.uchicago.gerber.favs.presentation.viewmodels.FavViewModel


@Composable
fun DetailsScreen(
    navController: NavController,
    favViewModel: FavViewModel
) {

    //observe the business
    val business = favViewModel.business.value
    val context = (LocalContext.current as? Activity)


    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 1.dp
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                        .align(Alignment.CenterVertically)
                        .padding(20.dp, 0.dp, 0.dp, 0.dp))



                Text(
                    text = "Details",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {

                    Icon(imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        modifier = Modifier
                            .clickable {
                                val sendIntent = Intent(Intent.ACTION_SEND)
                                sendIntent.type = "text/plain"
                                sendIntent.putExtra(
                                    Intent.EXTRA_TEXT,
                                    business.name + "\n \n"
                                )
                                context?.startActivity(sendIntent)
                            }
                            .align(Alignment.CenterVertically)
                            .padding(10.dp, 0.dp, 0.dp, 0.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(painter = painterResource(id = R.drawable.ic_navigation),
                        contentDescription = "Map",

                        modifier = Modifier
                            .clickable {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(
                                        "google.navigation:q=${business.location?.displayAddress}"
                                    )
                                )
                                context?.startActivity(intent)
                            }
                            .align(Alignment.CenterVertically)
                            .padding(10.dp, 0.dp, 10.dp, 0.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(imageVector = Icons.Default.Phone,
                        contentDescription = "Phone",

                        modifier = Modifier
                            .clickable {
                                val intent = Intent(
                                    Intent.ACTION_DIAL,
                                    Uri.parse("tel:${business.displayPhone}")
                                )
                                context?.startActivity(intent)
                            }
                            .align(Alignment.CenterVertically)
                            .padding(0.dp, 0.dp, 20.dp, 0.dp))
                }

            }

        }
    }) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState(0))
                .padding(20.dp, 0.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {


                Divider()
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(300.dp),
                    imageModel = business.imageUrl,
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.FillWidth,
                    // shows an image with a circular revealed animation.
                    // shows a placeholder ImageBitmap when loading.

                )
                business.name?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Start,
                        fontSize = 22.sp
                    )
                }

                business.categories?.get(0)?.let {
                    Text(
                        text = it.title.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(fontWeight = FontWeight.Normal),
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp
                    )
                }

                Row(horizontalArrangement = Arrangement.Center) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "start",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                    business.rating?.let {
                        Text(
                            text = it.toString(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.Normal),
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp
                        )
                    }
                }

                business.location?.let {
                    Text(
                        text = it.displayAddress.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(fontWeight = FontWeight.Normal),
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp
                    )
                }

                business.displayPhone?.let {
                    Text(
                        text = it.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(fontWeight = FontWeight.Normal),
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp
                    )
                }

                Button(
                    modifier =
                    Modifier
                        .padding(10.dp, 0.dp)
                        .fillMaxWidth(1f),


                    onClick = {
                        Toast.makeText(context, "Favorite Pressed", Toast.LENGTH_LONG).show()

                    },

                    colors =
                        ButtonDefaults.buttonColors(backgroundColor = Color.Green)

                ) {

                        Text(text = "Add to Favorites")

                }

            }
        }
    }

}

@Preview(showBackground = true)
@ExperimentalAnimationApi
@Composable
fun DetailsScreenPreview() {

//    DetailsScreen(
//        navController = rememberNavController(),
//        context = null
//    )
}


