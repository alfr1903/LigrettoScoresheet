package no.fredheim.ligrettoScoresheet.ui.screens

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.Util
import no.fredheim.ligrettoScoresheet.common.Counter
import no.fredheim.ligrettoScoresheet.common.Points
import no.fredheim.ligrettoScoresheet.model.CardType
import no.fredheim.ligrettoScoresheet.model.Player
import no.fredheim.ligrettoScoresheet.model.Round
import no.fredheim.ligrettoScoresheet.service.CalculationService
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme

@Composable
fun PlayerRoundScoreScreen(
    player: Player,
    round: Round,
    lastPlayer: Boolean,
    onNextPlayerButtonClick: (Round) -> Unit,
    onResultsButtonClick: (Round) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentNum10s by remember { mutableStateOf(round.num10s) }
    var currentNumCenter by remember { mutableStateOf(round.numCenter) }
    var currentNumLigretto by remember { mutableStateOf(round.numLigretto) }

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
        if (!lastPlayer) {
            Button(
                onClick = {
                    onNextPlayerButtonClick(
                        Round(
                            num10s = currentNum10s,
                            numCenter = currentNumCenter,
                            numLigretto = currentNumLigretto
                        )
                    )
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = stringResource(R.string.next_player))
            }
        } else {
            Button(
                onClick = {
                    onResultsButtonClick(
                        Round(
                            num10s = currentNum10s,
                            numCenter = currentNumCenter,
                            numLigretto = currentNumLigretto
                        )
                    )
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = stringResource(R.string.results))
            }
        }
        BackHandler(onBack = onBack)
    }
}

@Composable
fun CardPointsCalculator(
    @DrawableRes image: Int,
    @StringRes imageDescription: Int,
    @StringRes description: Int,
    numCards: String,
    points: Int,
    onNumCardsChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(image),
                contentDescription = stringResource(imageDescription)
            )
            Text(text = stringResource(description))
        }
        Counter(value = numCards, onValueChange = { onNumCardsChange(it) })
        Points(points = points)
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundScoreScreenPreview() {
    LigrettoScoresheetTheme {
        PlayerRoundScoreScreen(
            player = Util.alex,
            round = Util.alex.round[2]!!,
            lastPlayer = false,
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
            player = Util.thao,
            round = Util.thao.round[2]!!,
            lastPlayer = true,
            onNextPlayerButtonClick = { },
            onResultsButtonClick = { },
            onBack = { }
        )
    }
}
