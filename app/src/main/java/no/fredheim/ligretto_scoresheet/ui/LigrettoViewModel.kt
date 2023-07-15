package no.fredheim.ligretto_scoresheet.ui

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import no.fredheim.ligretto_scoresheet.model.CardType
import no.fredheim.ligretto_scoresheet.model.Player
import no.fredheim.ligretto_scoresheet.model.GameState
import no.fredheim.ligretto_scoresheet.model.PlayersUiState
import no.fredheim.ligretto_scoresheet.model.Round

class LigrettoViewModel: ViewModel() {
    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private var currentRound = 0
    private var currentPlayerIndex = 1

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
                    availableColors =  currentAvailableColors.minus(player.color)
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
        currentRound++
        (1.._gameState.value.players.size).forEach {
            _gameState.value.players[it]!!.round[currentRound] = Round()
        }
    }

    fun currentPlayer(): Player = _gameState.value.players[currentPlayerIndex]!!

    fun currentRound(): Round = currentPlayer().round[currentRound]!!


    fun updateNumCards(cardType: CardType, numCards: String) {
        when(cardType) {
            CardType.Ten -> _gameState.update {
                val playersStats = _gameState.value.players
                val currentRoundStats = playersStats[currentPlayerIndex]!!.round[currentRound]!!
                playersStats[currentPlayerIndex]!!.round[currentRound] =
                    currentRoundStats.copy(num10s = numCards)
                it.copy(players = playersStats)
            }

            CardType.Center -> _gameState.update {
                val playersStats = _gameState.value.players
                val currentRoundStats = playersStats[currentPlayerIndex]!!.round[currentRound]!!
                playersStats[currentPlayerIndex]!!.round[currentRound] =
                    currentRoundStats.copy(numCenter = numCards)
                it.copy(players = playersStats)
            }
            CardType.Ligretto -> _gameState.update {
                val playersStats = _gameState.value.players
                val currentRoundStats = playersStats[currentPlayerIndex]!!.round[currentRound]!!
                playersStats[currentPlayerIndex]!!.round[currentRound] =
                    currentRoundStats.copy(numLigretto = numCards)
                it.copy(players = playersStats)
            }
        }
    }
}