package no.fredheim.ligrettoScoresheet.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.model.CardType
import no.fredheim.ligrettoScoresheet.service.Calculate

@Composable
fun Points(
    cardType: CardType,
    numCards: String,
    modifier: Modifier = Modifier
) {
    val points = Calculate.points(cardType, numCards)
    Text(
        text = stringResource(R.string.points, points),
        modifier = modifier
    )
}
