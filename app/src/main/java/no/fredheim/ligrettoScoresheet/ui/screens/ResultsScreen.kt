package no.fredheim.ligrettoScoresheet.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeDarkGreen
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeDarkRed
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.util.Players

private const val topRowWeight = 15f
private const val headlineWeight = 5f
private const val restOfScreenWeight = 80f

@Composable
fun ResultsScreen(
    players: List<Player>,
    round: Int,
    onNewRound: () -> Unit,
    onEnd: () -> Unit,
    modifier: Modifier = Modifier
) {
    val playersSorted = players.sortedByDescending { it.score(untilRound = round) }

    Image(
        painter = painterResource(id = R.drawable.ligrettoyellow_background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .weight(topRowWeight)
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
                .weight(headlineWeight),
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
        )
        Column(modifier = Modifier.weight(restOfScreenWeight)) {
            LazyColumn(modifier = Modifier
                .padding(
                start = dimensionResource(id = R.dimen.list_padding_start),
                top = 12.dp,
                end = dimensionResource(id = R.dimen.list_padding_end)
                )
            ) {
                itemsIndexed(playersSorted) { num, player ->
                    PlayerScoreRow(
                        round = round,
                        number = num + 1,
                        player = player,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { onEnd() },
                    colors = ButtonDefaults.buttonColors(containerColor = ThemeDarkRed)
                ) {
                    Text(text = stringResource(R.string.end_game))
                }
                Button(
                    onClick = { onNewRound() },
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
            players = Players.threePlayers(),
            round = 1,
            modifier = Modifier.padding(4.dp),
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
            players = Players.threePlayers(),
            round = 2,
            modifier = Modifier.padding(4.dp),
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
            players = Players.allPlayers(),
            round = 2,
            modifier = Modifier.padding(4.dp),
            onNewRound = { },
            onEnd = { }
        )
    }
}
