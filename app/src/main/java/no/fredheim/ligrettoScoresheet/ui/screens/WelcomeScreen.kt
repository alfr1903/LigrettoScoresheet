package no.fredheim.ligrettoScoresheet.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeDarkBlue

private const val logoWeight = 45f
private const val spacerWeight = 8f
private const val restOfScreenWeight = 47f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    onStartGameButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { onStartGameButtonClick() },
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.button_bottom_padding))
                    .width(dimensionResource(id = R.dimen.button_long_width)),
                colors = ButtonDefaults.buttonColors(containerColor = ThemeDarkBlue)
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
            onStartGameButtonClick = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

