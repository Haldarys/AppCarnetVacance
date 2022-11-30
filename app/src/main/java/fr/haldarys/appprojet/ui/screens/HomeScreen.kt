package fr.haldarys.appprojet.ui.screens

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.navigation.NavController
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

@Composable
fun Home(navController: NavController){
    var result by remember {
        mutableStateOf(0)
    }

    var location by remember {
        mutableStateOf(LocationData("0", "0"))
    }

    val context : Context = LocalContext.current;

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "$result")
        Button(onClick = {
            result = (0..100).random()
            location = getLocation(context)
        }) {
            Text(text = "Click me !")
        }
        Text(text = "Lat :" + location.lat)
        Text(text = "Long :" + location.long)
    }
}

data class LocationData (
    val lat : String,
    val long : String,
)

fun getLocation(context : Context) : LocationData {
    val fusedLocationClient : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
    var lat : String = "0";
    var long : String = "0";
    if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(context as Activity, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION), 99);
    }
    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
        if(location != null) {
            lat = location.latitude.toString()
            long = location.longitude.toString()
        }
    }
    return LocationData(lat, long);
}