package no.fredheim.ligrettoScoresheet.service

import io.kotest.matchers.shouldBe
import no.fredheim.ligrettoScoresheet.model.CardType
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CalculateTest {

    @Nested
    inner class Incrementing {

        @Test
        fun `given non number input increment function should return input`() {
            Calculate.increment(",") shouldBe ","
        }

        @Test
        fun `given empty input increment function should set score to 1`() {
            Calculate.increment("") shouldBe "1"
        }

        @Test
        fun `given zero input increment function should set score to 1`() {
            Calculate.increment("0") shouldBe "1"
        }

        @Test
        fun `given 1 increment function should set score to 2`() {
            Calculate.increment("1") shouldBe "2"
        }

        @Test
        fun `given 2 increment function should set score to 3`() {
            Calculate.increment("2") shouldBe "3"
        }
    }

    @Nested
    inner class Decrementing {

        @Test
        fun `given 3 decrement function should set score to 2`() {
            Calculate.decrement("3") shouldBe "2"
        }

        @Test
        fun `given 2 decrement function should set score to 1`() {
            Calculate.decrement("2") shouldBe "1"
        }

        @Test
        fun `given 1 decrement function should set score to empty string`() {
            Calculate.decrement("1") shouldBe ""
        }

        @Test
        fun `given empty input decrement function should set score to empty string`() {
            Calculate.decrement("") shouldBe ""
        }
    }

    @Nested
    inner class PilePoints {

        @Nested
        inner class Tens {

            @Test
            fun `given illegal input in the tens pile the points for that pile should be 0`() {
                Calculate.points(CardType.Ten, "") shouldBe 0
            }

            @Test
            fun `given 0 card in the tens pile the points for that pile should be 0`() {
                Calculate.points(CardType.Ten, "0") shouldBe 0
            }

            @Test
            fun `given 1 card in the tens pile the points for that pile should be 2`() {
                Calculate.points(CardType.Ten, "1") shouldBe 2
            }

            @Test
            fun `given 2 cards in the tens pile the points for that pile should be 4`() {
                Calculate.points(CardType.Ten, "2") shouldBe 4
            }
        }

        @Nested
        inner class Center {

            @Test
            fun `given illegal input in the center pile the points for that pile should be 0`() {
                Calculate.points(CardType.Center, "") shouldBe 0
            }

            @Test
            fun `given 0 card in the center pile the points for that pile should be 0`() {
                Calculate.points(CardType.Center, "0") shouldBe 0
            }

            @Test
            fun `given 1 card in the center pile the points for that pile should be 1`() {
                Calculate.points(CardType.Center, "1") shouldBe 1
            }

            @Test
            fun `given 2 cards in the center pile the points for that pile should be 2`() {
                Calculate.points(CardType.Center, "2") shouldBe 2
            }
        }

        @Nested
        inner class Ligretto {

            @Test
            fun `given illegal input in the ligretto pile the points for that pile should be 0`() {
                Calculate.points(CardType.Minus, "") shouldBe 0
            }

            @Test
            fun `given 0 card in the ligretto pile the points for that pile should be 0`() {
                Calculate.points(CardType.Minus, "0") shouldBe 0
            }

            @Test
            fun `given 1 card in the ligretto pile the points for that pile should be minus 2`() {
                Calculate.points(CardType.Minus, "1") shouldBe -2
            }

            @Test
            fun `given 2 cards in the ligretto pile the points for that pile should be minus 4`() {
                Calculate.points(CardType.Minus, "2") shouldBe -4
            }
        }
    }

    @Nested
    inner class RoundPoints {

        @Test
        fun `given empty inputs round score should be minus 0`() {
            Calculate.points("", "", "") shouldBe 0
        }

        @Test
        fun `given 0 tens, 8 center and 5 ligretto cards round score should be minus 2`() {
            Calculate.points("0", "8", "5") shouldBe -2
        }

        @Test
        fun `given 1 ten, 14 center and 0 ligretto cards round score should be 16`() {
            Calculate.points("1", "14", "0") shouldBe 16
        }
    }
}