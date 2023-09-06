package no.fredheim.ligrettoScoresheet.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.model.PlayerScore
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.ui.theme.PreviewThemeBlue
import no.fredheim.ligrettoScoresheet.ui.theme.PreviewThemeOrange
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeColor
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

@Composable
fun PlayerScoreRow(
    number: Int,
    playerScore: PlayerScore,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(percent = 100),
        colors = CardDefaults.cardColors(
            containerColor = if (number == 1) ThemeColor.Orange.color else Color.Transparent
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                HighlightedCircle(color = playerScore.player.color)
                Text(
                    text = "$number. ${playerScore.player.name}",
                    modifier = Modifier.padding(start = 12.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
            Text(
                text = "${playerScore.score} points",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

    }
}

@Composable
fun PlayerEditScoreRow(
    number: Int,
    playerScore: PlayerScore,
    onScoreChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            HighlightedCircle(color = playerScore.player.color)
            Text(
                text = "$number. ${playerScore.player.name}",
                modifier = Modifier.padding(start = 12.dp),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            BasicTextField(
                value = playerScore.score.toString(),
                onValueChange = { onScoreChange() },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.extraSmall)
                    .width(60.dp),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                singleLine = true,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            )
            Text(
                text = "points",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PreviewThemeBlue,
    device = "id:pixel_4"
)
@Composable
fun PlayerNameRowPreview() {
    LigrettoScoresheetTheme {
        PlayerNameRow(number = 1, player = Players.alex, modifier = Modifier.fillMaxWidth())
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
            playerScore = PlayerScore(Players.thao, 12),
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
            playerScore = PlayerScore(Players.thao, 9),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PreviewThemeOrange,
    device = "id:pixel_4"
)
@Composable
fun PlayerEditScoreRowPreview() {
    LigrettoScoresheetTheme {
        PlayerEditScoreRow(
            number = 1,
            playerScore = PlayerScore(Players.alex, 9),
            onScoreChange = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
