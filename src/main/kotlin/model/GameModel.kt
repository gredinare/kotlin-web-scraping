package model

data class GameModel(
    val id: Int,
    val name: String,
    val release: String = "",
    val platform: List<String> = listOf(),
    val score: String = "",
    val genre: String = ""
)