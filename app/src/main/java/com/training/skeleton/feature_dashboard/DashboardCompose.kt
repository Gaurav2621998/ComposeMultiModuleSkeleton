package com.training.skeleton.feature_dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.training.skeleton.MainActivityViewModel
import com.training.skeleton.feature_dashboard.data.Anime
import com.training.skeleton.navigation.Screen

@Composable
fun DashboardCompose(
    mainActivityViewModel: MainActivityViewModel =viewModel(),
    navigateToProfile:()->Unit,
    navigateToSettings:()->Unit,
) {

    val dashboardViewModel:DashboardViewModel = viewModel()
    val uiState= dashboardViewModel.animeUIState.collectAsState()

    val animeList=uiState.value.animeList

    mainActivityViewModel.setScreenParams(
        screen = Screen.Dashboard,
        screenTitle = "Dashboard"
    )

    DashboardMainContent(navigateToProfile,navigateToSettings,animeList)


}
@Composable
fun DashboardMainContent(
    navigateToProfile: () -> Unit,
    navigateToSettings: () -> Unit,
    animeList: List<Anime>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally){


        Button(onClick = { navigateToProfile()}) {
            Text(text = "Go to profile")
        }

        Button(onClick = { navigateToSettings()}) {
            Text(text = "Go to settings")
        }
        ShowAnimeList(animeList)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowAnimeList(animeList:List<Anime>){

    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(animeList.size) { index ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(animeList[index].title.link)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
                Text(
                    text = animeList[index].title.text,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}
@Composable
@Preview
fun DashboardPreview(){
    val  navigateToProfile : () -> Unit = {}
    val  navigateToSettings : () -> Unit = {}
    DashboardMainContent(navigateToProfile, navigateToSettings, animeList)
}

@Composable
@Preview
fun ShowAnimeList(){
    ShowAnimeList(animeList = animeList )
}



