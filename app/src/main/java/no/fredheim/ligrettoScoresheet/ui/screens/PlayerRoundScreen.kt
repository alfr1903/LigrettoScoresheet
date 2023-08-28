package no.fredheim.ligrettoScoresheet.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.common.Background
import no.fredheim.ligrettoScoresheet.common.HeadlineBold
import no.fredheim.ligrettoScoresheet.common.IconsRow
import no.fredheim.ligrettoScoresheet.common.PlayerCardText
import no.fredheim.ligrettoScoresheet.common.cardCounterRowModifier
import no.fredheim.ligrettoScoresheet.common.topIconRowModifier
import no.fredheim.ligrettoScoresheet.model.CardType
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.model.Round
import no.fredheim.ligrettoScoresheet.service.Calculate
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeColor
import no.fredheim.ligrettoScoresheet.util.Players

@Composable
fun PlayerRoundScreen(
    player: Player,
    round: Round,
    onRoundChange: (Player, Round) -> Unit,
    numPlayers: Int,
    onHome: () -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onResults: () -> Unit,
    modifier: Modifier = Modifier
) {
    val firstPlayer = player.id == 1
    val lastPlayer = player.id == numPlayers


    Background(resId = R.drawable.ligrettogreen_background)
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = player.cardImageId),
            contentDescription = "",
            modifier = Modifier.height(140.dp),
        )
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconsRow(
            leftIconResId = R.drawable.home,
            lefIconDescriptionId = R.string.home,
            onLeft = { onHome() },
            rightIconResId = R.drawable.list,
            rightIconDescriptionId = R.string.list_of_players,
            onRight = { },
            modifier = Modifier.topIconRowModifier()
        )
        PlayerCardText(player = player)
        HeadlineBold(
            textId = R.string.round,
            modifier = Modifier.padding(top = 20.dp),
            arg = round.id
        )
        CardCounterRow(
            cardTypeImageId = R.drawable.tens_cards,
            cardTypeDescriptionId = R.string.number_10s_center,
            cardTypeTextId = R.string.tens,
            cardType = CardType.Ten,
            value = round.num10s,
            onValueChange = { onRoundChange(player, round.copy(num10s = it)) },
            modifier = Modifier.cardCounterRowModifier()
        )
        CardCounterRow(
            cardTypeImageId = R.drawable.center_pile_cards,
            cardTypeDescriptionId = R.string.number_cards_center_excluding_10s,
            cardTypeTextId = R.string.center_pile,
            cardType = CardType.Center,
            value = round.numCenter,
            onValueChange = { onRoundChange(player, round.copy(numCenter = it)) },
            modifier = Modifier.cardCounterRowModifier()
        )
        CardCounterRow(
            cardTypeImageId = R.drawable.minus_pile_cards,
            cardTypeDescriptionId = R.string.number_cards_minus_pile,
            cardTypeTextId = R.string.minus_pile,
            cardType = CardType.Minus,
            value = round.numMinus,
            onValueChange = { onRoundChange(player, round.copy(numMinus = it)) },
            modifier = Modifier.cardCounterRowModifier()
        )
        Text(
            text = stringResource(id = R.string.round_points, round.points()),
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold
        )
        PlayerNavigationRow(
            onPrevious = onPrevious,
            firstPlayer = firstPlayer,
            player = player,
            numPlayers = numPlayers,
            onNext = onNext,
            lastPlayer = lastPlayer,
            modifier = Modifier.padding(top = 52.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { onResults() },
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.screen_bottom_button_bottom_padding))
                .width(dimensionResource(id = R.dimen.button_long_width)),
            colors = ButtonDefaults.buttonColors(containerColor = ThemeColor.Orange.color),
        ) {
            Text(text = stringResource(R.string.see_results))
        }
    }
}

