package no.fredheim.ligrettoScoresheet.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import no.fredheim.ligrettoScoresheet.common.Circle
import no.fredheim.ligrettoScoresheet.common.HighlightedCircle
import no.fredheim.ligrettoScoresheet.common.IconsRow
import no.fredheim.ligrettoScoresheet.common.PlayerNameRow
import no.fredheim.ligrettoScoresheet.handler.BackPressHandler
import no.fredheim.ligrettoScoresheet.model.ColorPickerState
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.ui.theme.PlayerColors
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeGreen
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeYellow
import no.fredheim.ligrettoScoresheet.util.Players

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayersScreen(
    players: List<Player>,
    playerCreator: ColorPickerState,
    onPlayerCreatorChange: (ColorPickerState) -> Unit,
    onPlayerAdd: (Player) -> Unit,
    onStartGameClick: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ligrettoblue_background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
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
            onRight = {  }
        )
        Text(
            text = stringResource(id = R.string.players),
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium

        )
        LazyColumn(
            Modifier
                .height(360.dp)
            , contentPadding = PaddingValues(
                start = dimensionResource(id = R.dimen.list_padding_start),
                top = 12.dp,
                end = dimensionResource(id = R.dimen.list_padding_end)
            )
        ) {
            itemsIndexed(players) { num, player ->
                PlayerNameRow(
                    number = num + 1,
                    player = player,
                    Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                )
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.choose_color),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodySmall
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
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
            TextField(
                value = playerCreator.name,
                onValueChange = { onPlayerCreatorChange(playerCreator.copy(name = it)) },
                modifier = Modifier.padding(top = 12.dp),
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
            Button(
                onClick = {
                    onPlayerAdd(
                        Player(
                            id = players.size + 1,
                            name = playerCreator.name,
                            color = playerCreator.chosenColor!!
                        )
                    )
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(dimensionResource(id = R.dimen.button_long_width)),
                enabled = playerCreator.chosenColor != null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ThemeYellow,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = stringResource(id = R.string.add_player),
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { onStartGameClick() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = dimensionResource(id = R.dimen.screen_bottom_button_bottom_padding))
                .width(dimensionResource(id = R.dimen.button_long_width)),
            colors = ButtonDefaults.buttonColors(containerColor = ThemeGreen)
        ) {
            Text(
                text = stringResource(R.string.start_game),
                textAlign = TextAlign.Center
            )
        }
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
            onStartGameClick = { },
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
                PlayerColors.drop(2).toSet(),
                PlayerColors.elementAt(2)
            ),
            onPlayerCreatorChange = { },
            onPlayerAdd = { },
            onStartGameClick = { },
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
            onStartGameClick = { },
            onBack = { },
        )
    }
}
