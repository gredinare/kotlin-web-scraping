package data

import model.Console
import model.Game
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.FileInputStream
import java.io.FileOutputStream

class ManipulateXls(
    private val file: String
) {
    private var workbook: Workbook = WorkbookFactory.create(FileInputStream(file))

    fun saveFile() {
        val fileOut = FileOutputStream(file)
        workbook.write(fileOut)
        fileOut.close()
    }

    fun saveConsolesInXls(consoleList: List<Console>) {
        val sheet = workbook.getSheetAt(0)

        for(console in consoleList) {
            val lastRow = sheet.lastRowNum
            val newRow = sheet.createRow(lastRow + 1)

            newRow.createCell(0).setCellValue(console.platform)
            newRow.createCell(1).setCellValue(console.games)
            newRow.createCell(2).setCellValue(console.years)
            newRow.createCell(3).setCellValue(console.link)
        }
    }

    fun saveGamesInXls(gameList: List<Game>) {
        val sheet = workbook.getSheetAt(1)

        for(game in gameList) {
            val lastRow = sheet.lastRowNum
            val newRow = sheet.createRow(lastRow + 1)

            newRow.createCell(0).setCellValue(game.name)
            newRow.createCell(1).setCellValue(game.genre)
            newRow.createCell(2).setCellValue(game.release)
            newRow.createCell(3).setCellValue(game.platform)
        }

        val fileOut = FileOutputStream(file)
        workbook.write(fileOut)
        fileOut.close()
    }

    fun readLastConsoleInConsoleList(): Console? {
        val sheet = workbook.getSheetAt(0)

        val lastRow = sheet.lastRowNum

        if(lastRow < 1) return null

        val row = sheet.getRow(lastRow)

        val platform = row.getCell(0).stringCellValue
        val games = row.getCell(1).stringCellValue
        val years = row.getCell(2).stringCellValue
        val link = row.getCell(3).stringCellValue

        return Console(
            platform,
            games,
            years,
            link
        )
    }

    fun removeLastRow(): Boolean {
        val sheet = workbook.getSheetAt(0)

        val lastRow = sheet.lastRowNum

        if (lastRow < 1) {
            return false
        }

        sheet.removeRow(sheet.getRow(lastRow))

        val fileOut = FileOutputStream(file)
        workbook.write(fileOut)
        fileOut.close()

        return true
    }
}