package no.fredheim.ligretto_scoresheet.model

import androidx.compose.ui.graphics.Color
import no.fredheim.ligretto_scoresheet.ui.theme.Colors

data class GameState(
    val maxScore: String = "",
    val players: MutableMap<Int, Player> = mutableMapOf(),
    val playersUiState: PlayersUiState = PlayersUiState()
)
