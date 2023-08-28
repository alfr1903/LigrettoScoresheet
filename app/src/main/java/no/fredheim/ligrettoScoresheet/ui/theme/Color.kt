package no.fredheim.ligrettoScoresheet.ui.theme

import androidx.compose.ui.graphics.Color

enum class PlayerColor(val color: Color) {
    // Red box
    LightPink(Color(0xFFD2AFB6)),
    Black(Color(0xFF3A3A3D)),
    LightRed(Color(0xFFD4857F)),
    Blue(Color(0xFF4D98D0)),

    // Blue box
    Orange(Color(0xFFE76F45)),
    LightBlue(Color(0xFFA4D0F3)),
    Gray(Color(0xFF6B717D)),
    Yellow(Color(0xFFC7AD3D)),

    // Green box
    Green(Color(0xFFA8B936)),
    Brown(Color(0xFF7D6347)),
    Purple(Color(0xFF9C89C5)),
    Pink(Color(0xFFE9B1D4))
}

enum class ThemeColor(val color: Color) {
    DarkBlue(Color(0xFF1655A6)),
    Yellow(Color(0xFFFCCD04)),
    Green(Color(0xFF65B238)),
    Blue(Color(0xFF0B7CC8)),
    Red(Color(0xFFDA2427)),
    Orange(Color(0xFFF27F34)),
    DarkRed(Color(0xFF891715)),
    DarkGreen(Color(0xFF017938))
}

enum class TextColor(val color: Color) {
    Black(Color.Black),
    White(Color.White)
}

const val PreviewThemeOrange = 0xFFF29634
const val PreviewThemeBlue = 0xFF0B7CC8



