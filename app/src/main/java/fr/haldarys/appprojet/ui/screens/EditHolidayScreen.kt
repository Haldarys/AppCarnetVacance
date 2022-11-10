package fr.haldarys.appprojet.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fr.haldarys.appprojet.data.states.HolidayUiState
import fr.haldarys.appprojet.ui.viewmodels.HolidayViewModel


@Composable
fun EditHolidayScreen(modifier: Modifier = Modifier, lanUiState: HolidayViewModel, onNextButtonClicked : (lanUiState: HolidayViewModel) -> Unit){
    Column (modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        NameFieldRemember()
        Button(onClick = {onNextButtonClicked(lanUiState)}) {
            Text(text = "Save")
        }
    }
}

@Composable
fun NameFieldRemember(){
    var name by remember { mutableStateOf("") }

    NameField(name = name, onNameChange = { name = it })
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameField(name: String, onNameChange: (String) -> Unit) {

    TextField(label = { Text("Name of your holiday") }, value = name, onValueChange = onNameChange)
}