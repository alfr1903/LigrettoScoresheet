package no.fredheim.ligretto_scoresheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class LigrettoScreen {
    Welcome,
    Players,
    PlayerRoundScore,
    Results
}

@Composable
fun LigrettoApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = LigrettoScreen.Welcome.name,
    ) {

    }
}