package no.fredheim.ligretto_scoresheet.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligretto_scoresheet.R
import no.fredheim.ligretto_scoresheet.Util
import no.fredheim.ligretto_scoresheet.model.Player
import no.fredheim.ligretto_scoresheet.ui.theme.Colors
import no.fredheim.ligretto_scoresheet.ui.theme.LigrettoScoresheetTheme

@Composable
fun PlayersScreen(
    players: List<Player>,
    availableColors: Set<Color>,
    onPlayerCreated: (Player) -> Unit,
    onWriteResultsButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = onWriteResultsButtonClick,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = stringResource(R.string.write_results_first_round))
        }
        LazyColumn {
            items(players) {
                PlayerRow(player = it)
            }
        }
        // PlayerAdder(availableColors)

    }
}

@Composable
fun PlayerRow(
    player: Player,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = player.color),
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Text(
            text = player.name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
    }
}

@Composable
fun PlayerAdder(
    availableColors: Set<Color>,
    modifier: Modifier = Modifier
) {
    val newPlayer by remember {
        mutableStateOf(Player(color = availableColors.first()))
    }

    Column(modifier = modifier) {
        PlayerRow(player = newPlayer)
    }

}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayersScreenPreview() {
    LigrettoScoresheetTheme {
        PlayersScreen(
            players = Util.players,
            availableColors = Colors,
            onPlayerCreated = {  },
            onWriteResultsButtonClick = { /*TODO*/ }
        )
    }

}