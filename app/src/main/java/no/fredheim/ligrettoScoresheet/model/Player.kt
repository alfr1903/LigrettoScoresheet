package no.fredheim.ligrettoScoresheet.model

import androidx.compose.ui.graphics.Color

data class Player(
    val id: Int,
    val name: String = "",
    val color: Color,
)
