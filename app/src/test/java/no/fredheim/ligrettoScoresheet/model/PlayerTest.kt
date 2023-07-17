package no.fredheim.ligrettoScoresheet.model

import io.kotest.matchers.shouldBe
import no.fredheim.ligrettoScoresheet.Util
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `given first two rounds as described, total score for the game should be 14`() {
        // Alex cards first round: 0 tens, 8 center and 5 ligretto cards
        // Alex cards second round: 1 ten, 14 center and 0 ligretto cards
        Util.alex.score() shouldBe 14
    }
}