@Composable
private fun PlayerNavigationRow(
    onPrevious: () -> Unit,
    firstPlayer: Boolean,
    player: Player,
    numPlayers: Int,
    onNext: () -> Unit,
    lastPlayer: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { onPrevious() },
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.button_short_padding_horizontal))
                .width(dimensionResource(id = R.dimen.button_short_width))
                .alpha(if (firstPlayer) 0f else 1f),
            colors = ButtonDefaults.buttonColors(containerColor = ThemeColor.Blue.color),
        ) {
            Text(text = stringResource(id = R.string.prev_player))
        }
        Text(
            text = "${player.id}/$numPlayers",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center

        )
        Button(
            onClick = { onNext() },
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.button_short_padding_horizontal))
                .width(dimensionResource(id = R.dimen.button_short_width))
                .alpha(if (lastPlayer) 0f else 1f),
            colors = ButtonDefaults.buttonColors(containerColor = ThemeColor.Red.color),
        ) {
            Text(text = stringResource(id = R.string.next_player))
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
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,


    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.subtract),
                contentDescription = stringResource(id = R.string.subtract),
                modifier = Modifier
                    .padding(end = 4.dp)
                    .clickable { onValueChange(Calculate.decrement(value)) }
            )
            TextField(
                value = value,
                onValueChange = { onValueChange(it) },
                modifier = Modifier.width(80.dp),
                placeholder = { Text(text = "0") },
                singleLine = true
            )
            Image(
                painter = painterResource(id = R.drawable.add),
                contentDescription = stringResource(id = R.string.add),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { onValueChange(Calculate.increment(value)) }
            )
        }
        Text(
            text = stringResource(id = R.string.points, Calculate.points(cardType, value)),
            modifier = Modifier.padding(end = 24.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundFirstRoundFirstPlayerNoDataScreenPreview() {
    LigrettoScoresheetTheme {
        PlayerRoundScreen(
            player = Players.alex,
            round = Round(playerId = 1, id = 1),
            onRoundChange = { _,_ -> },
            numPlayers = 3,
            onHome = { },
            onNext = { },
            onResults = { },
            onPrevious = { }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundFirstRoundFirstPlayerScreenPreview() {
    val alex = Players.alex
    LigrettoScoresheetTheme {
        PlayerRoundScreen(
            player = alex,
            round = Round(alex.id, 1, "1", "2", "3"),
            onRoundChange = { _,_ -> },
            numPlayers = 3,
            onHome = { },
            onNext = { },
            onResults = { },
            onPrevious = { }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundScoreSecondRoundFirstPlayerScreenPreview() {
    val alex = Players.alex
    LigrettoScoresheetTheme {
        PlayerRoundScreen(
            player = alex,
            round = Round(alex.id, 2, "3", "2", "1"),
            onRoundChange = { _,_ -> },
            numPlayers = 3,
            onHome = { },
            onNext = { },
            onResults = { },
            onPrevious = { }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundMiddlePlayerScreenPreview() {
    val thao = Players.thao
    LigrettoScoresheetTheme {
        PlayerRoundScreen(
            player = thao,
            round = Round(thao.id, 1, "2", "12", "0"),
            onRoundChange = { _,_ -> },
            numPlayers = 3,
            onHome = { },
            onNext = { },
            onResults = { },
            onPrevious = { }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundMiddlePlayerTwelvePlayersScreenPreview() {
    val thao = Players.thao
    LigrettoScoresheetTheme {
        PlayerRoundScreen(
            player = thao.copy(id = 11),
            round = Round(thao.id, 1, "1", "1", "1"),
            onRoundChange = { _,_ -> },
            numPlayers = 12,
            onHome = { },
            onNext = { },
            onResults = { },
            onPrevious = { }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundLastPlayerScreenPreview() {
    val rikke = Players.rikke
    LigrettoScoresheetTheme {
        PlayerRoundScreen(
            player = Players.rikke,
            round = Round(rikke.id, 1, "3", "9", "1"),
            onRoundChange = { _,_ -> },
            numPlayers = 3,
            onHome = { },
            onNext = { },
            onResults = { },
            onPrevious = { }
        )
    }
}
