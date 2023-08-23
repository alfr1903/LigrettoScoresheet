package no.fredheim.ligrettoScoresheet.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.common.PlayerScoreRow
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.model.PlayerScore
import no.fredheim.ligrettoScoresheet.model.Round
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeDarkGreen
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeDarkRed
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.util.Players

private const val TOP_ROW_WEIGHT = 15f
private const val HEADLINE_WEIGHT = 5f
private const val REST_OF_SCREEN_WEIGHT = 80f

@Composable
fun ResultsScreen(
    playersScore: List<PlayerScore>,
    onHome: () -> Unit,
    onNewRound: () -> Unit,
    onEnd: () -> Unit,
    modifier: Modifier = Modifier
) {

    Image(
        painter = painterResource(id = R.drawable.ligrettoyellow_background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .weight(TOP_ROW_WEIGHT)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            Image(
                painter = painterResource(id = R.drawable.home),
                contentDescription = stringResource(R.string.home),
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.icon_screen_border_padding))
                    .size(dimensionResource(id = R.dimen.icon_size))
                    .clickable { onHome() }
            )
            Image(
                painter = painterResource(id = R.drawable.edit_square),
                contentDescription = stringResource(R.string.list_of_players),
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.icon_screen_border_padding))
                    .size(dimensionResource(id = R.dimen.icon_size))
            )
        }
        Text(
            text = stringResource(id = R.string.scoreboard),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(HEADLINE_WEIGHT),
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
        )
        Column(modifier = Modifier.weight(REST_OF_SCREEN_WEIGHT)) {
            LazyColumn(modifier = Modifier
                .padding(
                start = dimensionResource(id = R.dimen.list_padding_start),
                top = 12.dp,
                end = dimensionResource(id = R.dimen.list_padding_end)
                )
            ) {
                itemsIndexed(playersScore) { num, playerScore ->
                    PlayerScoreRow(
                        number = num + 1,
                        player = playerScore.player,
                        score = playerScore.score,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.button_bottom_padding)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { onEnd() },
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(
                                id = R.dimen.button_short_padding_horizontal
                            )
                        )
                        .width(dimensionResource(id = R.dimen.button_short_width)),
                    colors = ButtonDefaults.buttonColors(containerColor = ThemeDarkRed)
                ) {
                    Text(text = stringResource(R.string.end_game))
                }
                Button(
                    onClick = { onNewRound() },
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(
                            id = R.dimen.button_short_padding_horizontal)
                        )
                        .width(dimensionResource(id = R.dimen.button_short_width)),
                    colors = ButtonDefaults.buttonColors(containerColor = ThemeDarkGreen)
                ) {
                    Text(text = stringResource(R.string.new_round))
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun ResultsScreenRound1Preview() {
    LigrettoScoresheetTheme {
        ResultsScreen(
            Players.threePlayers().map { PlayerScore(it, 0) },
            onHome = { },
            onNewRound = { },
            onEnd = { }
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun ResultsScreenRound2Preview() {
    LigrettoScoresheetTheme {
        ResultsScreen(
            Players.threePlayers().map { PlayerScore(it, 0) },
            onHome = { },
            onNewRound = { },
            onEnd = { }
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun ResultsScreen12PlayersPreview() {
    LigrettoScoresheetTheme {
        ResultsScreen(
            Players.allPlayers().map { PlayerScore(it, 0) },
            onHome = { },
            onNewRound = { },
            onEnd = { }
        )
    }
}
