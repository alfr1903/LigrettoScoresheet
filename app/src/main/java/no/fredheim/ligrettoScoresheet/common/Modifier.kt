package no.fredheim.ligrettoScoresheet.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import no.fredheim.ligrettoScoresheet.R

fun Modifier.topIconRowModifier() = composed { this
    .fillMaxWidth()
    .padding(
        start = dimensionResource(id = R.dimen.icons_row_padding_horizontal),
        top = dimensionResource(id = R.dimen.icons_row_padding_top),
        end = dimensionResource(id = R.dimen.icons_row_padding_horizontal),
    )
}

fun Modifier.cardCounterRowModifier() = composed { this
    .fillMaxWidth()
    .height(120.dp)
    .padding(horizontal = 12.dp, vertical = 12.dp)
}

fun Modifier.buttonRowHorizontalModifier() = composed { this
    .fillMaxWidth()
    .padding(horizontal = 12.dp)
}
