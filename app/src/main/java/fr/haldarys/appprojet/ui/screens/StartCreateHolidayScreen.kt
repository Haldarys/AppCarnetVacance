package fr.haldarys.appprojet.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.haldarys.appprojet.R
import fr.haldarys.appprojet.data.states.CountryUiState
import fr.haldarys.appprojet.ui.viewmodels.CountryViewModel

@Composable
fun StartCreateHolidayScreen(modifier: Modifier = Modifier, viewModel: CountryViewModel = hiltViewModel(),onNextButtonClicked : () -> Unit) {
    val countryList = viewModel.uiState.collectAsState()

    Column (modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Button(onClick = onNextButtonClicked) {
            Text(text = stringResource(R.string.create_holiday))
        }
    }

    Scaffold(
        content = {
            CountryList(countryList = countryList.value, modifier.padding(it))
        }
    )
}

@Composable
private fun CountryList(countryList: List<CountryUiState>, modifier: Modifier = Modifier) {
    LazyColumn() {
        items(count = countryList.size,
            contentType = {
                it
            }){
            CountryCard(country = countryList[it])
        }
    }
}

@Composable
private fun CountryCard(country: CountryUiState, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(8.dp)) {
        Row() {
            Text(text = country.name,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium)
        }
    }
}