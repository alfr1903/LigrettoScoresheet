package no.fredheim.ligrettoScoresheet.ui

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.model.ColorPickerState
import no.fredheim.ligrettoScoresheet.model.PlayerScore
import no.fredheim.ligrettoScoresheet.model.Round

class LigrettoViewModel : ViewModel() {
    private val _playersState = MutableStateFlow(mutableMapOf<Int, Player>())
    val playersState = _playersState.asStateFlow()

    private val _colorPickerState = MutableStateFlow(ColorPickerState())
    val colorPickerState = _colorPickerState.asStateFlow()

    private var rounds = mutableMapOf<Int, MutableMap<Int, Round>>()

    private var currentRound = 1

    var currentPlayerIndex = 1
        private set

    fun reset() {
        _playersState.update { mutableMapOf() }
        _colorPickerState.update { ColorPickerState() }
        rounds = mutableMapOf()
        currentRound = 1
        currentPlayerIndex = 1
    }

    fun resetRoundData() { rounds = mutableMapOf() }

    fun addPlayer(player: Player) {
        _playersState.value[player.id] = player
        rounds[player.id] = mutableMapOf()
        _colorPickerState.update {
            it.copy(
                name = "",
                availableColors = it.availableColors.minus(player.color),
                chosenColor = it.availableColors.minus(player.color).first()
            )
        }
    }

    fun updateName(name: String) { _colorPickerState.update { it.copy(name = name) } }

    fun updateChosenColor(color: Color) {
        _colorPickerState.update { it.copy(chosenColor = color) }
    }
    fun nextRound(firstRound: Boolean) {
        if (!firstRound) {
            currentPlayerIndex = 1
            currentRound++
        }
        (1..numPlayers()).forEach {
            if(!rounds[it]!!.containsKey(currentRound))
                rounds[it]!![currentRound] = Round(playerId = it, id = currentRound)
        }
    }

    fun currentPlayer(): Player = _playersState.value[currentPlayerIndex]!!
    fun currentRound(player: Player): Round = rounds[player.id]!![currentRound]!!

    fun addRound(round: Round, player: Player) { rounds[player.id]!![currentRound] = round }

    fun numPlayers(): Int = _playersState.value.size

    fun playersScore(): List<PlayerScore> = players()
        .map { PlayerScore(it, scoreUntil(it.id, currentRound)) }
        .sortedByDescending { it.score }

    private fun players() = _playersState.value.values.toList()
    private fun scoreUntil(playerId: Int, round: Int): Int =
        rounds[playerId]!!.filterValues { it.id <= round }.values.sumOf { it.points() }

    fun incrementPlayer() {
        currentPlayerIndex++
    }

    fun decrementPlayer() {
        currentPlayerIndex--
    }
}
