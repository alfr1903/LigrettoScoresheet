package no.fredheim.ligrettoScoresheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import no.fredheim.ligrettoScoresheet.ui.LigrettoViewModel
import no.fredheim.ligrettoScoresheet.ui.screens.PlayerRoundScreen
import no.fredheim.ligrettoScoresheet.ui.screens.PlayersScreen
import no.fredheim.ligrettoScoresheet.ui.screens.ResultsScreen
import no.fredheim.ligrettoScoresheet.ui.screens.WelcomeScreen

enum class Screen {
    Welcome,
    Players,
    PlayerRound,
    Results
}

@Composable
fun LigrettoApp(
    navController: NavHostController = rememberNavController(),
    viewModel: LigrettoViewModel = viewModel()
) {
    val players by viewModel.playersState.collectAsState()
    val colorPicker by viewModel.colorPickerState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.name
    ) {
        composable(route = Screen.Welcome.name) {
            WelcomeScreen(
                onStartGameButtonClick = { navController.navigate(Screen.Players.name) }
            )
        }
        composable(route = Screen.Players.name) {
            PlayersScreen(
                players = players.values.toList(),
                name = colorPicker.name,
                availableColors = colorPicker.availableColors,
                chosenColor = colorPicker.chosenColor,
                onName = { viewModel.updateName(it) },
                onChosenColor = { viewModel.updateChosenColor(it) },
                onPlayerAdd = { viewModel.addPlayer(it) },
                onStartGameClick = {
                    viewModel.nextRound(firstRound = true)
                    navController.navigate(Screen.PlayerRound.name)
                },
                onBack = {
                    viewModel.reset()
                    navController.popBackStack()
                },
            )
        }
        composable(route = Screen.PlayerRound.name) {
            val currentPlayer = viewModel.currentPlayer()
            PlayerRoundScreen(
                player = currentPlayer,
                round = viewModel.currentRound(currentPlayer),
                numPlayers = viewModel.numPlayers(),
                onHome = {
                    viewModel.resetRoundData()
                    navController.popBackStack(route = Screen.Players.name, inclusive = false)
                },
                onNext = {
                    viewModel.addRound(it, currentPlayer)
                    viewModel.incrementPlayer()
                    navController.navigate(Screen.PlayerRound.name)
                },
                onResults = {
                    viewModel.addRound(it, currentPlayer)
                    navController.navigate(Screen.Results.name)
                },
                onPrevious = {
                    viewModel.addRound(it, currentPlayer)
                    viewModel.decrementPlayer()
                    navController.popBackStack()
                },
            )
        }
        composable(route = Screen.Results.name) {
            ResultsScreen(
                playersScore = viewModel.playersScore(),
                onHome = {
                    viewModel.resetRoundData()
                    navController.popBackStack(route = Screen.Players.name, inclusive = false)
                },
                onNewRound = {
                    viewModel.nextRound(firstRound = false)
                    navController.navigate(Screen.PlayerRound.name)
                },
                onEnd = {
                    viewModel.reset()
                    navController.popBackStack(route = Screen.Welcome.name, inclusive = false)
                },
            )
        }
    }
}
