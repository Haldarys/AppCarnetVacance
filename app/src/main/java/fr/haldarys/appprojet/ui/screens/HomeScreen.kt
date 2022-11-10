package fr.haldarys.appprojet.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.navigation.NavController
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Home(navController: NavController){
    var result by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "$result")
        Button(onClick = {
            result = (0..100).random()
        }) {
            Text(text = "Clique moi !")
        }
    }
}