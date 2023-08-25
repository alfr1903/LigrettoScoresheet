package no.fredheim.ligrettoScoresheet.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.common.Background
import no.fredheim.ligrettoScoresheet.common.Headline
import no.fredheim.ligrettoScoresheet.common.WideButton
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoScoresheetTheme
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeDarkBlue

@Composable
fun WelcomeScreen(
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Background(resId = R.drawable.ligrettored_background)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LigrettoLogo()
        Headline(
            textId = R.string.count_points,
            modifier = Modifier.padding(top = 132.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        WideButton(
            textId = R.string.start_ligretto_calculator,
            color = ThemeDarkBlue,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.screen_bottom_button_bottom_padding)),
            onClick = { onStartClick() }
        )
    }
}

@Composable
fun LigrettoLogo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(308.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.ligretto_calculator_logo),
            contentDescription = stringResource(R.string.logo)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
fun WelcomeScreenPreviewNoMaxScoreInput() {
    LigrettoScoresheetTheme {
        WelcomeScreen(
            onStartClick = { },
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
fun WelcomeScreenPreviewMaxScoreInput() {
    LigrettoScoresheetTheme {
        WelcomeScreen(
            onStartClick = { },
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4"
)
@Composable
fun WelcomeScreenPreviewInvalidMaxScoreInput() {
    LigrettoScoresheetTheme {
        WelcomeScreen(
            onStartClick = { },
        )
    }
}

