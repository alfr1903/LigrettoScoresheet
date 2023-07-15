package no.fredheim.ligretto_scoresheet.service

import no.fredheim.ligretto_scoresheet.isDigitsOnly
import no.fredheim.ligretto_scoresheet.model.CardType

object CalculationService {
    fun increment(value: String): String =
        if (!value.isDigitsOnly()) "1" else value.toInt().inc().toString()

    fun decrement(value: String): String =
        if (!value.isDigitsOnly() || value == "1") "" else value.toInt().dec().toString()

    fun points(cardType: CardType, numCards: String): String =
        when(cardType) {
            CardType.Ten -> {
                if(!numCards.isDigitsOnly()) "0" else (numCards.toInt() * 2).toString()
            }
            CardType.Center -> {
                if(!numCards.isDigitsOnly()) "0" else (numCards.toInt() * 2).toString()
            }
            CardType.Ligretto -> {
                if(!numCards.isDigitsOnly()) "0" else (numCards.toInt() * -2).toString()
            }
        }
}

