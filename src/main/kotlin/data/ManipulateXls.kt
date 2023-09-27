package data

import model.Game
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.FileInputStream
import java.io.FileOutputStream

class ManipulateXls(
    private val file: String
) {
    private lateinit var workbook: Workbook

    private fun saveGame(game: Game) {
        val sheet = workbook.getSheetAt(0)
        val row = sheet.createRow(game.number)

        row.createCell(0).setCellValue(game.number.toDouble())
        row.createCell(1).setCellValue(game.name)
        row.createCell(2).setCellValue(game.release)
        row.createCell(3).setCellValue(game.mobyScore)
        row.createCell(4).setCellValue(game.criticScore)
        row.createCell(5).setCellValue(game.genre)
        row.createCell(6).setCellValue(game.perspective)
        row.createCell(7).setCellValue(game.art)
        row.createCell(8).setCellValue(game.setting)
        row.createCell(9).setCellValue(game.narrative)
        row.createCell(10).setCellValue(game.platform.toString())
    }

    private fun saveFile() {
        val fileOut = FileOutputStream(file)
        workbook.write(fileOut)
        fileOut.close()
    }

    fun saveGames(listGames: List<Game>) {
        workbook = WorkbookFactory.create(FileInputStream(file))

        listGames.forEach { game ->
            saveGame(game)
        }

        saveFile()
    }
}