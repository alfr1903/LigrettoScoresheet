package no.fredheim.ligretto_scoresheet

import no.fredheim.ligretto_scoresheet.model.Player
import no.fredheim.ligretto_scoresheet.model.Round
import no.fredheim.ligretto_scoresheet.ui.theme.LigrettoLightBlue
import no.fredheim.ligretto_scoresheet.ui.theme.LigrettoOrange

object Util {
    val alex = Player(
        number = 1,
        name = "Alex",
        color = LigrettoOrange,
        round = mutableMapOf(Pair(1, Round()))
    )

    private val thao = Player(2, "Thao", LigrettoLightBlue)
    val players = listOf(alex, thao)
}

fun String.isDigitsOnly() = all(Char::isDigit) && isNotEmpty()