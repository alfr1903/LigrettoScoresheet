package no.fredheim.ligrettoScoresheet.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.ui.theme.PreviewThemeOrange
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeOrange
import no.fredheim.ligrettoScoresheet.util.Players

@Composable
fun PlayerNameRow(
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
    backgroundColor = PreviewThemeOrange,
    device = "id:pixel_4"
)
@Composable
fun PlayerNameRowPreview() {
    LigrettoScoresheetTheme {
        PlayerNameRow(number = 1, player = Players.alex, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun PlayerScoreRow(
    number: Int,
    player: Player,
    score: Int,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(percent = 100),
        colors = CardDefaults.cardColors(
            containerColor = if (number == 1) ThemeOrange else Color.Transparent
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                HighlightedCircle(color = player.color)
                Text(
                    text = "$number. ${player.name}",
                    modifier = Modifier.padding(start = 12.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
            Text(
                text = "$score",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

    }
}

@Preview(
    showBackground = true,
    backgroundColor = PreviewThemeOrange,
    device = "id:pixel_4"
)
@Composable
fun PlayerScoreRowFirstRoundPreview() {
    LigrettoScoresheetTheme {
        PlayerScoreRow(
            number = 1,
            score = 12,
            player = Players.thao,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 4.dp, bottom = 4.dp, end = 16.dp)
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PreviewThemeOrange,
    device = "id:pixel_4"
)
@Composable
fun PlayerScoreRowSecondRoundPreview() {
    LigrettoScoresheetTheme {
        PlayerScoreRow(
            number = 2,
            score = 9,
            player = Players.thao,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


