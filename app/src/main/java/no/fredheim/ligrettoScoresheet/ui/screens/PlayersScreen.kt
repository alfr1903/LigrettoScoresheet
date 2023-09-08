package no.fredheim.ligrettoScoresheet.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.common.Background
import no.fredheim.ligrettoScoresheet.common.BodySmall
import no.fredheim.ligrettoScoresheet.common.Circle
import no.fredheim.ligrettoScoresheet.common.HeadlineBold
import no.fredheim.ligrettoScoresheet.common.HighlightedCircle
import no.fredheim.ligrettoScoresheet.common.IconsRow
import no.fredheim.ligrettoScoresheet.common.PlayerNameRow
import no.fredheim.ligrettoScoresheet.common.SmallButton
import no.fredheim.ligrettoScoresheet.common.WideButton
import no.fredheim.ligrettoScoresheet.common.topIconRowModifier
import no.fredheim.ligrettoScoresheet.handler.BackPressHandler
import no.fredheim.ligrettoScoresheet.model.Icon
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.model.PlayerCreatorState
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.ui.theme.PlayerColor
import no.fredheim.ligrettoScoresheet.ui.theme.TextColor
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeColor
import no.fredheim.ligrettoScoresheet.util.Players

@Composable
fun PlayersScreen(
    players: List<Player>,
    playerCreator: PlayerCreatorState,
    onPlayerCreatorChange: (PlayerCreatorState) -> Unit,
    onPlayerAdd: (Player) -> Unit,
    onNavigate: (PlayersScreenNav) -> Unit,
    modifier: Modifier = Modifier
) {
    Background(resId = R.drawable.ligrettoblue_background)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconsRow(
            leftIcon = Icon(resId = R.drawable.back, descriptionId = R.string.back),
            onLeft = { onNavigate(PlayersScreenNav.Back) },
            rightIcon = Icon(resId = R.drawable.edit, descriptionId = R.string.edit),
            onRight = { },
            modifier = Modifier.topIconRowModifier()
        )
        HeadlineBold(R.string.players)
        LazyColumn(
            modifier = Modifier.height(400.dp),
            contentPadding = PaddingValues(start = 72.dp, top = 28.dp, end = 72.dp)
        ) {
            itemsIndexed(players) { num, player ->
                PlayerNameRow(
                    number = num + 1,
                    player = player,
                    Modifier
                        .padding(vertical = 3.dp)
                        .fillMaxWidth()
                )
            }
        }
        BodySmall(
            textId = R.string.choose_color,
            modifier = Modifier.padding(top = 20.dp)
        )
        PlayerCreator(
            playerCreator = playerCreator,
            onPlayerCreatorChange = onPlayerCreatorChange,
            onPlayerAdd = onPlayerAdd,
            numPlayers = players.size
        )
        Spacer(modifier = Modifier.weight(1f))
        WideButton(
            textId = R.string.start_game,
            buttonColor = ThemeColor.Green,
            modifier = Modifier.padding(
                bottom = dimensionResource(id = R.dimen.screen_bottom_button_bottom_padding)
            ),
            enabled = players.size >= 2,
            onClick = { onNavigate(PlayersScreenNav.StartGame) }
        )
    }
    BackPressHandler({ onNavigate(PlayersScreenNav.Back) })
}

@Composable
private fun PlayerCreator(
    playerCreator: PlayerCreatorState,
    onPlayerCreatorChange: (PlayerCreatorState) -> Unit,
    onPlayerAdd: (Player) -> Unit,
    numPlayers: Int
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(4.dp)
    ) {
        items(playerCreator.availableColors.toList()) { color ->
            if (playerCreator.chosenColor == color) {
                HighlightedCircle(
                    color = color,
                    onClick = {
                        onPlayerCreatorChange(playerCreator.copy(chosenColor = color))
                    }
                )
            } else {
                Circle(
                    color = color,
                    onClick = {
                        onPlayerCreatorChange(playerCreator.copy(chosenColor = color))
                    }
                )
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayerAdderTextField(playerCreator, onPlayerCreatorChange, onPlayerAdd, numPlayers)
        SmallButton(
            textId = R.string.add_player,
            color = ThemeColor.Yellow,
            textColor = TextColor.Black,
            enabled = playerCreator.chosenColor != null,
            onClick = {
                onPlayerAdd(
                    Player(
                        id = numPlayers + 1,
                        name = playerCreator.name,
                        color = playerCreator.chosenColor!!
                    )
                )
            }
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun PlayerAdderTextField(
    playerCreator: PlayerCreatorState,
    onPlayerCreatorChange: (PlayerCreatorState) -> Unit,
    onPlayerAdd: (Player) -> Unit,
    numPlayers: Int
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = playerCreator.name,
        onValueChange = { onPlayerCreatorChange(playerCreator.copy(name = it)) },
        modifier = Modifier.width(220.dp),
        enabled = playerCreator.chosenColor != null,
        label = { Text(text = stringResource(R.string.type_name)) },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = if (playerCreator.name.isEmpty()) ImeAction.Done else ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() },
            onNext = {
                onPlayerAdd(
                    Player(
                        id = numPlayers + 1,
                        name = playerCreator.name,
                        color = playerCreator.chosenColor!!
                    )
                )
            }
        ),
        singleLine = true
    )
}

enum class PlayersScreenNav {
    StartGame,
    Back
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
private fun PlayersScreenNoPlayersPreview() {
    LigrettoScoresheetTheme {
        PlayersScreen(
            players = emptyList(),
            playerCreator = PlayerCreatorState(),
            onPlayerCreatorChange = { },
            onPlayerAdd = { },
            onNavigate = { }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
private fun PlayersScreenThreePlayersPreview() {
    LigrettoScoresheetTheme {
        PlayersScreen(
            players = Players.threePlayers(),
            playerCreator = PlayerCreatorState(
                "OJ",
                PlayerColor.values().drop(2).toSet(),
                PlayerColor.values().elementAt(2)
            ),
            onPlayerCreatorChange = { },
            onPlayerAdd = { },
            onNavigate = { }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
private fun PlayersScreenAllPlayersPreview() {
    LigrettoScoresheetTheme {
        PlayersScreen(
            players = Players.allPlayers(),
            playerCreator = PlayerCreatorState(availableColors = emptySet(), chosenColor = null),
            onPlayerCreatorChange = { },
            onPlayerAdd = { },
            onNavigate = { }
        )
    }
}
