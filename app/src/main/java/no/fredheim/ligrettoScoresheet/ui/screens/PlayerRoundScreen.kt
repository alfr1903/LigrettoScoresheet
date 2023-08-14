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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
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
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeBlue
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeOrange
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeRed
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.util.Players

private const val topRowWeight = 22f
private const val cardCountersColumnWeight = 50f
private const val navigationRowWeight = 18f
private const val restOfScreenWeight = 10f



@Composable
fun PlayerRoundScreen(
    player: Player,
    round: Round,
    numPlayers: Int,
    onNext: (Round) -> Unit,
    onResults: (Round) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentNum10s by remember { mutableStateOf(round.num10s) }
    var currentNumCenter by remember { mutableStateOf(round.numCenter) }
    var currentNumMinus by remember { mutableStateOf(round.numLigretto) }

    val firstPlayer = player.number == 1
    val lastPlayer = player.number == numPlayers

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
        Column(modifier = Modifier.weight(cardCountersColumnWeight)) {
            CardCounterRow(
                cardTypeImageId = R.drawable.tens_cards,
                cardTypeDescriptionId = R.string.number_10s_center,
                cardTypeTextId = R.string.tens,
                cardType = CardType.Ten,
                value = currentNum10s,
                onValueChange = { currentNum10s = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 24.dp)
            )
            CardCounterRow(
                cardTypeImageId = R.drawable.center_pile_cards,
                cardTypeDescriptionId = R.string.number_cards_center_excluding_10s,
                cardTypeTextId = R.string.center_pile,
                cardType = CardType.Center,
                value = currentNumCenter,
                onValueChange = { currentNumCenter = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 24.dp)
            )
            CardCounterRow(
                cardTypeImageId = R.drawable.minus_pile_cards,
                cardTypeDescriptionId = R.string.number_cards_minus_pile,
                cardTypeTextId = R.string.minus_pile,
                cardType = CardType.Minus,
                value = currentNumMinus,
                onValueChange = { currentNumMinus = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 24.dp)
            )

        }
        Row(
            modifier = Modifier
                .weight(navigationRowWeight)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { onBack() },
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.button_short_padding_horizontal))
                    .width(dimensionResource(id = R.dimen.button_short_width))
                    .alpha(if (firstPlayer) 0f else 1f),
                colors = ButtonDefaults.buttonColors(containerColor = ThemeBlue),
            ) {
                Text(text = stringResource(id = R.string.prev_player))
            }
            Text(
                text = "${player.number}/$numPlayers",
                color = Color.White,
                textAlign = TextAlign.Center

            )
            Button(
                onClick = { onNext(
                    Round(round.number, currentNum10s, currentNumCenter, currentNumMinus)
                ) },
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.button_short_padding_horizontal))
                    .width(dimensionResource(id = R.dimen.button_short_width))
                    .alpha(if (lastPlayer) 0f else 1f),
                colors = ButtonDefaults.buttonColors(containerColor = ThemeRed),
            ) {
                Text(text = stringResource(id = R.string.next_player))
            }
        }
        Column(
            modifier = Modifier
                .weight(restOfScreenWeight)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { onResults(
                    Round(round.number, currentNum10s, currentNumCenter, currentNumMinus)
                ) },
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.button_bottom_padding))
                    .width(dimensionResource(id = R.dimen.button_long_width)),
                colors = ButtonDefaults.buttonColors(containerColor = ThemeOrange),
            ) {
                Text(text = stringResource(R.string.see_results))
            }

        }
    }
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
        modifier = modifier,
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
fun PlayerRoundFirstRoundFirstPlayerNoDataScreenPreview() {
    LigrettoScoresheetTheme {
        PlayerRoundScreen(
            player = Players.alex,
            round = Round(1),
            numPlayers = 3,
            onNext = { },
            onResults = { },
            onBack = { }
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundFirstRoundFirstPlayerScreenPreview() {
    LigrettoScoresheetTheme {
        PlayerRoundScreen(
            player = Players.alex,
            round = Players.alex.round[1]!!,
            numPlayers = 3,
            onNext = { },
            onResults = { },
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
        PlayerRoundScreen(
            player = Players.alex,
            round = Players.alex.round[2]!!,
            numPlayers = 3,
            onNext = { },
            onResults = { },
            onBack = { }
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundMiddlePlayerScreenPreview() {
    LigrettoScoresheetTheme {
        PlayerRoundScreen(
            player = Players.thao,
            round = Players.thao.round[2]!!,
            numPlayers = 3,
            onNext = { },
            onResults = { },
            onBack = { }
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundMiddlePlayerTwelvePlayersScreenPreview() {
    LigrettoScoresheetTheme {
        PlayerRoundScreen(
            player = Players.thao.copy(number = 11),
            round = Players.thao.round[2]!!,
            numPlayers = 12,
            onNext = { },
            onResults = { },
            onBack = { }
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundLastPlayerScreenPreview() {
    LigrettoScoresheetTheme {
        PlayerRoundScreen(
            player = Players.rikke,
            round = Players.rikke.round[2]!!,
            numPlayers = 3,
            onNext = { },
            onResults = { },
            onBack = { }
        )
    }
}
