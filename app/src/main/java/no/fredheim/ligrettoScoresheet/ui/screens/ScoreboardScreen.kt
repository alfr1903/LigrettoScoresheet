package no.fredheim.ligrettoScoresheet.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.common.Background
import no.fredheim.ligrettoScoresheet.common.HeadlineBold
import no.fredheim.ligrettoScoresheet.common.IconsRow
import no.fredheim.ligrettoScoresheet.common.MediumButton
import no.fredheim.ligrettoScoresheet.common.PlayerScoreRow
import no.fredheim.ligrettoScoresheet.common.TitleBold
import no.fredheim.ligrettoScoresheet.common.buttonRowHorizontalModifier
import no.fredheim.ligrettoScoresheet.common.topIconRowModifier
import no.fredheim.ligrettoScoresheet.model.Icon
import no.fredheim.ligrettoScoresheet.model.PlayerScore
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeColor
import no.fredheim.ligrettoScoresheet.util.Players

@Composable
fun ResultsScreen(
    roundId: Int,
    playersScore: List<PlayerScore>,
    onHome: () -> Unit,
    onNewRound: () -> Unit,
    onEnd: () -> Unit,
    modifier: Modifier = Modifier
) {
    Background(resId = R.drawable.ligrettoyellow_background)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconsRow(
            leftIcon = Icon(resId = R.drawable.home, descriptionId = R.string.home),
            onLeft = { onHome() },
            rightIcon = Icon(resId = R.drawable.edit, descriptionId = R.string.edit),
            onRight = { },
            modifier = Modifier.topIconRowModifier()
        )
        HeadlineBold(textId = R.string.scoreboard)
        TitleBold(textId = R.string.after_round, arg = roundId)
        Column(modifier = Modifier.padding(horizontal = 60.dp, vertical = 8.dp)) {
            playersScore.forEachIndexed { index, playerScore ->
                PlayerScoreRow(
                    number = index + 1,
                    playerScore = playerScore,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .buttonRowHorizontalModifier()
                .padding(
                    bottom = dimensionResource(id = R.dimen.screen_bottom_button_bottom_padding)
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MediumButton(
                textId = R.string.end_game,
                color = ThemeColor.DarkRed,
                onClick = { onEnd() }
            )
            MediumButton(
                textId = R.string.new_round,
                color = ThemeColor.DarkGreen,
                onClick = { onNewRound() }
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
private fun ResultsScreenRound1Preview() {
    LigrettoScoresheetTheme {
        ResultsScreen(
            roundId = 1,
            playersScore = Players.threePlayers().map { PlayerScore(it, 0) },
            onHome = { },
            onNewRound = { },
            onEnd = { }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
private fun ResultsScreenRound2Preview() {
    LigrettoScoresheetTheme {
        ResultsScreen(
            roundId = 2,
            playersScore = Players.threePlayers().map { PlayerScore(it, 0) },
            onHome = { },
            onNewRound = { },
            onEnd = { }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
private fun ResultsScreen12PlayersPreview() {
    LigrettoScoresheetTheme {
        ResultsScreen(
            roundId = 1,
            playersScore = Players.allPlayers().map { PlayerScore(it, 0) },
            onHome = { },
            onNewRound = { },
            onEnd = { }
        )
    }
}
