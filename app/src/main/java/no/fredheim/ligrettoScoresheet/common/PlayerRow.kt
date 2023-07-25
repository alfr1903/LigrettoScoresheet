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
import androidx.compose.ui.graphics.Color
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
            CircleHighlighted(color = player.color)
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

@Composable
fun CircleHighlighted(color: Color) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .background(color = Color.White, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(color = color, shape = CircleShape),
        )
    }
}

@Composable
fun PlayerScoreRow(
    number: Int,
    player: Player,
    round: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = "$number.",
        )
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color = player.color, shape = CircleShape)
        )
        Text(
            text = player.name,
            modifier = Modifier.padding(start = 8.dp)
        )
        if (number == 1) {
            Image(
                painter = painterResource(id = R.drawable.trophy),
                contentDescription = stringResource(R.string.trophy),
            )
        }
        Spacer(modifier = Modifier.weight(5f))
        Points(points = player.score(untilRound = round))
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

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerScoreRowPreview() {
    LigrettoScoresheetTheme {
        PlayerScoreRow(
            number = 1,
            player = Players.alex,
            round = 1,
            modifier = Modifier.padding(4.dp)
        )
    }
}
