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
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.ui.theme.TextColor
import no.fredheim.ligrettoScoresheet.ui.theme.ThemeColor

@Composable
fun WideButton(
    @StringRes textId: Int,
    buttonColor: ThemeColor,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.width(200.dp),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor.color)
    ) {
        Text(text = stringResource(textId))
    }
}

@Composable
fun MediumButton(
    @StringRes textId: Int,
    color: ThemeColor,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.width(140.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color.color)
    ) {
        Text(text = stringResource(textId))
    }
}

@Composable
fun SmallButton(
    @StringRes textId: Int,
    color: ThemeColor,
    modifier: Modifier = Modifier,
    textColor: TextColor = TextColor.White,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.width(120.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = color.color,
            contentColor = textColor.color
        )
    ) {
        Text(text = stringResource(textId))
    }
}