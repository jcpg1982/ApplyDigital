package pe.master.machines.applyDigital.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import pe.master.machines.applyDigital.data.models.Route
import pe.master.machines.applyDigital.ui.viewComposables.HitDetail
import pe.master.machines.applyDigital.ui.viewComposables.Home
import pe.master.machines.applyDigital.ui.viewModels.NavigationViewModel
import java.net.URLDecoder

object MainNavigation {
    @Composable
    fun MainNavigation(
        navController: NavHostController,
        startDestination: String,
        navigationViewModel: NavigationViewModel
    ) {
        NavHost(
            navController = navController, startDestination = startDestination
        ) {
            composable(route = Route.Home.route) { Home(navigationViewModel) }
            composable(
                route = Route.HitDetail().route,
                arguments = listOf(navArgument("webUrl") { type = NavType.StringType })
            ) { backStackEntry ->
                backStackEntry.arguments?.getString("webUrl")?.let { HitDetail(it) }
            }
        }
    }
}