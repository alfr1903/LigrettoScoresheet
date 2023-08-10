package no.fredheim.ligrettoScoresheet.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.util.Players

@Composable
fun PlayerRow(
    number: Int,
    player: Player,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            HighlightedCircle(color = player.color)
            Text(
                text = stringResource(R.string.player, number),
                modifier = Modifier.padding(start = 12.dp),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        Text(
            text = player.name,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF0f6bb9,
    device = "id:pixel_4"
)
@Composable
fun PlayerRowPreview() {
    LigrettoScoresheetTheme {
        PlayerRow(number = 1, player = Players.alex, modifier = Modifier.fillMaxWidth())
    }
}
