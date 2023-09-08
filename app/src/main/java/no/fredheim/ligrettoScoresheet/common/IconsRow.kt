package no.fredheim.ligrettoScoresheet.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import no.fredheim.ligrettoScoresheet.R
import no.fredheim.ligrettoScoresheet.model.Icon

@Composable
fun IconsRow(
    leftIcon: Icon,
    onLeft: () -> Unit,
    rightIcon: Icon,
    onRight: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Image(
            painter = painterResource(id = leftIcon.resId),
            contentDescription = stringResource(leftIcon.descriptionId),
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.icon_size))
                .clickable { onLeft() }
        )
        Image(
            painter = painterResource(id = rightIcon.resId),
            contentDescription = stringResource(id = rightIcon.descriptionId),
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.icon_size))
                .clickable { onRight() }
        )
    }
}
