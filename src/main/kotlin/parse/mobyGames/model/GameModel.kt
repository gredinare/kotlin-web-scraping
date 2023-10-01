package parse.mobyGames.model

data class GameModel(
    val id: Int,
    val name: String,
    val release: String,
    val platform: List<String>,
    val score: Double,
    val genre: String
)