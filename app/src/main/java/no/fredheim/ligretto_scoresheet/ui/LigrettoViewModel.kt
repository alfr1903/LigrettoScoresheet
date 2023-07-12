package no.fredheim.ligretto_scoresheet.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import no.fredheim.ligretto_scoresheet.model.Player
import no.fredheim.ligretto_scoresheet.model.State

class LigrettoViewModel: ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun updateMaxScore(score: String) {
        _state.update { _state.value.copy(maxScore = score) }
    }

    fun updatePlayers(player: Player) {
        _state.update {
            val currentPlayers = _state.value.players
            _state.value.copy(players = currentPlayers.plus(player))
        }
    }
}