package no.fredheim.ligrettoScoresheet.model

import androidx.compose.ui.graphics.Color
import no.fredheim.ligrettoScoresheet.ui.theme.PlayerColors

data class PlayersUiState(
    val name: String = "",
    val availableColors: Set<Color> = PlayerColors,
    val chosenColor: Color? = availableColors.firstOrNull()
)
