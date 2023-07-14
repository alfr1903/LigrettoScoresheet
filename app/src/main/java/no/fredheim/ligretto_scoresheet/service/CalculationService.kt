package no.fredheim.ligretto_scoresheet.service

import no.fredheim.ligretto_scoresheet.model.CardType

object CalculationService {
    fun calculate(cardType: CardType, numCards: Int): Int =
        when(cardType) {
            CardType.Ten -> numCards * 2
            CardType.Center -> numCards
            CardType.Ligretto -> numCards * -2
        }
}