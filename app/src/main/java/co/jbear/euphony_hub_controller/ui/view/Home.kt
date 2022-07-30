package co.jbear.euphony_hub_controller.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.jbear.euphony_hub_controller.ui.components.BottomMenuContent
import co.jbear.euphony_hub_controller.ui.components.BottomNavigator
import co.jbear.euphony_hub_controller.ui.theme.EuphonyHubTheme
import com.ramcosta.composedestinations.DestinationsNavHost

@ExperimentalMaterialApi
@Composable
fun Home() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination
    val scaffoldState = rememberScaffoldState()

    EuphonyHubTheme {
        Scaffold(
            bottomBar = {
                BottomNavigator(
                    items = BottomMenuContent.values().toList(),
                    currentRoute = currentScreen?.route ?: BottomMenuContent.COLLECTOR_VIEW.route,
                    navigateToRoute = navController::navigate
                )
            },
            scaffoldState = scaffoldState
        ) {
            Box(modifier = Modifier.padding(it)) {
                DestinationsNavHost(navGraph = NavGraphs.root, navController = navController)
            }
        }
    }
}
