package fr.haldarys.appprojet.ui.screens

import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import fr.haldarys.appprojet.R
import fr.haldarys.appprojet.ui.viewmodels.HolidayViewModel

enum class AddHolidayRoutes(@StringRes val title: Int) {
    Start(R.string.create_holiday),
    Details(R.string.holiday_details)
}

fun NavGraphBuilder.createHolidayGraph(navController: NavController,
                                   modifier: Modifier = Modifier,
                                   viewModel: HolidayViewModel = HolidayViewModel(),
                                   onTitleChanged : (Int) -> Unit,
                                   onCanNavigateBackChange: (Boolean) -> Unit)
{

    navigation(startDestination = AddHolidayRoutes.Start.name, route = Screen.HolidayList.route) {
        composable(route = AddHolidayRoutes.Start.name) {
            onCanNavigateBackChange(false)
            StartCreateHolidayScreen(modifier, onNextButtonClicked = {
                navController.navigate(AddHolidayRoutes.Details.name)
            })
        }
        composable(route = AddHolidayRoutes.Details.name) {
            onCanNavigateBackChange(true)
            EditHolidayScreen(modifier, viewModel, onNextButtonClicked = {
                navController.popBackStack(AddHolidayRoutes.Start.name, inclusive = false)
            })
        }
    }
}