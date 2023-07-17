package no.fredheim.ligrettoScoresheet.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import no.fredheim.ligrettoScoresheet.R

@Composable
fun Points(points: Int) {
    Text(text = stringResource(R.string.points, points))
}
