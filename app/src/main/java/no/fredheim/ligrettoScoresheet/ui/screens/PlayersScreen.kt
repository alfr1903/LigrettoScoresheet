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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
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
import no.fredheim.ligrettoScoresheet.model.ColorPickerState
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.ui.theme.PlayerColor
import no.fredheim.ligrettoScoresheet.ui.theme.TextColor
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeColor
import no.fredheim.ligrettoScoresheet.util.Players

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayersScreen(
    players: List<Player>,
    playerCreator: ColorPickerState,
    onPlayerCreatorChange: (ColorPickerState) -> Unit,
    onPlayerAdd: (Player) -> Unit,
    onStartGame: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Background(resId = R.drawable.ligrettoblue_background)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconsRow(
            leftIconResId = R.drawable.back,
            lefIconDescriptionId = R.string.back,
            onLeft = { onBack() },
            rightIconResId = R.drawable.edit,
            rightIconDescriptionId = R.string.edit,
            onRight = {  },
            modifier = Modifier.topIconRowModifier()
        )
        HeadlineBold(R.string.players)
        LazyColumn(
            modifier = Modifier.height(400.dp),
            contentPadding = PaddingValues(
                horizontal = dimensionResource(id = R.dimen.players_list_padding_horizontal)
            )
        ) {
            itemsIndexed(players) { num, player ->
                PlayerNameRow(
                    number = num + 1,
                    player = player,
                    Modifier
                        .padding(vertical = 4.dp)
                        .fillMaxWidth()
                )
            }
        }
        BodySmall(
            textId = R.string.choose_color,
            modifier = Modifier.padding(top = 20.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(playerCreator.availableColors.toList()) { color ->
                if (playerCreator.chosenColor == color) {
                    HighlightedCircle(
                        color = color,
                        onClick = {
                            onPlayerCreatorChange(playerCreator.copy(chosenColor = color))
                        }
                    )
                } else
                    Circle(
                        color = color,
                        onClick = {
                            onPlayerCreatorChange(playerCreator.copy(chosenColor = color))
                        }
                    )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = playerCreator.name,
                onValueChange = { onPlayerCreatorChange(playerCreator.copy(name = it)) },
                modifier = Modifier.width(220.dp),
                enabled = playerCreator.chosenColor != null,
                label = { Text(text = stringResource(R.string.type_name)) },
                keyboardActions = KeyboardActions(
                    onDone = { onPlayerAdd(
                        Player(
                            id = players.size + 1,
                            name = playerCreator.name,
                            color = playerCreator.chosenColor!!
                        )
                    ) }
                ),
                singleLine = true,
            )
            SmallButton(
                textId = R.string.add_player,
                color = ThemeColor.Yellow,
                textColor = TextColor.Black,
                enabled = playerCreator.chosenColor != null,
                onClick = {
                    onPlayerAdd(
                        Player(
                            id = players.size + 1,
                            name = playerCreator.name,
                            color = playerCreator.chosenColor!!
                        )
                    )

                }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        WideButton(
            textId = R.string.start_game,
            buttonColor = ThemeColor.Green,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.screen_bottom_button_bottom_padding)),
            onClick = { onStartGame() }
        )
    }
    BackPressHandler { onBack() }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
fun PlayersScreenNoPlayersPreview() {
    LigrettoScoresheetTheme {
        PlayersScreen(
            players = emptyList(),
            playerCreator = ColorPickerState(),
            onPlayerCreatorChange = { },
            onPlayerAdd = { },
            onStartGame = { },
            onBack = { },
        )
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
fun PlayersScreenThreePlayersPreview() {
    LigrettoScoresheetTheme {
        PlayersScreen(
            players = Players.threePlayers(),
            playerCreator = ColorPickerState(
                "OJ",
                PlayerColor.values().drop(2).toSet(),
                PlayerColor.values().elementAt(2)
            ),
            onPlayerCreatorChange = { },
            onPlayerAdd = { },
            onStartGame = { },
            onBack = { },
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
fun PlayersScreenAllPlayersPreview() {
    LigrettoScoresheetTheme {
        PlayersScreen(
            players = Players.allPlayers(),
            playerCreator = ColorPickerState(availableColors =  emptySet(), chosenColor = null),
            onPlayerCreatorChange = { },
            onPlayerAdd = { },
            onStartGame = { },
            onBack = { },
        )
    }
}
