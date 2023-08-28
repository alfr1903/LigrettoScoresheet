package no.fredheim.ligrettoScoresheet.common

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.model.Player

@Composable
fun Headline(
    @StringRes textId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(textId),
        modifier = modifier,
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
fun HeadlineBold(
    @StringRes textId: Int,
    modifier: Modifier = Modifier,
    arg: Int? = null,
) {
    Text(
        text = arg?.let { stringResource(textId, it) } ?: stringResource(textId),
        modifier = modifier,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
fun BodySmall(
    @StringRes textId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(textId),
        modifier = modifier,
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall
    )
}

@Composable
fun PlayerCardText(
    player: Player,
    modifier: Modifier = Modifier
) {
    Text(
        text = player.name,
        modifier = modifier,
        color = player.color.color,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge
    )
}