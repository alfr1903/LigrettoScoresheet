package no.fredheim.ligrettoScoresheet.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.Util
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.service.CalculationService
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme

@Composable
fun PlayerRow(
    number: Int,
    player: Player,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.player, number),
            modifier = Modifier.weight(3f)
        )
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color = player.color, shape = CircleShape)
        )
        Text(
            text = player.name,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )
    }
}

@Composable
fun PlayerScoreRow(
    number: Int,
    player: Player,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = "$number.",
            modifier = Modifier
                .weight(1f)
        )
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color = player.color, shape = CircleShape)
        )
        Text(
            text = player.name,
            modifier = Modifier
                .weight(5f)
                .padding(start = 8.dp)
        )
        Points(points = CalculationService.score(player))
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRowPreview() {
    LigrettoScoresheetTheme {
        PlayerRow(number = 1, player = Util.alex, modifier = Modifier.padding(4.dp))
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerScoreRowPreview() {
    LigrettoScoresheetTheme {
        PlayerScoreRow(number = 1, player = Util.alex, modifier = Modifier.padding(4.dp))
    }
}