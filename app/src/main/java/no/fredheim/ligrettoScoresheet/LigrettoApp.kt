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
    val state by viewModel.gameState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.name
    ) {
        composable(route = Screen.Welcome.name) {
            WelcomeScreen(
                maxScore = state.maxScore,
                onStartGameButtonClick = { maxScore ->
                    viewModel.updateMaxScore(maxScore)
                    navController.navigate(Screen.Players.name)
                }
            )
        }
        composable(route = Screen.Players.name) {
            PlayersScreen(
                players = state.players.values.toList(),
                name = state.playersUiState.name,
                availableColors = state.playersUiState.availableColors,
                chosenColor = state.playersUiState.chosenColor,
                onNameChange = { viewModel.updateName(it) },
                onChosenColor = { viewModel.updateChosenColor(it) },
                onPlayerAdded = { viewModel.addPlayer(it) },
                onStartGameClick = {
                    viewModel.initNextRoundAllPlayers(firstTime = true)
                    navController.navigate(Screen.PlayerRound.name)
                },
                onBack = { navController.popBackStack() },
            )
        }
        composable(route = Screen.PlayerRound.name) {
            val currentPlayer = viewModel.currentPlayer()
            PlayerRoundScreen(
                player = currentPlayer,
                round = viewModel.currentRound(currentPlayer),
                numPlayers = viewModel.numPlayers(),
                onNext = {
                    viewModel.addRoundCurrentPlayer(it)
                    navController.navigate(Screen.PlayerRound.name)
                },
                onResults = {
                    viewModel.addRoundCurrentPlayer(it)
                    navController.navigate(Screen.Results.name)
                },
                onBack = {
                    viewModel.handleBackPress(Screen.PlayerRound)
                    navController.popBackStack()
                },
            )
        }
        composable(route = Screen.Results.name) {
            ResultsScreen(
                players = state.players.values.toList(),
                round = viewModel.currentRound,
                onNewRound = {
                    viewModel.initNextRoundAllPlayers(firstTime = false)
                    navController.navigate(Screen.PlayerRound.name)
                },
                onEnd = {}
            )
        }
    }
}
