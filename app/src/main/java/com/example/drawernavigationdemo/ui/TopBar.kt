package com.example.drawernavigationdemo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(title:String, icon: ImageVector, onButtonClick: () -> Unit) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = icon ,
                    contentDescription = "Home",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(horizontal = 5.dp)
                        .clickable { onButtonClick.invoke() },
                )
            }
        })

}