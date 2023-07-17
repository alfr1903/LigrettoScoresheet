package no.fredheim.ligrettoScoresheet.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.common.Counter
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme

@Composable
fun WelcomeScreen(
    maxScore: String,
    onStartGameButtonClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var currentMax by remember { mutableStateOf(maxScore) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.welcome),
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.add_max_score),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )
            Text(
                text = stringResource(R.string.max_score_rules),
                textAlign = TextAlign.Center
            )
            Counter(
                value = currentMax,
                onValueChange = { currentMax = it }
            )
            Button(
                onClick = { onStartGameButtonClick(currentMax) }
            ) {
                Text(
                    text = stringResource(R.string.start_game)
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
fun WelcomeScreenPreview() {
    LigrettoScoresheetTheme {
        WelcomeScreen(
            maxScore = "",
            onStartGameButtonClick = { }
        )
    }
}
