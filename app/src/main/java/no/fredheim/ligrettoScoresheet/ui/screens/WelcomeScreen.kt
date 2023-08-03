package no.fredheim.ligrettoScoresheet.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.ui.theme.ButtonDarkBlue
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme

private const val logoWeight = 45f
private const val spacerWeight = 8f
private const val restOfScreenWeight = 47f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    maxScore: String,
    onStartGameButtonClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var currentMax by remember { mutableStateOf(maxScore) }

    Image(
        painter = painterResource(id = R.drawable.ligrettored_background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
    Column(modifier = modifier) {
        Box(
            modifier = Modifier.weight(logoWeight),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.ligretto_calculator_logo),
                contentDescription = stringResource(R.string.logo)
            )
        }
        Spacer(modifier = Modifier.weight(spacerWeight))
        Column(
            modifier = Modifier.weight(restOfScreenWeight),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.count_points),
                modifier = Modifier.padding(bottom = 16.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )
            TextField(
                value = currentMax,
                onValueChange = { currentMax = it },
                label = { Text(text = stringResource(R.string.type_total_amount)) },
                isError = !currentMax.isDigitsOnly(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
            )
            Text(
                text = stringResource(R.string.can_start_without_total_amount),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { onStartGameButtonClick(currentMax) },
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.button_bottom_padding))
                    .width(dimensionResource(id = R.dimen.button_long_width)),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonDarkBlue)
            ) {
                Text(
                    text = stringResource(R.string.start_ligretto_calculator),
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
fun WelcomeScreenPreviewNoMaxScoreInput() {
    LigrettoScoresheetTheme {
        WelcomeScreen(
            maxScore = "",
            onStartGameButtonClick = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun WelcomeScreenPreviewMaxScoreInput() {
    LigrettoScoresheetTheme {
        WelcomeScreen(
            maxScore = "0",
            onStartGameButtonClick = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun WelcomeScreenPreviewInvalidMaxScoreInput() {
    LigrettoScoresheetTheme {
        WelcomeScreen(
            maxScore = ",",
            onStartGameButtonClick = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

