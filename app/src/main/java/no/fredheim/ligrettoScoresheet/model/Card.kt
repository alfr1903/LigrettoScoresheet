package no.fredheim.ligrettoScoresheet.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import no.fredheim.ligrettoScoresheet.R

abstract class Card(
    @DrawableRes open val typeImageId: Int,
    @StringRes open val typeDescriptionId: Int,
    @StringRes open val typeTextId: Int,
    open val type: CardType
)

data class TenCard(
    override val typeImageId: Int = R.drawable.tens_cards,
    override val typeDescriptionId: Int = R.string.number_10s_center,
    override val typeTextId: Int = R.string.tens,
    override val type: CardType = CardType.Ten
) : Card(typeImageId, typeDescriptionId, typeTextId, type)

data class CenterPileCard(
    override val typeImageId: Int = R.drawable.center_pile_cards,
    override val typeDescriptionId: Int = R.string.number_cards_center_pile,
    override val typeTextId: Int = R.string.center_pile,
    override val type: CardType = CardType.Center
) : Card(typeImageId, typeDescriptionId, typeTextId, type)

data class MinusPileCard(
    override val typeImageId: Int = R.drawable.minus_pile_cards,
    override val typeDescriptionId: Int = R.string.number_cards_minus_pile,
    override val typeTextId: Int = R.string.minus_pile,
    override val type: CardType = CardType.Minus
) : Card(typeImageId, typeDescriptionId, typeTextId, type)

enum class CardType {
    Ten,
    Center,
    Minus
}
