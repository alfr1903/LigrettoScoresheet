package no.fredheim.ligrettoScoresheet.ui

import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.shouldBe
import no.fredheim.ligrettoScoresheet.model.Round
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoBlue
import no.fredheim.ligrettoScoresheet.ui.theme.LigrettoLightPink
import no.fredheim.ligrettoScoresheet.ui.theme.PlayerColors
import no.fredheim.ligrettoScoresheet.util.Players
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

        viewModel.addPlayer(Players.alex)
        viewModel.gameState.value.players.shouldContain(1, Players.alex)
    }

    @Test
    fun `adding a player removes the players chosen color from available colors`() {
        viewModel.gameState.value.playersUiState.availableColors shouldBe  PlayerColors

        viewModel.addPlayer(Players.alex)
        viewModel.gameState.value.playersUiState.availableColors shouldBe
                PlayerColors.minus(Players.alex.color)
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
            viewModel.gameState.value.playersUiState.chosenColor shouldBe LigrettoLightPink

            viewModel.updateChosenColor(LigrettoBlue)
            viewModel.gameState.value.playersUiState.chosenColor shouldBe LigrettoBlue
        }
    }
    @Nested
    inner class WithTwoPlayersAdded {

        @BeforeEach
        fun `add two players`() {
            viewModel.addPlayer(Players.alex.copy(round = mutableMapOf()))
            viewModel.addPlayer(Players.thao.copy(round = mutableMapOf()))
        }

        @Test
        fun `rounds with default values can be created for all participants`() {
            viewModel.initNextRoundAllPlayers(firstRound = true)
            viewModel.gameState.value.players[1]!!.round[1] shouldBe Round(1)
            viewModel.gameState.value.players[2]!!.round[1] shouldBe Round(1)
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