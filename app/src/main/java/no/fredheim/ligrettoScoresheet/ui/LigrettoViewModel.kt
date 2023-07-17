package no.fredheim.ligrettoScoresheet.ui

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import no.fredheim.ligrettoScoresheet.LigrettoScreen
import no.fredheim.ligrettoScoresheet.model.GameState
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.model.PlayersUiState
import no.fredheim.ligrettoScoresheet.model.Round

class LigrettoViewModel : ViewModel() {
    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    var currentRound = 0
        private set

    var currentPlayerIndex = 1
        private set

    fun updateMaxScore(score: String) {
        _gameState.update { _gameState.value.copy(maxScore = score) }
    }

    fun addPlayer(player: Player) {
        _gameState.update {
            val currentPlayers = _gameState.value.players
            currentPlayers[player.number] = player

            val currentAvailableColors = _gameState.value.playersUiState.availableColors

            _gameState.value.copy(
                players = currentPlayers,
                playersUiState = PlayersUiState(
                    availableColors = currentAvailableColors.minus(player.color)
                )
            )
        }
    }

    fun updateName(name: String) {
        val currentUiState = _gameState.value.playersUiState
        val newUiState = currentUiState.copy(name = name)

        _gameState.update {
            it.copy(playersUiState = newUiState)
        }
    }

    fun updateChosenColor(color: Color) {
        val currentUiState = _gameState.value.playersUiState
        val newUiState = currentUiState.copy(chosenColor = color)

        _gameState.update {
            it.copy(playersUiState = newUiState)
        }
    }
    fun initNextRoundAllPlayers() {
        currentPlayerIndex = 1
        currentRound++
        (1.._gameState.value.players.size).forEach {
            if(!_gameState.value.players[it]!!.round.containsKey(currentRound))
                _gameState.value.players[it]!!.round[currentRound] = Round()
        }
    }

    fun currentPlayer(): Player = _gameState.value.players[currentPlayerIndex]!!

    fun currentRound(player: Player): Round =
        _gameState.value.players[player.number]!!.round[currentRound]!!

    fun addRoundCurrentPlayer(round: Round) {
        _gameState.value.players[currentPlayerIndex]!!.round[currentRound] = round
        if (currentPlayerIndex + 1 <= _gameState.value.players.size) currentPlayerIndex++
    }

    fun lastPlayer(): Boolean = currentPlayerIndex == _gameState.value.players.size
    fun handleBackPress(fromScreen: LigrettoScreen) {
        when (fromScreen) {
            LigrettoScreen.Welcome,
            LigrettoScreen.Players,
            LigrettoScreen.Results -> Unit
            LigrettoScreen.PlayerRoundScore -> {
                when {
                    currentPlayerIndex == 1 && currentRound == 1 -> Unit
                    currentPlayerIndex == 1 -> {
                        currentPlayerIndex = _gameState.value.players.size; currentRound--
                    }
                    else -> currentPlayerIndex--
                }
            }
        }
    }
}
