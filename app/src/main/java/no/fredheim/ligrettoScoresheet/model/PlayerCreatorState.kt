package no.fredheim.ligrettoScoresheet.model

import no.fredheim.ligrettoScoresheet.ui.theme.PlayerColor

data class PlayerCreatorState(
    val name: String = "",
    val availableColors: Set<PlayerColor> = PlayerColor.values().toSet(),
    val chosenColor: PlayerColor? = availableColors.firstOrNull()
)
