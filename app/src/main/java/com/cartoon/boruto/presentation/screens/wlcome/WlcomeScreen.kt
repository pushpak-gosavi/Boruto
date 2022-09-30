package com.cartoon.boruto.presentation.screens.wlcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.cartoon.boruto.R
import com.cartoon.boruto.domain.model.OnBoardingPage
import com.cartoon.boruto.ui.theme.*
import com.cartoon.boruto.utils.Constants.ON_BOARDING_PAGE_COUNT
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import org.intellij.lang.annotations.JdkConstants

@ExperimentalPagerApi
@Composable
fun WelcomeScreen(navController:NavHostController){
    val pages= listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third)
    val pagerState = rememberPagerState()
    Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)
            ){
        HorizontalPager(
            state = pagerState,
            count = ON_BOARDING_PAGE_COUNT,
            verticalAlignment = Alignment.Top) { position ->
                PagerScreen(onBoardingPage = pages[position])
            
        }
    }
}
@Composable
fun PagerScreen(onBoardingPage:OnBoardingPage){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(modifier = Modifier.fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
                painter = painterResource(id = onBoardingPage.image),
                contentDescription = stringResource(R.string.on_boarding_image))
            Text(modifier = Modifier
                .fillMaxWidth(),
                text = onBoardingPage.title,
                color = MaterialTheme.colors.titleColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(top = EXTRA_LARGE_PADDING)
                .padding(horizontal = SMALL_PADDING)
                ,text = onBoardingPage.description,
                color = MaterialTheme.colors.description,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center)
        }
}

@Composable
@Preview (showBackground = true)
fun FirstOnBoardingScreenPreview(){
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
    }
}

@Composable
@Preview (showBackground = true)
fun SecondOnBoardingScreenPreview(){
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Second)

    }
}

@Composable
@Preview(showBackground = true)
fun ThirdOnBoardingScreenPreview(){
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Third)

    }
}