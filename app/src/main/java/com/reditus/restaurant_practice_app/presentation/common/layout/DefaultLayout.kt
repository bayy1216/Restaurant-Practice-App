package com.reditus.restaurant_practice_app.presentation.common.layout

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultLayout(
    modifier: Modifier? = Modifier,
    title: String? = null,
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable () -> Unit
){
    Scaffold(
        modifier = modifier?: Modifier.fillMaxWidth().fillMaxHeight(),
        topBar = {
            title?.let {
                TopAppBar(
                    title = {
                        Text(text = title)
                    },
                )
            }
        },
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,

    ) {
        content()
    }
}