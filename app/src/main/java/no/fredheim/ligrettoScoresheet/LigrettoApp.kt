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
import no.fredheim.ligrettoScoresheet.ui.PlayerSideEffect
import no.fredheim.ligrettoScoresheet.ui.RoundSideEffect
import no.fredheim.ligrettoScoresheet.ui.screens.PlayerRoundScreen
import no.fredheim.ligrettoScoresheet.ui.screens.PlayersScreen
import no.fredheim.ligrettoScoresheet.ui.screens.PlayersScreenNav
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
    val playerCreator by viewModel.playerCreatorState.collectAsState()
    val round by viewModel.roundState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.name
    ) {
        composable(route = Screen.Welcome.name) {
            WelcomeScreen(
                onStartClick = { navController.navigate(Screen.Players.name) }
            )
        }
        composable(route = Screen.Players.name) {
            PlayersScreen(
                players = players.values.toList(),
                playerCreator = playerCreator,
                onPlayerCreatorChange = { viewModel.updatePlayerCreatorState(it) },
                onPlayerAdd = { viewModel.addPlayer(it) },
                onNavigate = { playersScreenNavigate(it, navController, viewModel) },
            )
        }
        composable(route = Screen.PlayerRound.name) {
            val currentPlayer = viewModel.currentPlayer()

            PlayerRoundScreen(
                player = currentPlayer,
                round = round,
                onRoundChange = { player, rnd -> viewModel.updatePlayerRound(player, rnd)},
                numPlayers = viewModel.numPlayers(),
                onHome = {
                    viewModel.resetData(deletePlayers = false)
                    navController.popBackStack(route = Screen.Players.name, inclusive = false)
                },
                onPrevious = {
                    viewModel.sideEffect(PlayerSideEffect.Decrement)
                    navController.popBackStack()
                },
                onNext = {
                    viewModel.sideEffect(PlayerSideEffect.Increment)
                    navController.navigate(Screen.PlayerRound.name)
                },
                onResults = { navController.navigate(Screen.Results.name) },
                onBack = {
                    when {
                        firstRoundFirstPlayer(viewModel) -> {
                            viewModel.resetData(deletePlayers = false)
                        }
                        firstPlayer(viewModel) -> viewModel.sideEffect(RoundSideEffect.Decrement)
                        else -> viewModel.sideEffect(PlayerSideEffect.Decrement)
                    }
                    navController.popBackStack()
                }
            )
        }
        composable(route = Screen.Results.name) {
            ResultsScreen(
                roundId = viewModel.currentRound,
                playersScore = viewModel.playersScore(),
                onHome = {
                    viewModel.resetData(deletePlayers = false)
                    navController.popBackStack(route = Screen.Players.name, inclusive = false)
                },
                onNewRound = {
                    viewModel.sideEffect(RoundSideEffect.Increment)
                    navController.navigate(Screen.PlayerRound.name)
                },
                onEnd = {
                    viewModel.resetData()
                    navController.popBackStack(route = Screen.Welcome.name, inclusive = false)
                }
            )
        }
    }
}

private fun playersScreenNavigate(
    it: PlayersScreenNav,
    navController: NavHostController,
    viewModel: LigrettoViewModel
) {
    when (it) {
        PlayersScreenNav.StartGame -> navController.navigate(Screen.PlayerRound.name)
        PlayersScreenNav.Back -> {
            viewModel.resetData()
            navController.popBackStack()
        }
    }
}

private fun firstRoundFirstPlayer(viewModel: LigrettoViewModel) =
    viewModel.currentRound == 1 && firstPlayer(viewModel)

private fun firstPlayer(viewModel: LigrettoViewModel) =
    viewModel.currentPlayer().id == 1
