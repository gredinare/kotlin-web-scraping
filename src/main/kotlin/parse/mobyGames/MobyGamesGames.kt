package parse.mobyGames

import model.Console
import model.Game

object MobyGamesGames: MobyGamesParser() {
    fun parserGameList(console: Console): List<Game> {
        val document = pageToDocument(console.link)
        val consoleList = mutableListOf<Console>()

        return mutableListOf()
    }

    fun pagesNumber() {

    }

    fun parserAndSaveGames() {
    }
}