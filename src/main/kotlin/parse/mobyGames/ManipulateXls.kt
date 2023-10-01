package parse.mobyGames

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import parse.mobyGames.model.GameModel
import java.io.FileInputStream
import java.io.FileOutputStream

class ManipulateXls(
    private val file: String
) {
    private lateinit var workbook: Workbook

    private fun saveGame(gameModel: GameModel) {
        val sheet = workbook.getSheetAt(0)
        val row = sheet.createRow(gameModel.number)

        row.createCell(0).setCellValue(gameModel.number.toDouble())
        row.createCell(1).setCellValue(gameModel.name)
        row.createCell(2).setCellValue(gameModel.release)
        row.createCell(3).setCellValue(gameModel.mobyScore)
        row.createCell(4).setCellValue(gameModel.criticScore)
        row.createCell(5).setCellValue(gameModel.genre)
        row.createCell(6).setCellValue(gameModel.perspective)
        row.createCell(7).setCellValue(gameModel.art)
        row.createCell(8).setCellValue(gameModel.setting)
        row.createCell(9).setCellValue(gameModel.narrative)
        row.createCell(10).setCellValue(gameModel.platform.toString())
    }

    private fun saveFile() {
        val fileOut = FileOutputStream(file)
        workbook.write(fileOut)
        fileOut.close()
    }

    fun saveGames(listGameModels: List<GameModel>) {
        workbook = WorkbookFactory.create(FileInputStream(file))

        listGameModels.forEach { game ->
            saveGame(game)
        }

        saveFile()
    }
}