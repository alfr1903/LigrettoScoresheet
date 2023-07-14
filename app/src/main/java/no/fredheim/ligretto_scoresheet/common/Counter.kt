package no.fredheim.ligretto_scoresheet.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligretto_scoresheet.R
import no.fredheim.ligretto_scoresheet.service.CalculationService
import no.fredheim.ligretto_scoresheet.ui.theme.LigrettoScoresheetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Counter(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = R.drawable.subtract),
            contentDescription = stringResource(R.string.subtract),
            modifier = Modifier.clickable {
                onValueChange(CalculationService.decrement(value))
            }
        )
        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(16.dp)
                .width(60.dp),
            placeholder = {
                Text(
                    text = "0",
                    textAlign = TextAlign.Center,
                    style = LocalTextStyle.current.copy(color = Color.Gray),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
        Image(
            painter = painterResource(id = R.drawable.add),
            contentDescription = stringResource(R.string.add),
            modifier = Modifier.clickable {
                onValueChange(CalculationService.increment(value))
            }
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun CounterPreview() {
    LigrettoScoresheetTheme {
        Counter(value = "", onValueChange = {})
    }
}
