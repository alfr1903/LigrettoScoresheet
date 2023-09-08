package no.fredheim.ligrettoScoresheet.model

import androidx.annotation.DrawableRes
import no.fredheim.ligrettoScoresheet.service.CardImage
import no.fredheim.ligrettoScoresheet.ui.theme.PlayerColor

data class Player(
    val id: Int,
    val name: String = "",
    val color: PlayerColor,
    @DrawableRes val cardImageId: Int = CardImage.resolve(color)
)
