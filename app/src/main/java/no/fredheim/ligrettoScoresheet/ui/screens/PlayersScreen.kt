package no.fredheim.ligrettoScoresheet.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.Util
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.ui.theme.PlayerColors

@Composable
fun PlayersScreen(
    players: List<Player>,
    name: String,
    onNameChange: (String) -> Unit,
    availableColors: Set<Color>,
    chosenColor: Color?,
    onChosenColorChange: (Color) -> Unit,
    onPlayerCreated: (Player) -> Unit,
    onWriteResultsButtonClick: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ligrettoblue_background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
    Column {
        Box(
            modifier = Modifier.weight(7f),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = stringResource(
                    R.string.arrow_back
                ),
                modifier = Modifier
                    .padding(start = 32.dp)
                    .clickable { onBack() }
            )
        }
        Column(
            modifier = modifier.weight(33f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.players),
            )
        }

    }

    /*
        Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            itemsIndexed(players) { num, player ->
                PlayerRow(number = num + 1, player = player, modifier.padding(4.dp))
            }
        }
        Divider(modifier = Modifier.padding(top = 16.dp))
        PlayerAdder(
            number = players.size + 1,
            name = name,
            onNameChange = onNameChange,
            availableColors = availableColors,
            chosenColor = chosenColor,
            onChosenColorChange = onChosenColorChange,
            onPlayerCreate = onPlayerCreated,
            modifier = Modifier.padding(top = 16.dp)
        )
        Button(
            enabled = players.isNotEmpty(),
            onClick = onWriteResultsButtonClick,
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Text(text = stringResource(R.string.start_ligretto_calculator))
        }
    }

    */
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerAdder(
    number: Int,
    name: String,
    onNameChange: (String) -> Unit,
    availableColors: Set<Color>,
    chosenColor: Color?,
    onChosenColorChange: (Color) -> Unit,
    onPlayerCreate: (Player) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (chosenColor != null) {
            TextField(
                value = name,
                placeholder = { Text(text = stringResource(R.string.add_new_player)) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = chosenColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (name.isNotEmpty()) {
                            onPlayerCreate(
                                Player(
                                    number = number,
                                    name = name,
                                    color = chosenColor
                                )
                            )
                        }
                    }
                ),
                singleLine = true,
                onValueChange = { onNameChange(it) },
                modifier = Modifier.padding(16.dp)
            )
        }
        LazyRow {
            items(availableColors.toList()) { color ->
                ColorPicker(
                    color = color,
                    onColorChosen = { onChosenColorChange(it) }
                )
            }
        }
        Button(
            enabled = chosenColor != null && name.isNotEmpty(),
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
    onColorChosen: (Color) -> Unit
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
            name = "",
            availableColors = PlayerColors.drop(2).toSet(),
            chosenColor = PlayerColors.elementAt(2),
            onNameChange = { },
            onChosenColorChange = { },
            onPlayerCreated = { },
            onWriteResultsButtonClick = { },
            onBack = { },
            modifier = Modifier.fillMaxSize()
        )
    }
}
