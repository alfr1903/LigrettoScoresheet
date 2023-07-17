package no.fredheim.ligrettoScoresheet.model

import androidx.compose.ui.graphics.Color
import no.fredheim.ligrettoScoresheet.ui.theme.Colors

data class PlayersUiState(
    val name: String = "",
    val availableColors: Set<Color> = Colors,
    val chosenColor: Color? = availableColors.firstOrNull()
)
