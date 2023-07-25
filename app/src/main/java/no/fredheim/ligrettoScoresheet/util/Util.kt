package no.fredheim.ligrettoScoresheet.util

fun String.isDigitsOnly() = all(Char::isDigit) && isNotEmpty()
