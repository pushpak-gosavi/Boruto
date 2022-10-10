package com.cartoon.boruto.presentation.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.ImageButton
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cartoon.boruto.R
import com.cartoon.boruto.ui.theme.buttonBackgroundColor
import com.cartoon.boruto.ui.theme.topAppBarBackgroundColor
import com.cartoon.boruto.ui.theme.topAppbarContentColor
import com.google.accompanist.pager.HorizontalPager

@Composable
fun HomeTopBar(onSearchClicked: () -> Unit) {

    TopAppBar(
        title = {
            Text(text = "Explore",
                color = MaterialTheme.colors.topAppbarContentColor)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search_icon))
            }
        }
    )
}

@Composable
@Preview
fun HomeTopAppBarPreview(){
    HomeTopBar {
    }
}