package fr.haldarys.appprojet.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.navigation.NavController
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.haldarys.appprojet.ui.viewmodels.LocationViewModel

@Composable
fun Home(navController: NavController,
         viewModel: LocationViewModel = hiltViewModel()){
    val location = viewModel.uiState.collectAsState()
    val city:String = location.value.city

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
        Text(text = "city : $city.")
        Button(onClick = {
            result = (0..100).random()
        }) {
            Text(text = "Click me !")
        }
    }
}