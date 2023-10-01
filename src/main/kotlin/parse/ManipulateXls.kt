package parse

import model.GameModel
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.FileInputStream
import java.io.FileOutputStream

class ManipulateXls(
    private val file: String
) {
    private lateinit var workbook: Workbook

    private fun saveGame(gameModel: GameModel) {
        val sheet = workbook.getSheetAt(0)
        val row = sheet.createRow(gameModel.id)

        row.createCell(0).setCellValue(gameModel.id.toDouble())
        row.createCell(1).setCellValue(gameModel.name)
        row.createCell(2).setCellValue(gameModel.release)
        row.createCell(3).setCellValue(gameModel.platform.toString())
        row.createCell(4).setCellValue(gameModel.score)
        row.createCell(5).setCellValue(gameModel.genre)
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