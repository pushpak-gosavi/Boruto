package com.cartoon.boruto.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.cartoon.boruto.presentation.componants.RatingWidget

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
){
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            HomeTopBar (onSearchClicked = {})
        }
    ) {
        RatingWidget(modifier = Modifier, rating = 1.6)
    }
}