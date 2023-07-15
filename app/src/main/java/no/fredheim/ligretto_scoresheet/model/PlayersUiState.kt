package no.fredheim.ligretto_scoresheet.model

import androidx.compose.ui.graphics.Color
import no.fredheim.ligretto_scoresheet.ui.theme.Colors

data class PlayersUiState(
    val name: String = "",
    val availableColors: Set<Color> = Colors,
    val chosenColor: Color? = availableColors.firstOrNull(),
)
