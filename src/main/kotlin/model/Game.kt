package model

data class Game(
    val number: Int,
    val name: String,
    val release: String = "",
    val platform: List<String> = mutableListOf(),
    val mobyScore: String = "",
    val criticScore: String = "",
    val genre: String = "",
    val perspective: String = "",
    val art: String = "",
    val setting: String = "",
    val narrative: String = ""
)