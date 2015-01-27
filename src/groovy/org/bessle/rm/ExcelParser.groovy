package org.bessle.rm

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.ss.util.CellReference

/**
 * Created by uwe on 15.01.15.
 */
class ExcelParser {
    //http://poi.apache.org/spreadsheet/quick-guide.html#Iterator

    def parse(path, boolean readFormulaResult = true) {
        InputStream inp = new FileInputStream(path)
        Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = wb.getSheetAt(0);

        parse(sheet, readFormulaResult)
    }

    def parse(Sheet sheet, boolean readFormulaResult = true) {
        Iterator<Row> rowIt = sheet.rowIterator()
        Row row = rowIt.next()
        def headers = getRowData(row, readFormulaResult)

        def rows = []
        while (rowIt.hasNext()) {
            row = rowIt.next()
            rows << getRowData(row, readFormulaResult)
        }
        return [headers, rows]
    }

    def getRowData(Row row, boolean readFormulaResult) {
        def data = []
        for (Cell cell : row) {
            getValue(row, cell, data, readFormulaResult)
        }
        data
    }

    def getRowReference(Row row, Cell cell) {
        def rowIndex = row.getRowNum()
        def colIndex = cell.getColumnIndex()
        CellReference ref = new CellReference(rowIndex, colIndex)
        ref.getRichStringCellValue().getString()
    }

    def getValue(Row row, Cell cell, List data, boolean readFormulaResult = true) {
        def rowIndex = row.getRowNum()
        def colIndex = cell.getColumnIndex()
        def value = ""
        int cellType = cell.getCellType()
        if (readFormulaResult && (cellType == Cell.CELL_TYPE_FORMULA)) {
            cellType = cell.getCachedFormulaResultType()
            log.info "set cachedFormulaResultType=${cellType} for cell row=${cell.rowIndex} column=${cell.columnIndex}"
        }
        switch (cellType) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    value = cell.getDateCellValue();
                } else {
                    value = cell.getNumericCellValue();
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = cell.getCellFormula();
                break;
            default:
                value = ""
        }
        data[colIndex] = value
        data
    }

    def toXml(header, row) {
        def obj = "<object>\n"
        row.eachWithIndex { datum, i ->
            def headerName = header[i]
            obj += "\t<$headerName>$datum</$headerName>\n"
        }
        obj += "</object>"
    }

    public static void main(String[] args) {
        def filename = 'Arbeitsmappe.xlsx'
        ExcelParser parser = new ExcelParser()
        def (headers, rows) = parser.parse(filename, false)
        println 'Headers'
        println '------------------'
        headers.each { header ->
            println header
        }
        println "\n"
        println 'Rows'
        println '------------------'
        rows.each { row ->
            println parser.toXml(headers, row)
        }
    }
}
