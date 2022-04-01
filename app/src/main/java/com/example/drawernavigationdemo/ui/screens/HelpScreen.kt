package com.example.drawernavigationdemo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.drawernavigationdemo.ui.TopBar

@Composable
fun HelpScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Help",
            icon = Icons.Filled.ArrowBack,
            onButtonClick = {navController.popBackStack()}
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "This is Help Composable")
        }
    }

}