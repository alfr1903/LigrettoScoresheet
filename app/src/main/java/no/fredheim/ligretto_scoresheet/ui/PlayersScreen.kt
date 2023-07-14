package no.fredheim.ligretto_scoresheet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
    nameInput: String,
    availableColors: Set<Color>,
    chosenColor: Color?,
    onNameChange: (String) -> Unit,
    onChosenColorChange: (Color) -> Unit,
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
        LazyColumn {
            itemsIndexed(players) {num, player ->
                PlayerRow(number = num + 1, player = player)
            }
        }
        Divider(modifier = Modifier.padding(top = 16.dp))
        PlayerAdder(
            number = players.size + 1,
            name = nameInput,
            availableColors = availableColors,
            chosenColor = chosenColor,
            onNameChange = onNameChange,
            onChosenColorChange = onChosenColorChange,
            onPlayerCreate = onPlayerCreated,
            modifier = Modifier.padding(top = 16.dp)
        )
        Button(
            onClick = onWriteResultsButtonClick,
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Text(text = stringResource(R.string.start_game))
        }


    }
}

@Composable
fun PlayerRow(
    number: Int,
    player: Player,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.player, number),
            modifier = Modifier
                .padding(4.dp)
                .weight(3f)
        )
        Box(
            modifier = Modifier
                .padding(4.dp)
                .size(16.dp)
                .background(color = player.color, shape = CircleShape)
        )
        Text(
            text = player.name,
            modifier = Modifier
                .padding(4.dp)
                .weight(1f)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerAdder(
    number: Int,
    name: String,
    availableColors: Set<Color>,
    chosenColor: Color?,
    onNameChange: (String) -> Unit,
    onChosenColorChange: (Color) -> Unit,
    onPlayerCreate: (Player) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (chosenColor != null) TextField(
            value = name,
            placeholder = { Text(text = stringResource(R.string.add_new_player)) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = chosenColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(16.dp),
            onValueChange = onNameChange,
            modifier = Modifier.padding(16.dp)
        )
        LazyRow {
            items(availableColors.toList()) {color ->
                ColorPicker(
                    color = color,
                    onColorChosen = { onChosenColorChange(color) }
                )
            }
        }
        Button(
            enabled = chosenColor != null,
            onClick = {
                onPlayerCreate(
                    Player(
                        number = number,
                        name = name,
                        color = chosenColor!!
                    )
                )
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = stringResource(R.string.add_player))
        }
    }
}

@Composable
fun ColorPicker(
    color: Color,
    onColorChosen: (Color) -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(20.dp)
            .background(color = color, shape = CircleShape)
            .clickable { onColorChosen(color) }
    )
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
            nameInput = "",
            availableColors = Colors.drop(2).toSet(),
            chosenColor = Colors.elementAt(2),
            onNameChange = { },
            onChosenColorChange = { },
            onPlayerCreated = { },
            onWriteResultsButtonClick = { /*TODO*/ }
        )
    }

}