package parse.mobyGames

import model.Console

class MobyGames {
    fun parseAll() {
        val test = MobyGamesConsoles.parserConsoleList()
        test.forEach { console ->
            val games = MobyGamesGames(console)
            games.parserAndSaveGames()
        }
    }

    fun parseAndSave() {
        val console = Console(
            "Windows",
            "79,475 games",
            "1993–2023",
            "https://www.mobygames.com/platform/windows/"
        )

        val games = MobyGamesGames(console)
        games.parserAndSaveGames()
    }
}