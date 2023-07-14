package no.fredheim.ligretto_scoresheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import no.fredheim.ligretto_scoresheet.ui.LigrettoViewModel
import no.fredheim.ligretto_scoresheet.ui.PlayerRoundScoreScreen
import no.fredheim.ligretto_scoresheet.ui.PlayersScreen
import no.fredheim.ligretto_scoresheet.ui.ResultsScreen
import no.fredheim.ligretto_scoresheet.ui.WelcomeScreen

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
        startDestination = LigrettoScreen.Welcome.name,
    ) {
        composable(route = LigrettoScreen.Welcome.name) {
            WelcomeScreen(
                maxScore = state.maxScore,
                onMaxScoreChange = { viewModel.updateMaxScore(it) },
                onStartGameButtonClick = { navController.navigate(LigrettoScreen.Players.name) },
            )
        }
        composable(route = LigrettoScreen.Players.name) {
            PlayersScreen(
                players = state.players.values.toList(),
                nameInput = state.nameInput,
                availableColors = state.availableColors,
                chosenColor = state.chosenColor,
                onNameChange = { viewModel.updateNameInput(it) },
                onChosenColorChange = { viewModel.updateChosenColor(it) },
                onPlayerCreated = { viewModel.addPlayer(it) },
                onWriteResultsButtonClick = {
                    viewModel.initNextRoundAllPlayers()
                    navController.navigate(LigrettoScreen.PlayerRoundScore.name)
                }
            )
        }
        composable(route = LigrettoScreen.PlayerRoundScore.name) {
            PlayerRoundScoreScreen(
                player = viewModel.currentPlayer(),
                round = viewModel.currentRound,
                onSubtractCard = {  },
                onNumCardsChange = {  }

            )
        }
        composable(route = LigrettoScreen.Results.name) {
            ResultsScreen()
        }

    }
}