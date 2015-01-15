//@Grapes([
//        @Grab(group='org.apache.poi', module='poi', version='3.11'),
//        @Grab(group='org.apache.poi', module='poi-ooxml', version='3.11'),
//        @Grab(group='org.apache.poi', module='ooxml-schemas', version='1.1')
//])

package org.bessle.util.excel

import org.apache.poi.ss.usermodel.*
import org.apache.poi.hssf.usermodel.*
import org.apache.poi.xssf.usermodel.*
import org.apache.poi.ss.util.*
import org.apache.poi.ss.usermodel.*

/**
 * Created by uwe on 12.01.15.
 */
class ExcelParser {
        //http://poi.apache.org/spreadsheet/quick-guide.html#Iterator
//        Workbook readExcelFile(String path) {
//            WorbookFactory.create(new FileInputStream(path))
//        }

    def parse(path, boolean readFormulaResult = true) {
        Workbook wb = readExcelFile(path)
        Sheet sheet = wb.getSheetAt(0);

        ArrayList headers = getHeaderList(sheet, readFormulaResult)

        Iterator<Row> rowIt = sheet.rowIterator()
        rowIt.next()
        def rows = []
        while(rowIt.hasNext()) {
            row = rowIt.next()
            rows << getRowData(row, readFormulaResult)
        }
        [headers, rows]
    }

    private ArrayList getHeaderList(Sheet sheet, boolean readFormulaResult) {
        Iterator<Row> rowIt = sheet.rowIterator()
        Row row = rowIt.next()
        def headers = getRowData(row, readFormulaResult)
        return headers
    }

    private Workbook readExcelFile(path) {
        InputStream inp = new FileInputStream(path)
        Workbook wb = WorkbookFactory.create(inp);
        return wb
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
        if (readFormulaResult && (cellType == Cell.CELL_TYPE_FORMULA)) { cellType = cell.getCachedFormulaResultType() }
        switch (cellType ) {
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

}
