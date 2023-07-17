package no.fredheim.ligrettoScoresheet.model

data class GameState(
    val maxScore: String = "",
    val players: MutableMap<Int, Player> = mutableMapOf(),
    val playersUiState: PlayersUiState = PlayersUiState()
)
