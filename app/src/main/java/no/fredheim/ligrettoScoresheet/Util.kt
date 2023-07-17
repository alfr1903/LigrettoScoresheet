package no.fredheim.ligrettoScoresheet

import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.model.Round
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoLightBlue
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoOrange

object Util {
    val alex = Player(
        number = 1,
        name = "Alex",
        color = LigrettoOrange,
        round = mutableMapOf(Pair(1, Round()))
    )

    val thao = Player(
        number = 2,
        name = "Thao",
        color = LigrettoLightBlue,
        round = mutableMapOf(Pair(2, Round()))
    )
    val players = listOf(alex, thao)
}

fun String.isDigitsOnly() = all(Char::isDigit) && isNotEmpty()
