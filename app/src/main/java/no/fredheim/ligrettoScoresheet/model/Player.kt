package no.fredheim.ligrettoScoresheet.model

import androidx.compose.ui.graphics.Color

data class Player(
    val number: Int,
    val name: String = "",
    val color: Color,
    val round: MutableMap<Int, Round> = mutableMapOf()
) {

}
