package org.bessle.rm

import grails.transaction.Transactional
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory

@Transactional
class ImportService {
    def excelImportService

    def importExcelFile(File importFile) {
        Map importResult = [:]
        importResult['action'] = 'import File'
        importResult['file'] = importFile.name
        importResult['sheets'] = [:]
        importFile.withInputStream { fis ->
            Workbook wb = WorkbookFactory.create(fis)
            log.debug "Workbook created for ${importFile}"
            int sheetNumber = wb.getNumberOfSheets()
            log.info("found ${sheetNumber} sheet${sheetNumber>1?s:''} in workbook")
            Map sheetMap = [:]
            for ( i in [0..sheetNumber-1]) {
                Sheet sheet = wb.getSheetAt(i)
                String sheetName = sheet.getSheetName()
                log.info "found Sheet ${sheetName}"
                sheetMap[sheetName] = sheet
            }
            if (sheetMap.containsKey('Review')) {
                // import Reviews from Review sheet into Review table
                importResult.sheets['Review'] = importReviewSheet(sheetMap['Review'])
            }

            if (sheetMap.containsKey("Anforderungen")) {
                // import Requirements from Anforderungen sheet into Requirements table
            }
            if (sheetMap.containsKey("Requirements")) {
                // import Requirements from Requirements sheet into Requirements table
            }
            if (sheetMap.containsKey("Architekturentscheidungen")) {
                // import ArchitecturalDecisions from Architekturentscheidungen sheet into ArchitecturalDecision table
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

    def importReviewSheet(Sheet reviewSheet) {
        log.info "import sheet ${reviewSheet}"
        Map importResult = ['reviews':[]]
        List reviewMapList = excelImportService.convertColumnMapManyRows(reviewSheet,REVIEW_IMPORT_CONFIG,REVIEW_IMPORT_CONFIG.startRow)
        reviewMapList.each { Map reviewParams ->
            log.debug("reviewParams:${reviewParams}")
            def reviewImportResult = [:]
            reviewImportResult['reviewParams'] = reviewParams
            Review review = Review.findByCompanyAndProjectName(reviewParams.company,reviewParams.projectName)
            if (review) {
                review.properties = reviewParams
                reviewImportResult['action'] = 'update'
            } else {
                review = new Review(reviewParams)
                reviewImportResult['action'] = 'create'
            }
            if (!review.save()) {
                println "Review not saved, errors = ${review.errors}"
                reviewImportResult['status'] = 'Review not saved'
                reviewImportResult['errors'] = review.errors
            } else {
                reviewImportResult['status'] = 'ok'
            }
            importResult.reviews.add(reviewImportResult)
        }
        println("importReviewSheet() importResult=${importResult}")
        return importResult
    }


    static REVIEW_IMPORT_CONFIG = [
            sheet:'Review',
            startRow: 1,
            columnMap: [
                    'A' : 'company',
                    'B' : 'projectName',
                    'C' : 'startDate',
                    'D' : 'endDate'
            ]
    ]
}
