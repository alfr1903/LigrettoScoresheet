package no.fredheim.ligretto_scoresheet.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
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
import no.fredheim.ligretto_scoresheet.Util
import no.fredheim.ligretto_scoresheet.common.Counter
import no.fredheim.ligretto_scoresheet.model.CardType
import no.fredheim.ligretto_scoresheet.model.Player
import no.fredheim.ligretto_scoresheet.service.CalculationService
import no.fredheim.ligretto_scoresheet.ui.theme.LigrettoScoresheetTheme

@Composable
fun PlayerRoundScoreScreen(
    player: Player,
    round: Int,
    onSubtractCard: (CardType) -> Unit,
    onNumCardsChange: (CardType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 60.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(240.dp)
                .height(40.dp)
                .background(color = player.color, shape = CircleShape)
        ) {
            Text(
                text = player.name,
            )
        }
        Divider(Modifier.padding(top = 32.dp, bottom = 32.dp))
        CardPointsCalculator(
            image = R.drawable.playing_cards,
            imageDescription = R.string.playing_cards,
            description = R.string.tens,
            cardType = CardType.Ten,
            numCards = player.round[round]!!.num10s,
            onNumCardsChange = {  },
        )
        CardPointsCalculator(
            image = R.drawable.playing_cards,
            imageDescription = R.string.playing_cards,
            description = R.string.center,
            cardType = CardType.Center,
            numCards = player.round[round]!!.num10s,
            onNumCardsChange = {  },
        )
        CardPointsCalculator(
            image = R.drawable.playing_cards,
            imageDescription = R.string.playing_cards,
            description = R.string.ligretto,
            cardType = CardType.Ligretto,
            numCards = player.round[round]!!.num10s,
            onNumCardsChange = {  },
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardPointsCalculator(
    @DrawableRes image: Int,
    @StringRes imageDescription: Int,
    @StringRes description: Int,
    cardType: CardType,
    numCards: Int,
    onNumCardsChange: () -> Unit,
    modifier: Modifier = Modifier
) {
   Row(
       verticalAlignment = Alignment.CenterVertically,
       modifier = modifier
   ) {
       Column(horizontalAlignment = Alignment.CenterHorizontally) {
           Image(
               painter = painterResource(image),
               contentDescription = stringResource(imageDescription)
           )
           Text(text = stringResource(description))
       }
       Text(
           text = stringResource(
               R.string.points,
               CalculationService.calculate(cardType, numCards)
           )
       )

   }
}


@Preview(
    showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun PlayerRoundScoreScreenPreview() {
    LigrettoScoresheetTheme {
        PlayerRoundScoreScreen(
            player = Util.alex,
            round = 1,
            onSubtractCard = {  },
            onNumCardsChange = {  },
        )
    }

}