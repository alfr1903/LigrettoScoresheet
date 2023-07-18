package no.fredheim.ligrettoScoresheet.ui

import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.shouldBe
import no.fredheim.ligrettoScoresheet.Util
import no.fredheim.ligrettoScoresheet.model.Round
import no.fredheim.ligrettoScoresheet.ui.theme.Colors
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoBlue
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoOrange
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class LigrettoViewModelTest {
    private val viewModel = LigrettoViewModel()

    @Test
    fun `max score is updated correctly`() {
        viewModel.gameState.value.maxScore shouldBe ""

        viewModel.updateMaxScore("40")
        viewModel.gameState.value.maxScore shouldBe "40"
    }

    @Test
    fun `player is added correctly`() {
        viewModel.gameState.value.players.shouldBeEmpty()

        viewModel.addPlayer(Util.alex)
        viewModel.gameState.value.players.shouldContain(1, Util.alex)
    }

    @Test
    fun `adding a player removes the players chosen color from available colors`() {
        viewModel.gameState.value.playersUiState.availableColors shouldBe  Colors

        viewModel.addPlayer(Util.alex)
        viewModel.gameState.value.playersUiState.availableColors shouldBe
                Colors.minus(Util.alex.color)
    }

    @Nested
    inner class PlayersUiState {

        @Test
        fun `name input is stored in gameState`() {
            viewModel.gameState.value.playersUiState.name shouldBe ""

            viewModel.updateName("Alex")
            viewModel.gameState.value.playersUiState.name shouldBe "Alex"
        }

        @Test
        fun `chosen color is stored in gameState`() {
            viewModel.gameState.value.playersUiState.chosenColor shouldBe LigrettoOrange

            viewModel.updateChosenColor(LigrettoBlue)
            viewModel.gameState.value.playersUiState.chosenColor shouldBe LigrettoBlue
        }
    }
    @Nested
    inner class WithTwoPlayersAdded {

        @BeforeEach
        fun `add two players`() {
            viewModel.addPlayer(Util.alex.copy(round = mutableMapOf()))
            viewModel.addPlayer(Util.thao.copy(round = mutableMapOf()))
        }

        @Test
        fun `rounds with default values can be created for all participants`() {
            viewModel.initNextRoundAllPlayers()
            viewModel.gameState.value.players[1]!!.round[1] shouldBe Round(1)
            viewModel.gameState.value.players[2]!!.round[1] shouldBe Round(1)
        }

        @Test
        fun `current round is incremented when next round is initiated`() {
            viewModel.currentRound shouldBe 0

            viewModel.initNextRoundAllPlayers()
            viewModel.currentRound shouldBe 1
        }

        @Test
        fun `adding a player round should increment player index by 1`() {
            viewModel.currentPlayerIndex shouldBe 1

            viewModel.addRoundCurrentPlayer(Round(1, "1", "13", "0"))
            viewModel.currentPlayerIndex shouldBe 2
        }

        @Test
        fun `adding a player round for the last player should not increment player index`() {
            viewModel.addRoundCurrentPlayer(Round(1, "1", "13", "0"))
            viewModel.currentPlayerIndex shouldBe 2

            viewModel.addRoundCurrentPlayer(Round(1, "1", "13", "0"))
            viewModel.currentPlayerIndex shouldBe 2
        }
    }

}