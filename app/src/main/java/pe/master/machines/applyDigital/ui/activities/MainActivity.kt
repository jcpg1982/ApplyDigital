package pe.master.machines.applyDigital.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pe.master.machines.applyDigital.data.models.Route
import pe.master.machines.applyDigital.ui.customComposables.CustomToolbarCompose
import pe.master.machines.applyDigital.ui.navigation.MainNavigation.MainNavigation
import pe.master.machines.applyDigital.ui.theme.ColorWhite
import pe.master.machines.applyDigital.ui.theme.TestTheme
import pe.master.machines.applyDigital.ui.theme.Turquoise500
import pe.master.machines.applyDigital.ui.viewModels.NavigationViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val navigationViewModel: NavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val currentRoute by navigationViewModel.currentRoute.collectAsStateWithLifecycle()
            val navController = rememberNavController()

            TestTheme(
                context = this,
                colorStatusBar = Turquoise500
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CustomToolbarCompose(
                            title = currentRoute.title,
                            iconNavigation = currentRoute.icon,
                            iconNavigationColor = ColorWhite,
                            onClickNavigation = { navigationViewModel.onBack },
                            backgroundColor = Turquoise500
                        )
                    }) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        MainNavigation(
                            navController = navController,
                            startDestination = Route.Home.route,
                            navigationViewModel = navigationViewModel
                        )
                    }
                }
            }

            LaunchedEffect(currentRoute) {
                navController.navigate(currentRoute.route) {
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }

    override fun onBackPressed() {
        navigationViewModel.onBack
        return
        super.onBackPressed()
    }
}