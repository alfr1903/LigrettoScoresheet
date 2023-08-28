package no.fredheim.ligrettoScoresheet.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import no.fredheim.ligrettoScoresheet.model.PlayerCreatorState
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.model.PlayerScore
import no.fredheim.ligrettoScoresheet.model.Round

typealias RoundMap = MutableMap<Int, Round>
typealias PlayerId = Int

class LigrettoViewModel : ViewModel() {
    private var currentRound = 1
    private var idCurrentPlayer: PlayerId = 1


    private val _playersState = MutableStateFlow(mutableMapOf<PlayerId, Player>())
    val playersState = _playersState.asStateFlow()

    private val _playerCreatorState = MutableStateFlow(PlayerCreatorState())

    val playerCreatorState = _playerCreatorState.asStateFlow()

    private val _roundState = MutableStateFlow(Round(idCurrentPlayer, currentRound))
    val roundState = _roundState.asStateFlow()

    private var playerRound = mutableMapOf<PlayerId, RoundMap>()

    fun resetData(deletePlayers: Boolean = true) {
        if (deletePlayers) _playersState.update { mutableMapOf() }
        _playerCreatorState.update { PlayerCreatorState() }
        if (deletePlayers) playerRound = mutableMapOf()
        else playerRound.mapValues { mutableMapOf<Int, Round>() }
        currentRound = 1
        idCurrentPlayer = 1
        updateRoundState()

    }

    private fun updateRoundState() {
        _roundState.update {
            playerRound[idCurrentPlayer]?.get(currentRound) ?: Round(idCurrentPlayer, currentRound)
        }
    }

    fun updatePlayerCreatorState(colorPicker: PlayerCreatorState) {
        _playerCreatorState.value = colorPicker
    }

    fun addPlayer(player: Player) {
        _playersState.update { it.apply { it[player.id] = player }}
        playerRound[player.id] = mutableMapOf()
        _playerCreatorState.update {
            it.copy(
                name = "",
                availableColors = it.availableColors.minus(player.color),
                chosenColor = it.availableColors.minus(player.color).firstOrNull()
            )
        }
    }

    fun updatePlayerRound(player: Player, round: Round) {
        _roundState.update { round }
        playerRound[player.id]?.set(round.id, round)
            ?: throw PlayerRoundDataStructureNotFound(player.id)
    }

    fun currentPlayer(): Player = _playersState.value[idCurrentPlayer]
        ?: throw PlayerRoundDataStructureNotFound(idCurrentPlayer)

    fun numPlayers(): Int = _playersState.value.size

    fun playersScore(): List<PlayerScore> = players()
        .map { PlayerScore(it, scoreUntil(it, currentRound)) }
        .sortedByDescending { it.score }
    private fun players() = _playersState.value.values.toList()

    private fun scoreUntil(player: Player, round: Int): Int =
        playerRound[player.id]?.filterValues { it.id <= round }?.values?.sumOf { it.points() }
            ?: throw RoundDataStructureNotFound(player)

    fun incrementPlayer() {
        idCurrentPlayer++
        updateRoundState()
    }

    fun decrementPlayer() {
        idCurrentPlayer--
        updateRoundState()
    }

    fun nextRound(firstRound: Boolean = false) {
        if (!firstRound) { idCurrentPlayer = 1; currentRound++ }
        updateRoundState()
    }
}

class RoundDataStructureNotFound(
    player: Player,
    message: String = "No round data structure found for player ${player.name}"
) : Exception(message)

class PlayerRoundDataStructureNotFound(
    playerId: PlayerId,
    message: String = "No player-round data structure found for player with id $playerId"
) : Exception(message)
