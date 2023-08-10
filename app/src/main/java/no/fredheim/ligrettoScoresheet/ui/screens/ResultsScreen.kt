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
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.ui.theme.ButtonDarkBlue
import no.fredheim.ligrettoScoresheet.ui.theme.ButtonDarkGreen
import no.fredheim.ligrettoScoresheet.ui.theme.ButtonDarkRed
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.util.Players

private const val topRowWeight = 15f
private const val headlineWeight = 20f
private const val restOfScreenWeight = 65f

@Composable
fun ResultsScreen(
    players: List<Player>,
    round: Int,
    onNextRoundButtonClick: () -> Unit,
    onBack: () -> Unit,
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
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonDarkRed)
                ) {
                    Text(text = stringResource(R.string.end_game))
                }
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonDarkGreen)
                ) {
                    Text(text = stringResource(R.string.new_game))
                }
            }
        }
    }
    /*
    val playersSorted = players.sortedByDescending { it.score(untilRound = round) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.scoreboard),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = stringResource(R.string.after_round, round) + if (round > 1) "s" else "")
        Divider()
        LazyColumn {
            itemsIndexed(playersSorted) { num, player ->
                PlayerScoreRow(
                    number = num + 1,
                    player = player,
                    round = round,
                    modifier.padding(4.dp)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onBack) {
                Text(text = stringResource(R.string.prev_player))
            }
            Button(onClick = onNextRoundButtonClick) {
                Text(text = stringResource(R.string.next_round))
            }
        }
    }
    BackHandler(onBack = onBack)*/
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
            onNextRoundButtonClick = { },
            onBack = { }
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
            onNextRoundButtonClick = { },
            onBack = { }
        )
    }
}
