package no.fredheim.ligrettoScoresheet.ui.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.common.Background
import no.fredheim.ligrettoScoresheet.common.BodySmall
import no.fredheim.ligrettoScoresheet.common.HeadlineBold
import no.fredheim.ligrettoScoresheet.common.IconsRow
import no.fredheim.ligrettoScoresheet.common.MediumButton
import no.fredheim.ligrettoScoresheet.common.PlayerCardText
import no.fredheim.ligrettoScoresheet.common.WideButton
import no.fredheim.ligrettoScoresheet.common.buttonRowHorizontalModifier
import no.fredheim.ligrettoScoresheet.common.cardCounterRowModifier
import no.fredheim.ligrettoScoresheet.common.topIconRowModifier
import no.fredheim.ligrettoScoresheet.model.Card
import no.fredheim.ligrettoScoresheet.model.CenterPileCard
import no.fredheim.ligrettoScoresheet.model.Icon
import no.fredheim.ligrettoScoresheet.model.MinusPileCard
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.model.Round
import no.fredheim.ligrettoScoresheet.model.TenCard
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
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val firstPlayer = player.id == 1
    val lastPlayer = player.id == numPlayers


    Background(resId = R.drawable.ligrettogreen_background)

    // Temp row
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        BodySmall(
            textId = R.string.coming_soon,
            modifier.padding(top = 80.dp, end = 20.dp).width(52.dp)
        )
    }

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
            leftIcon = Icon(resId = R.drawable.home, descriptionId = R.string.home),
            onLeft = { onHome() },
            rightIcon = Icon(resId = R.drawable.list, descriptionId = R.string.list_of_players),
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
            card = TenCard(),
            value = round.num10s,
            onValueChange = { onRoundChange(player, round.copy(num10s = it)) },
            modifier = Modifier.cardCounterRowModifier()
        )
        CardCounterRow(
            card = CenterPileCard(),
            value = round.numCenter,
            onValueChange = { onRoundChange(player, round.copy(numCenter = it)) },
            modifier = Modifier.cardCounterRowModifier()
        )
        CardCounterRow(
            card = MinusPileCard(),
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
            modifier = Modifier
                .buttonRowHorizontalModifier()
                .padding(top = 52.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        WideButton(
            textId = R.string.see_results,
            buttonColor = ThemeColor.Orange,
            onClick = { onResults() },
            modifier = Modifier.padding(
                bottom = dimensionResource(id = R.dimen.screen_bottom_button_bottom_padding)
            )
        )
        BackHandler { onBack() }
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
        MediumButton(
            textId = R.string.prev_player,
            color = ThemeColor.Blue,
            onClick = { onPrevious() },
            modifier = Modifier.alpha(if (firstPlayer) 0f else 1f),
        )
        Text(
            text = "${player.id}/$numPlayers",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center

        )
        MediumButton(
            textId = R.string.next_player,
            color = ThemeColor.Red,
            onClick = { onNext() },
            modifier = Modifier.alpha(if (lastPlayer) 0f else 1f),
        )
    }
}

@Composable
private fun CardCounterRow(
    card: Card,
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
                painter = painterResource(card.typeImageId),
                contentDescription = stringResource(card.typeDescriptionId),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(card.typeTextId),
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
            text = stringResource(id = R.string.points, Calculate.points(card.type, value)),
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
            onPrevious = { },
            onBack = { }
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
            onPrevious = { },
            onBack = { }
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
            onPrevious = { },
            onBack = { }
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
            onPrevious = { },
            onBack = { }
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
            onPrevious = { },
            onBack = { }
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
            onPrevious = { },
            onBack = { }
        )
    }
}
