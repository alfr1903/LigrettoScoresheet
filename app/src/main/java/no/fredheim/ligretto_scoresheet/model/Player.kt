package no.fredheim.ligretto_scoresheet.model

import androidx.compose.ui.graphics.Color

data class Player(
    val number: Int,
    val name: String = "",
    val color: Color,
    val score: Int = 0,
    val round: MutableMap<Int, Round> = mutableMapOf()
)
