package no.fredheim.ligrettoScoresheet.util

import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.ui.theme.PlayerColor

object Players {
    val alex = Player(
        id = 1,
        name = "Alex",
        color = PlayerColor.Orange
    )

    val thao = Player(
        id = 2,
        name = "Thao",
        color = PlayerColor.LightBlue
    )

    val rikke = Player(
        id = 3,
        name = "Rikke",
        color = PlayerColor.Purple
    )

    fun threePlayers() = listOf(alex, thao, rikke)

    fun allPlayers(): List<Player> {
        val names = ArrayDeque(
            listOf(
                "Alex", "Thao", "Rikke", "OJ", "Lili", "Andrea",
                "Petter", "Markus", "Maria", "Carlo", "Beate", "Ingrid"
            )
        )

        return PlayerColor.values().mapIndexed { i, color -> Player(i, names.removeFirst(), color) }
    }
}
