package model

data class GameModel(
    val id: Int,
    val name: String,
    val release: String,
    val platform: List<String>,
    val score: String,
    val genre: String
)