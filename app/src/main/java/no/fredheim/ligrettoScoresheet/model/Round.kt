package no.fredheim.ligrettoScoresheet.model

import no.fredheim.ligrettoScoresheet.service.CalculationService

data class Round(
    val num10s: String = "",
    val numCenter: String = "",
    val numLigretto: String = ""
) {
    fun points(): Int = CalculationService.points(num10s, numCenter, numLigretto)
}
