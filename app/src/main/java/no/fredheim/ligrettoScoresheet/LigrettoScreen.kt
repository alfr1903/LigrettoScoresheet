package no.fredheim.ligrettoScoresheet

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import no.fredheim.ligrettoScoresheet.ui.LigrettoViewModel
import no.fredheim.ligrettoScoresheet.ui.screens.PlayerRoundScoreScreen
import no.fredheim.ligrettoScoresheet.ui.screens.PlayersScreen
import no.fredheim.ligrettoScoresheet.ui.screens.ResultsScreen
import no.fredheim.ligrettoScoresheet.ui.screens.WelcomeScreen

enum class LigrettoScreen {
    Welcome,
    Players,
    PlayerRoundScore,
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
        startDestination = LigrettoScreen.Welcome.name
    ) {
        composable(route = LigrettoScreen.Welcome.name) {
            WelcomeScreen(
                maxScore = state.maxScore,
                onStartGameButtonClick = { maxScore ->
                    viewModel.updateMaxScore(maxScore)
                    navController.navigate(LigrettoScreen.Players.name)
                },
                modifier = Modifier.screenBorder()
            )
        }
        composable(route = LigrettoScreen.Players.name) {
            PlayersScreen(
                players = state.players.values.toList(),
                name = state.playersUiState.name,
                availableColors = state.playersUiState.availableColors,
                chosenColor = state.playersUiState.chosenColor,
                onNameChange = { viewModel.updateName(it) },
                onChosenColorChange = { viewModel.updateChosenColor(it) },
                onPlayerCreated = { viewModel.addPlayer(it) },
                onWriteResultsButtonClick = {
                    viewModel.initNextRoundAllPlayers()
                    navController.navigate(LigrettoScreen.PlayerRoundScore.name)
                },
                modifier = Modifier.screenBorder()
            )
        }
        composable(route = LigrettoScreen.PlayerRoundScore.name) {
            val currentPlayer = viewModel.currentPlayer()

            PlayerRoundScoreScreen(
                player = currentPlayer,
                round = viewModel.currentRound(currentPlayer),
                lastPlayer = viewModel.lastPlayer(),
                onNextPlayerButtonClick = {
                    viewModel.addRoundCurrentPlayer(it)
                    navController.navigate(LigrettoScreen.PlayerRoundScore.name)
                },
                onResultsButtonClick = {
                    viewModel.addRoundCurrentPlayer(it)
                    navController.navigate(LigrettoScreen.Results.name)
                },
                onBack = {
                    viewModel.handleBackPress(LigrettoScreen.PlayerRoundScore)
                    navController.popBackStack()
                },
                modifier = Modifier.screenBorder()
            )
        }
        composable(route = LigrettoScreen.Results.name) {
            ResultsScreen(
                players = state.players.values.toList(),
                onNextRoundButtonClick = {
                    viewModel.initNextRoundAllPlayers()
                    navController.navigate(LigrettoScreen.PlayerRoundScore.name)
                },
                onBack = {
                    viewModel.handleBackPress(LigrettoScreen.Results)
                    navController.popBackStack()
                },
                modifier = Modifier.screenBorder()
            )
        }
    }
}

private fun Modifier.screenBorder() = this.padding(4.dp)
