package com.example.drawernavigationdemo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.drawernavigationdemo.ui.TopBar

@Composable
fun AccountScreen(openDrawer: () -> Unit) {

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Account",
            icon = Icons.Filled.Menu,
            onButtonClick = {openDrawer()}
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "This is Account Composable")
        }
    }

}