package org.bessle.rm

import grails.transaction.Transactional
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory

@Transactional
class ImportService {
    def excelImportService

    def importExcelFile(File importFile) {
        def excelImporter = new ReviewExcelImporter()
        excelImporter.excelImportService = excelImportService
        Map importResult = [:]
        importResult['action'] = 'import File'
        importResult['file'] = importFile.name
        importResult['sheets'] = [:]
        importFile.withInputStream { fis ->
            Workbook wb = WorkbookFactory.create(fis)
            log.debug "Workbook created for ${importFile}"
            int sheetNumber = wb.getNumberOfSheets()
            log.info("found ${sheetNumber} sheet${sheetNumber>1?'s':''} in workbook")
            Map sheetMap = [:]
            (0..sheetNumber-1).each() { int i ->
                log.info("retrieving Sheet ${i}")
                Sheet sheet = wb.getSheetAt(i)
                String sheetName = sheet.getSheetName()
                log.info "found Sheet ${sheetName}"
                sheetMap[sheetName] = sheet
            }
            if (sheetMap.containsKey('Review')) {
                // import Reviews from Review sheet into Review table
                importResult.sheets['Review'] = excelImporter.importReviewSheet(sheetMap['Review'])
            }

            if (sheetMap.containsKey("Anforderungen")) {
                // import Requirements from Anforderungen sheet into Requirements table
                importResult.sheets['Anforderungen'] = excelImporter.importRequirementSheet(sheetMap['Anforderungen'])
            }
            if (sheetMap.containsKey("Requirements")) {
                // import Requirements from Requirements sheet into Requirements table
                importResult.sheets['Anforderungen'] = excelImporter.importRequirementSheet(sheetMap['Anforderungen'])
            }
            if (sheetMap.containsKey("Architekturentscheidungen")) {
                // import ArchitecturalDecisions from Architekturentscheidungen sheet into ArchitecturalDecision table
                importResult.sheets['Architekturentscheidungen'] = excelImporter.importArchitecturalDecisionSheet(sheetMap['Architekturentscheidungen'])
            }
            if (sheetMap.containsKey("ArchitecturalDecision")) {
                // import ArchitecturalDecisions from Architekturentscheidungen sheet into ArchitecturalDecision table
            }
            if (sheetMap.containsKey("Mapping")) {
                // import mappings from Mapping sheet into Sensitive table
            }
            if (sheetMap.containsKey("Handlungsempfehlungen")) {
                // import Findings from Handlungsempfehlungen sheet into Findings table
            }
            if (sheetMap.containsKey("Findings")) {
                // import Findings from Findings sheet into Findings table
            }

//            wb.getSheet("Review")
//            Sheet sheet = wb.getSheetAt(0);
        }
        return importResult
    }

}
