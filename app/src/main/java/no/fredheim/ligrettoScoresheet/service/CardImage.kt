package no.fredheim.ligrettoScoresheet.service

import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.ui.theme.PlayerColor

object CardImage {
    fun resolve(color: PlayerColor): Int =
        when (color) {
            PlayerColor.LightPink -> R.drawable.lightpinkcard_name
            PlayerColor.Black -> R.drawable.blackcard_name
            PlayerColor.LightRed -> R.drawable.lightredcard_name
            PlayerColor.Blue -> R.drawable.bluecard_name
            PlayerColor.Orange -> R.drawable.orangecard_name
            PlayerColor.LightBlue -> R.drawable.lightbluecard_name
            PlayerColor.Gray -> R.drawable.graycard_name
            PlayerColor.Yellow -> R.drawable.yellowcard_name
            PlayerColor.Green -> R.drawable.greencard_name
            PlayerColor.Brown -> R.drawable.browncard_name
            PlayerColor.Purple -> R.drawable.purplecard_name
            PlayerColor.Pink -> R.drawable.pinkcard_name
        }
}
