package no.fredheim.ligrettoScoresheet.service

import no.fredheim.ligrettoScoresheet.isDigitsOnly
import no.fredheim.ligrettoScoresheet.model.CardType
import no.fredheim.ligrettoScoresheet.model.Player

object CalculationService {
    fun increment(value: String): String =
        if (!value.isDigitsOnly()) "1" else value.toInt().inc().toString()

    fun decrement(value: String): String =
        if (!value.isDigitsOnly() || value == "1") "" else value.toInt().dec().toString()

    fun points(cardType: CardType, numCards: String): String =
        when (cardType) {
            CardType.Ten -> {
                if (!numCards.isDigitsOnly()) "0" else (numCards.toInt() * 2).toString()
            }
            CardType.Center -> {
                if (!numCards.isDigitsOnly()) "0" else (numCards.toInt()).toString()
            }
            CardType.Ligretto -> {
                if (!numCards.isDigitsOnly()) "0" else (numCards.toInt() * -2).toString()
            }
        }

    fun points(num10s: String, numCenter: String, numLigretto: String): Int {
        val tens = points(CardType.Ten, num10s)
        val center = points(CardType.Center, numCenter)
        val ligretto = points(CardType.Ligretto, numLigretto)
        return tens.toInt() + center.toInt() + ligretto.toInt()
    }

    fun score(player: Player): String = player.round.values.sumOf { it.points() }.toString()
}
