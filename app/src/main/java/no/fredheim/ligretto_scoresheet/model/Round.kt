package no.fredheim.ligretto_scoresheet.model

data class Round(
    val num10s: Int = 0,
    val numCenter: Int = 0,
    val numLigretto: Int = 0,
) {
    fun roundScore(): Int = 2 * num10s + numCenter - 2 * numLigretto
}
