package data

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
}