package no.fredheim.ligrettoScoresheet.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun WideButton(
    @StringRes textId: Int,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.width(200.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(
            text = stringResource(textId),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MediumButton(
    @StringRes textId: Int,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.width(140.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(text = stringResource(textId))
    }
}

@Composable
fun SmallButton(
    @StringRes textId: Int,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.width(100.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(text = stringResource(textId))
    }

}