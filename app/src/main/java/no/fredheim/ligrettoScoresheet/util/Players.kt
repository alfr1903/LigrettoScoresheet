package no.fredheim.ligrettoScoresheet.util

import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.model.Round
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoLightBlue
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoOrange
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoPurple
import no.fredheim.ligrettoScoresheet.ui.theme.PlayerColors

object Players {
    val alex = Player(
        id = 1,
        name = "Alex",
        color = LigrettoOrange,
    )

    val thao = Player(
        id = 2,
        name = "Thao",
        color = LigrettoLightBlue,
    )

    val rikke = Player(
        id = 3,
        name = "Rikke",
        color = LigrettoPurple,
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