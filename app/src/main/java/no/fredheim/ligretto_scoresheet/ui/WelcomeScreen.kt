package no.fredheim.ligretto_scoresheet.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import no.fredheim.ligretto_scoresheet.R
import no.fredheim.ligretto_scoresheet.common.Counter
import no.fredheim.ligretto_scoresheet.ui.theme.LigrettoScoresheetTheme

@Composable
fun WelcomeScreen(
    maxScore: String,
    onMaxScoreChange: (String) -> Unit,
    onStartGameButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.welcome),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.add_max_score),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
            )
            Text(
                text = stringResource(R.string.max_score_rules),
                textAlign = TextAlign.Center
            )
            Counter(
                value = maxScore,
                onValueChange = onMaxScoreChange,
            )
            Button(
                onClick = onStartGameButtonClick,
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
            onMaxScoreChange = {},
            onStartGameButtonClick = { /*TODO*/ }
        )
    }
}