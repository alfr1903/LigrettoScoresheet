package no.fredheim.ligrettoScoresheet.service

import no.fredheim.ligrettoScoresheet.isDigitsOnly
import no.fredheim.ligrettoScoresheet.model.CardType
import no.fredheim.ligrettoScoresheet.model.Player

object CalculationService {
    fun increment(value: String): String =
        if (!value.isDigitsOnly()) "1" else value.toInt().inc().toString()

    fun decrement(value: String): String =
        if (!value.isDigitsOnly() || value == "1") "" else value.toInt().dec().toString()

    fun points(cardType: CardType, numCards: String): Int =
        when (cardType) {
            CardType.Ten -> {
                if (!numCards.isDigitsOnly()) 0 else numCards.toInt() * 2
            }
            CardType.Center -> {
                if (!numCards.isDigitsOnly()) 0 else numCards.toInt()
            }
            CardType.Ligretto -> {
                if (!numCards.isDigitsOnly()) 0 else numCards.toInt() * -2
            }
        }

    fun points(num10s: String, numCenter: String, numLigretto: String): Int =
        points(CardType.Ten, num10s) +
        points(CardType.Center, numCenter) +
        points(CardType.Ligretto, numLigretto)
}
