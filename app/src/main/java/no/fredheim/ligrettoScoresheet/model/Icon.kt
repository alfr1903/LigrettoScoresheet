package no.fredheim.ligrettoScoresheet.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Icon(
    @DrawableRes val resId: Int,
    @StringRes val descriptionId: Int
)
