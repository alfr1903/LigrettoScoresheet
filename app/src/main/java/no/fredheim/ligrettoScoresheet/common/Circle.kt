package no.fredheim.ligrettoScoresheet.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.ui.theme.PlayerColors

@Composable
fun HighlightedCircle(color: Color, clickable: Boolean = true, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .shadow(elevation = 1.dp, shape = CircleShape)
            .background(color = Color.White, shape = CircleShape)
            .clickable(enabled = clickable) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Circle(color = color) { onClick() }
    }
}

@Composable
fun HighlightedCircle(color: Color) {
    HighlightedCircle(color = color, clickable = false, onClick = { })
}

@Composable
fun Circle(
    color: Color,
    clickable: Boolean = true,
    shade: Boolean = false,
    onClick: () -> Unit
) {
    val elevation = if (shade) 1.dp else 0.dp
    Box(
        modifier = Modifier
            .shadow(elevation = elevation, shape = CircleShape)
            .size(20.dp)
            .background(color = color, shape = CircleShape)
            .clickable(enabled = clickable) { onClick() }
    )
}

@Composable
fun Circle(color: Color) {
    Circle(color = color, clickable = false, shade = true, onClick = { })
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF0f6bb9
)
@Composable
fun HighlightedCirclePreview() {
    HighlightedCircle(color = PlayerColors.first())
}

@Preview(
    showBackground = true,
)
@Composable
fun CirclePreview() {
    Circle(color = PlayerColors.first())
}

