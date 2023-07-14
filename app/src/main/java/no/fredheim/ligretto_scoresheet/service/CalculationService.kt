package no.fredheim.ligretto_scoresheet.service

import no.fredheim.ligretto_scoresheet.model.CardType

object CalculationService {
    fun calculate(cardType: CardType, numCards: Int): Int =
        when(cardType) {
            CardType.Ten -> numCards * 2
            CardType.Center -> numCards
            CardType.Ligretto -> numCards * -2
        }

    fun increment(value: String): String =
        if (!value.isDigitsOnly()) "1" else value.toInt().inc().toString()

    fun decrement(value: String): String =
        if (!value.isDigitsOnly() || value == "1") "" else value.toInt().dec().toString()

    private fun String.isDigitsOnly() = all(Char::isDigit) && isNotEmpty()
}