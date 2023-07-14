package no.fredheim.ligretto_scoresheet.ui

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import no.fredheim.ligretto_scoresheet.model.Player
import no.fredheim.ligretto_scoresheet.model.GameState
import no.fredheim.ligretto_scoresheet.model.Round

class LigrettoViewModel: ViewModel() {
    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    var currentRound = 0
        private set

    private val currentPlayerIndex = 1

    fun updateMaxScore(score: String) {
        _gameState.update { _gameState.value.copy(maxScore = score) }
    }

    fun addPlayer(player: Player) {
        _gameState.update {
            val newAvailableColors = _gameState.value.availableColors.minus(player.color)
            val currentPlayers = _gameState.value.players
            currentPlayers[player.number] = player

            _gameState.value.copy(
                nameInput = "",
                players = currentPlayers,
                availableColors = newAvailableColors,
                chosenColor = newAvailableColors.firstOrNull()
            )
        }
    }

    fun updateNameInput(name: String) {
        _gameState.update { it.copy(nameInput = name) }
    }

    fun updateChosenColor(color: Color) {
        _gameState.update { it.copy(chosenColor = color) }
    }

    fun currentPlayer(): Player = _gameState.value.players[currentPlayerIndex]!!
    fun initNextRoundAllPlayers() {
        currentRound++
        (1.._gameState.value.players.size).forEach {
            _gameState.value.players[it]!!.round[currentRound] = Round()
        }
    }
}