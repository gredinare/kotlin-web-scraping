package model

data class Game(
    val name: String,
    val genre: String,
    val release: String,
    val score: String,
    val platform: List<String>
)