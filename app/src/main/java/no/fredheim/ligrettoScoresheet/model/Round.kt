package no.fredheim.ligrettoScoresheet.model

import no.fredheim.ligrettoScoresheet.service.Calculate

data class Round(
    val number: Int,
    val num10s: String = "",
    val numCenter: String = "",
    val numLigretto: String = ""
) {
    fun points(): Int = Calculate.points(num10s, numCenter, numLigretto)
}
