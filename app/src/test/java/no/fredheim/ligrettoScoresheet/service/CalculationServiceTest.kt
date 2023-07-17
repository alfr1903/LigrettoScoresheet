package no.fredheim.ligrettoScoresheet.service

import io.kotest.matchers.shouldBe
import no.fredheim.ligrettoScoresheet.Util
import no.fredheim.ligrettoScoresheet.model.CardType
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CalculationServiceTest {

    @Nested
    inner class Incrementing {

        @Test
        fun `given non number input increment function should set score to 1`() {
            CalculationService.increment("") shouldBe "1"
            CalculationService.increment(",") shouldBe "1"
            CalculationService.increment(".") shouldBe "1"
            CalculationService.increment("-") shouldBe "1"
            CalculationService.increment(" ") shouldBe "1"
        }

        @Test
        fun `given 1 increment function should set score to 2`() {
            CalculationService.increment("1") shouldBe "2"
        }

        @Test
        fun `given 2 increment function should set score to 3`() {
            CalculationService.increment("2") shouldBe "3"
        }
    }

    @Nested
    inner class Decrementing {

        @Test
        fun `given 3 decrement function should set score to 2`() {
            CalculationService.decrement("3") shouldBe "2"
        }

        @Test
        fun `given 2 decrement function should set score to 1`() {
            CalculationService.decrement("2") shouldBe "1"
        }

        @Test
        fun `given 1 decrement function should set score to empty string`() {
            CalculationService.decrement("1") shouldBe ""
        }

        @Test
        fun `given empty input decrement function should set score to empty string`() {
            CalculationService.decrement("") shouldBe ""
        }
    }

    @Nested
    inner class PilePoints {

        @Nested
        inner class Tens {

            @Test
            fun `given illegal input in the tens pile the points for that pile should be 0`() {
                CalculationService.points(CardType.Ten, "") shouldBe "0"
            }

            @Test
            fun `given 0 card in the tens pile the points for that pile should be 0`() {
                CalculationService.points(CardType.Ten, "0") shouldBe "0"
            }

            @Test
            fun `given 1 card in the tens pile the points for that pile should be 2`() {
                CalculationService.points(CardType.Ten, "1") shouldBe "2"
            }

            @Test
            fun `given 2 cards in the tens pile the points for that pile should be 4`() {
                CalculationService.points(CardType.Ten, "2") shouldBe "4"
            }
        }

        @Nested
        inner class Center {

            @Test
            fun `given illegal input in the center pile the points for that pile should be 0`() {
                CalculationService.points(CardType.Center, "") shouldBe "0"
            }

            @Test
            fun `given 0 card in the center pile the points for that pile should be 0`() {
                CalculationService.points(CardType.Center, "0") shouldBe "0"
            }

            @Test
            fun `given 1 card in the center pile the points for that pile should be 1`() {
                CalculationService.points(CardType.Center, "1") shouldBe "1"
            }

            @Test
            fun `given 2 cards in the center pile the points for that pile should be 2`() {
                CalculationService.points(CardType.Center, "2") shouldBe "2"
            }
        }

        @Nested
        inner class Ligretto {

            @Test
            fun `given illegal input in the ligretto pile the points for that pile should be 0`() {
                CalculationService.points(CardType.Ligretto, "") shouldBe "0"
            }

            @Test
            fun `given 0 card in the ligretto pile the points for that pile should be 0`() {
                CalculationService.points(CardType.Ligretto, "0") shouldBe "0"
            }

            @Test
            fun `given 1 card in the ligretto pile the points for that pile should be minus 2`() {
                CalculationService.points(CardType.Ligretto, "1") shouldBe "-2"
            }

            @Test
            fun `given 2 cards in the ligretto pile the points for that pile should be minus 4`() {
                CalculationService.points(CardType.Ligretto, "2") shouldBe "-4"
            }
        }
    }

    @Nested
    inner class RoundPoints {

        @Test
        fun `given empty inputs round score should be minus 0`() {
            CalculationService.points("", "", "") shouldBe 0
        }

        @Test
        fun `given 0 tens, 8 center and 5 ligretto cards round score should be minus 2`() {
            CalculationService.points("0", "8", "5") shouldBe -2
        }

        @Test
        fun `given 1 ten, 14 center and 0 ligretto cards round score should be 16`() {
            CalculationService.points("1", "14", "0") shouldBe 16
        }
    }
}