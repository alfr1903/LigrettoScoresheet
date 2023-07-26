package no.fredheim.ligrettoScoresheet.util

import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.model.Round
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoLightBlue
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoOrange
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoPurple
import no.fredheim.ligrettoScoresheet.ui.theme.PlayerColors

object Players {
    val alex = Player(
        number = 1,
        name = "Alex",
        color = LigrettoOrange,
        round = mutableMapOf(
            Pair(1, Round(number = 1, num10s = "0", numCenter = "8", numLigretto = "5")),
            Pair(2, Round(number = 2, num10s = "1", numCenter = "14", numLigretto = "0"))
        )
    )

    val thao = Player(
        number = 2,
        name = "Thao",
        color = LigrettoLightBlue,
        round = mutableMapOf(
            Pair(1, Round(number = 1, num10s = "2", numCenter = "15", numLigretto = "0")),
            Pair(2, Round(number = 2, num10s = "0", numCenter = "6", numLigretto = "8")),
        )
    )

    val rikke = Player(
        number = 3,
        name = "Rikke",
        color = LigrettoPurple,
        round = mutableMapOf(
            Pair(1, Round(number = 1, num10s = "2", numCenter = "15", numLigretto = "1")),
            Pair(2, Round(number = 2, num10s = "1", numCenter = "12", numLigretto = "1")),
        )
    )

    fun threePlayers() = listOf(alex, thao, rikke)

    fun allPlayers(): List<Player> {
        val names = ArrayDeque(
            listOf(
                "Alex", "Thao", "Rikke", "OJ", "Lili", "Andrea",
                "Petter", "Markus", "Maria", "Carlo", "Beate", "Ingrid"
            )
        )

        return PlayerColors.mapIndexed { i, color -> Player(i, names.removeFirst(), color) }

    }
}