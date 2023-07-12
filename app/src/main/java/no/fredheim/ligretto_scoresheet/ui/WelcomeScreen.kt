package no.fredheim.ligretto_scoresheet.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.fredheim.ligretto_scoresheet.R
import no.fredheim.ligretto_scoresheet.ui.theme.LigrettoScoresheetTheme

@Composable
fun WelcomeScreen(
    maxScore: String,
    onMaxScoreChange: (String) -> Unit,
    onStartGameButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to the ligretto score sheet app",
            modifier = Modifier.padding(8.dp)
        )
        EditNumberField(
            label = R.string.choose_max_score,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            value = maxScore,
            onValueChange = onMaxScoreChange,
            modifier = Modifier.padding(8.dp)
        )
        Button(
            onClick = onStartGameButtonClick,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "Start game"
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = {
            Text(
                text = stringResource(label),
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth()
            )
        },
        keyboardOptions = keyboardOptions,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun WelcomeScreenPreview() {
    LigrettoScoresheetTheme {
        WelcomeScreen(
            maxScore = "",
            onMaxScoreChange = {},
            onStartGameButtonClick = { /*TODO*/ }
        )
    }
}