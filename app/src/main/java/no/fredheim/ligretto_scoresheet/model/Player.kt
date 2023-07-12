package no.fredheim.ligretto_scoresheet.model

import androidx.compose.ui.graphics.Color

data class Player(
    val name: String = "",
    val color: Color,
    val score: Int = 0,
)
