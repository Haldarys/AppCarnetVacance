package fr.haldarys.appprojet.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun TempScreen1(navController: NavController){
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, "Valere1_Plantevin@uqac.ca")
                putExtra(Intent.EXTRA_SUBJECT, "Rendu du TP2")
                putExtra(Intent.EXTRA_TEXT, """
                    Bonjour monsieur,
                    
                    Voici le lien du github pour notre rendu du TP2 :
                    https://github.com/Haldarys/AppProjet
                    
                    Membres du groupe :
                    - Loïc FAISY
                    - Lucas CORREIA
                    - Aurélien PINON
                    - Samuel REBILLARD
                    
                    Cordialement
                """.trimIndent())
            }

            context.startActivity(
                Intent.createChooser(intent, "Rendu du projet")
            )
        }) {
            Text(text = "Rendre le projet par mail")
        }
    }
}