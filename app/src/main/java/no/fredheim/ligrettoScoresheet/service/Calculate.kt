package no.fredheim.ligrettoScoresheet.service

import no.fredheim.ligrettoScoresheet.model.CardType
import no.fredheim.ligrettoScoresheet.util.isDigitsOnly

object Calculate {
    fun increment(value: String): String =
        when {
            value == "" || value == "0" -> "1"
            !value.isDigitsOnly() -> value
            else -> value.toInt().inc().toString()
        }

    fun decrement(value: String): String =
        when {
            value == "" || value == "0" -> value
            !value.isDigitsOnly() -> value
            else -> value.toInt().dec().toString()
        }

    @Suppress("MagicNumber")
    fun points(cardType: CardType, numCards: String): Int =
        when (cardType) {
            CardType.Ten -> {
                if (!numCards.isDigitsOnly()) 0 else numCards.toInt() * 2
            }
            CardType.Center -> {
                if (!numCards.isDigitsOnly()) 0 else numCards.toInt()
            }
            CardType.Minus -> {
                if (!numCards.isDigitsOnly()) 0 else numCards.toInt() * -2
            }
        }

    fun points(num10s: String, numCenter: String, numLigretto: String): Int =
        points(CardType.Ten, num10s) +
            points(CardType.Center, numCenter) +
            points(CardType.Minus, numLigretto)
}
