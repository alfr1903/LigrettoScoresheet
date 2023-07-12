package no.fredheim.ligretto_scoresheet.model

import androidx.compose.ui.graphics.Color
import androidx.core.text.isDigitsOnly
import no.fredheim.ligretto_scoresheet.ui.theme.Colors

data class State(
    val maxScore: String = "",
    val players: List<Player> = emptyList(),
    val colors: Set<Color> = Colors,
    val availableColors: Set<Color> = Colors
) {
    init {
        require(maxScore.isDigitsOnly())
    }
}
