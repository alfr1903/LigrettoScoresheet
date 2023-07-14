package no.fredheim.ligretto_scoresheet.model

import androidx.compose.ui.graphics.Color
import no.fredheim.ligretto_scoresheet.ui.theme.Colors

data class GameState(
    val maxScore: String = "",
    val nameInput: String = "",
    val players: MutableMap<Int, Player> = mutableMapOf(),
    val colors: Set<Color> = Colors,
    val availableColors: Set<Color> = Colors,
    val chosenColor: Color? = availableColors.firstOrNull(),
)
