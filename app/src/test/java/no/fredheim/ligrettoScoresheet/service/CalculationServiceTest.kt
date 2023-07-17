package no.fredheim.ligrettoScoresheet.service

import io.kotest.matchers.shouldBe
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
    }

}