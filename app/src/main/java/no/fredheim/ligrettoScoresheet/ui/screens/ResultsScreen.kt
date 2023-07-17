package no.fredheim.ligrettoScoresheet.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.Util
import no.fredheim.ligrettoScoresheet.common.PlayerScoreRow
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme

@Composable
fun ResultsScreen(
    players: List<Player>,
    onNextRoundButtonClick: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val playersSorted = players.sortedByDescending { it.score() }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.scoreboard),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Divider()
        LazyColumn {
            itemsIndexed(playersSorted) { num, player ->
                PlayerScoreRow(number = num + 1, player = player, modifier.padding(4.dp))
            }
        }
        Button(onClick = onNextRoundButtonClick) {
            Text(text = stringResource(R.string.next_round))
        }
        BackHandler(onBack = onBack)
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun ResultsScreenPreview() {
    LigrettoScoresheetTheme {
        ResultsScreen(
            players = Util.players,
            modifier = Modifier.padding(4.dp),
            onNextRoundButtonClick = { },
            onBack = { }
        )
    }
}
