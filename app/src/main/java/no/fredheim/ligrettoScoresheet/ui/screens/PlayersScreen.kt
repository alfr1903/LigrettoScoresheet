package no.fredheim.ligrettoScoresheet.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import no.fredheim.ligrettoScoresheet.common.Circle
import no.fredheim.ligrettoScoresheet.common.HighlightedCircle
import no.fredheim.ligrettoScoresheet.common.PlayerNameRow
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeGreen
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeYellow
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.ui.theme.PlayerColors
import no.fredheim.ligrettoScoresheet.util.Players

private const val topRowWeight = 18f
private const val playersColumnWeight = 39f
private const val spacerWeight = 6f
private const val RestOfScreenWeight = 37f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayersScreen(
    players: List<Player>,
    name: String,
    onNameChange: (String) -> Unit,
    availableColors: Set<Color>,
    chosenColor: Color?,
    onChosenColor: (Color) -> Unit,
    onPlayerAdded: (Player) -> Unit,
    onStartGameClick: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ligrettoblue_background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.weight(topRowWeight),
            verticalAlignment = Alignment.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = stringResource(R.string.arrow_back),
                modifier = Modifier
                    .padding(start = 32.dp)
                    .clickable { onBack() }
            )
            Text(
                text = stringResource(R.string.players),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 68.dp)
            )
        }
        LazyColumn(
            Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.list_padding_start),
                    top = 12.dp,
                    end = dimensionResource(id = R.dimen.list_padding_end)
                )
                .weight(playersColumnWeight)
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
        Spacer(modifier = Modifier.weight(spacerWeight))
        Column(modifier = Modifier.weight(RestOfScreenWeight)) {
            Column(
                modifier = Modifier
                    .alpha(if (chosenColor != null) 1f else 0f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.choose_color),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodySmall
                )
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(availableColors.toList()) { color ->
                        if (chosenColor == color)
                            HighlightedCircle(color = color, onClick = { onChosenColor(color) })
                        else
                            Circle(color = color, onClick = { onChosenColor(color) })
                    }
                }
                TextField(
                    value = name,
                    onValueChange = { onNameChange(it) },
                    modifier = Modifier.padding(top = 12.dp),
                    label = { Text(text = stringResource(R.string.type_name)) },
                    keyboardActions = KeyboardActions(
                        onDone = { onPlayerAdded(
                            Player(number = players.size + 1, name = name, color = chosenColor!!)
                        ) }
                    ),
                    singleLine = true,
                )
                Button(
                    onClick = {
                        onPlayerAdded(
                            Player(number = players.size + 1, name = name, color = chosenColor!!)
                        )
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(dimensionResource(id = R.dimen.button_long_width)),
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
                    .padding(bottom = dimensionResource(id = R.dimen.button_bottom_padding))
                    .width(dimensionResource(id = R.dimen.button_long_width)),
                colors = ButtonDefaults.buttonColors(containerColor = ThemeGreen)
            ) {
                Text(
                    text = stringResource(R.string.start_game),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayersScreenNoPlayersPreview() {
    LigrettoScoresheetTheme {
        PlayersScreen(
            players = emptyList(),
            name = "",
            availableColors = PlayerColors,
            chosenColor = PlayerColors.elementAt(0),
            onNameChange = { },
            onChosenColor = { },
            onPlayerAdded = { },
            onStartGameClick = { },
            onBack = { },
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayersScreenThreePlayersPreview() {
    LigrettoScoresheetTheme {
        PlayersScreen(
            players = Players.threePlayers(),
            name = "OJ",
            availableColors = PlayerColors.drop(2).toSet(),
            chosenColor = PlayerColors.elementAt(2),
            onNameChange = { },
            onChosenColor = { },
            onPlayerAdded = { },
            onStartGameClick = { },
            onBack = { },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayersScreenAllPlayersPreview() {
    LigrettoScoresheetTheme {
        PlayersScreen(
            players = Players.allPlayers(),
            name = "",
            availableColors = emptySet(),
            chosenColor = null,
            onNameChange = { },
            onChosenColor = { },
            onPlayerAdded = { },
            onStartGameClick = { },
            onBack = { },
            modifier = Modifier.fillMaxSize()
        )
    }
}
