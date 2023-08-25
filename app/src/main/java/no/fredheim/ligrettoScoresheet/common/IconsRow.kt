package no.fredheim.ligrettoScoresheet.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import no.fredheim.ligrettoScoresheet.R

@Composable
fun IconsRow(
    @DrawableRes leftIconResId: Int,
    @StringRes lefIconDescriptionId: Int,
    onLeft: () -> Unit,
    @DrawableRes rightIconResId: Int,
    @StringRes rightIconDescriptionId: Int,
    onRight: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.icons_row_padding_horizontal),
                top = dimensionResource(id = R.dimen.icons_row_padding_top),
                end = dimensionResource(id = R.dimen.icons_row_padding_horizontal),
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
    ) {
        Image(
            painter = painterResource(id = leftIconResId),
            contentDescription = stringResource(lefIconDescriptionId),
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.icon_size))
                .clickable { onLeft() }
        )
        Image(
            painter = painterResource(id = rightIconResId),
            contentDescription = stringResource(id = rightIconDescriptionId),
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.icon_size))
                .clickable { onRight() }
        )
    }
}