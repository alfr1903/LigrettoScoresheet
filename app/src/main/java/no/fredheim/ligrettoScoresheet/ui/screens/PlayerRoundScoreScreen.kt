package no.fredheim.ligrettoScoresheet.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.model.CardType
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.model.Round
import no.fredheim.ligrettoScoresheet.service.Calculate
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.util.Players

private const val topRowWeight = 22f
private const val cardCountersColumnWeight = 46f
private const val restOfScreenWeight = 32f



@Composable
fun PlayerRoundScoreScreen(
    player: Player,
    round: Round,
    numPlayers: Int,
    onNextPlayerButtonClick: (Round) -> Unit,
    onResultsButtonClick: (Round) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentNum10s by remember { mutableStateOf(round.num10s) }
    var currentNumCenter by remember { mutableStateOf(round.numCenter) }
    var currentNumMinus by remember { mutableStateOf(round.numLigretto) }

    Image(
        painter = painterResource(id = R.drawable.ligrettogreen_background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .weight(topRowWeight)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom,
        ) {
            Image(
                painter = painterResource(id = R.drawable.home),
                contentDescription = stringResource(R.string.home)
            )
            Image(
                painter = painterResource(id = R.drawable.blackcard_name),
                contentDescription = stringResource(R.string.player_card),
            )
            Image(
                painter = painterResource(id = R.drawable.list),
                contentDescription = stringResource(R.string.list_of_players),
            )
        }
        Column(
            modifier = Modifier
                .weight(cardCountersColumnWeight)
                .padding(top = 12.dp)
        ) {
            CardCounterRow(
                cardTypeImageId = R.drawable.tens_cards,
                cardTypeDescriptionId = R.string.number_10s_center,
                cardTypeTextId = R.string.tens,
                cardType = CardType.Ten,
                value = currentNum10s,
                onValueChange = { currentNum10s = it },
                modifier = Modifier.weight(1f)
            )
            CardCounterRow(
                cardTypeImageId = R.drawable.center_pile_cards,
                cardTypeDescriptionId = R.string.number_cards_center_excluding_10s,
                cardTypeTextId = R.string.center_pile,
                cardType = CardType.Center,
                value = currentNumCenter,
                onValueChange = { currentNumCenter = it },
                modifier = Modifier.weight(1f)
            )
            CardCounterRow(
                cardTypeImageId = R.drawable.minus_pile_cards,
                cardTypeDescriptionId = R.string.number_cards_minus_pile,
                cardTypeTextId = R.string.minus_pile,
                cardType = CardType.Minus,
                value = currentNumMinus,
                onValueChange = { currentNumMinus = it },
                modifier = Modifier.weight(1f)
            )

        }
        Spacer(modifier = Modifier.weight(restOfScreenWeight))
    }
    /*

    val lastPlayer = player.number == numPlayers

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 60.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(240.dp)
                .height(40.dp)
                .background(color = player.color, shape = CircleShape)
        ) {
            Text(
                text = player.name
            )
        }
        Divider(Modifier.padding(top = 32.dp, bottom = 32.dp))
        CardPointsCalculator(
            image = R.drawable.playing_cards,
            imageDescription = R.string.playing_cards,
            description = R.string.tens,
            numCards = currentNum10s,
            points = CalculationService.points(CardType.Ten, currentNum10s),
            onNumCardsChange = { currentNum10s = it }
        )
        CardPointsCalculator(
            image = R.drawable.playing_cards,
            imageDescription = R.string.playing_cards,
            description = R.string.center,
            numCards = currentNumCenter,
            points = CalculationService.points(CardType.Center, currentNumCenter),
            onNumCardsChange = { currentNumCenter = it }
        )
        CardPointsCalculator(
            image = R.drawable.playing_cards,
            imageDescription = R.string.playing_cards,
            description = R.string.ligretto,
            numCards = currentNumLigretto,
            points = CalculationService.points(CardType.Ligretto, currentNumLigretto),
            onNumCardsChange = { currentNumLigretto = it }
        )
        Text(
            text = stringResource(
                R.string.round_points,
                CalculationService.points(currentNum10s, currentNumCenter, currentNumLigretto)
            )
        )
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onBack) {
                if (player.number == 1) {
                    if (round.number == 1) Text(text = stringResource(R.string.abort_game))
                    else Text(text = stringResource(R.string.last_round_results))
                }
                else Text(text = stringResource(R.string.prev_player))
            }
            Button(
                onClick = {
                    val roundData = Round(
                        number = round.number,
                        num10s = currentNum10s,
                        numCenter = currentNumCenter,
                        numLigretto = currentNumLigretto
                    )
                    if (!lastPlayer) onNextPlayerButtonClick(roundData)
                    else onResultsButtonClick(roundData)
                },
            ) {
                if(!lastPlayer) Text(text = stringResource(R.string.next_player))
                else Text(text = stringResource(R.string.results))
            }
        }
        Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text(
                text = "${player.number}/$numPlayers",
                color = Color.Gray
            )
        }
    }
    BackHandler(onBack = onBack)
     */
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardCounterRow(
    @DrawableRes cardTypeImageId: Int,
    @StringRes cardTypeDescriptionId: Int,
    @StringRes cardTypeTextId: Int,
    cardType: CardType,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(cardTypeImageId),
                contentDescription = stringResource(cardTypeDescriptionId),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(cardTypeTextId),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.subtract),
                contentDescription = stringResource(id = R.string.subtract),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            )
            TextField(
                value = value,
                onValueChange = { onValueChange(it) },
                placeholder = { Text(text = "0") },
                modifier = Modifier.weight(3f),
                singleLine = true
            )
            Image(
                painter = painterResource(id = R.drawable.add),
                contentDescription = stringResource(id = R.string.add),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            )
        }
        Text(
            text = stringResource(id = R.string.points, Calculate.points(cardType, value)),
            modifier = Modifier
                .weight(1f)
                .padding(end = 24.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundScoreFirstRoundFirstPlayerNoDataScreenPreview() {
    LigrettoScoresheetTheme {
        PlayerRoundScoreScreen(
            player = Players.alex,
            round = Round(1),
            numPlayers = 3,
            onNextPlayerButtonClick = { },
            onResultsButtonClick = { },
            onBack = { }
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundScoreFirstRoundFirstPlayerScreenPreview() {
    LigrettoScoresheetTheme {
        PlayerRoundScoreScreen(
            player = Players.alex,
            round = Players.alex.round[1]!!,
            numPlayers = 3,
            onNextPlayerButtonClick = { },
            onResultsButtonClick = { },
            onBack = { }
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundScoreSecondRoundFirstPlayerScreenPreview() {
    LigrettoScoresheetTheme {
        PlayerRoundScoreScreen(
            player = Players.alex,
            round = Players.alex.round[2]!!,
            numPlayers = 3,
            onNextPlayerButtonClick = { },
            onResultsButtonClick = { },
            onBack = { }
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundScoreMiddlePlayerScreenPreview() {
    LigrettoScoresheetTheme {
        PlayerRoundScoreScreen(
            player = Players.thao,
            round = Players.thao.round[2]!!,
            numPlayers = 3,
            onNextPlayerButtonClick = { },
            onResultsButtonClick = { },
            onBack = { }
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundScoreLastPlayerScreenPreview() {
    LigrettoScoresheetTheme {
        PlayerRoundScoreScreen(
            player = Players.rikke,
            round = Players.rikke.round[2]!!,
            numPlayers = 3,
            onNextPlayerButtonClick = { },
            onResultsButtonClick = { },
            onBack = { }
        )
    }
}
