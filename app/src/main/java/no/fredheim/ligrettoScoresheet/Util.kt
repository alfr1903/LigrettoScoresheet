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
        round = mutableMapOf(
            Pair(1, Round(num10s = "0", numCenter = "8", numLigretto = "5")),
            Pair(2, Round(num10s = "1", numCenter = "14", numLigretto = "0"))
        )
    )

    val thao = Player(
        number = 2,
        name = "Thao",
        color = LigrettoLightBlue,
        round = mutableMapOf(
            Pair(1, Round(num10s = "2", numCenter = "15", numLigretto = "0")),
            Pair(2, Round(num10s = "0", numCenter = "6", numLigretto = "8")),

        )
    )
    val players = listOf(alex, thao)
}

fun String.isDigitsOnly() = all(Char::isDigit) && isNotEmpty()
