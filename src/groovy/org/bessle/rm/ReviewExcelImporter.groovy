package org.bessle.rm

import org.apache.poi.ss.usermodel.Sheet
import org.apache.commons.logging.LogFactory

/**
 * Created by uwe on 16.01.15.
 */
class ReviewExcelImporter {
    private static final log = LogFactory.getLog(this)

    def excelImportService

    def importReviewSheet(Sheet reviewSheet) {
        log.info "import sheet ${reviewSheet}"
        Map importResult = ['reviews':[]]
        List reviewMapList = excelImportService.convertColumnMapManyRows(reviewSheet,REVIEW_IMPORT_CONFIG,REVIEW_IMPORT_CONFIG.startRow)
        reviewMapList.each { Map reviewParams ->
            log.debug("requirementParams:${reviewParams}")
            def reviewImportResult = [:]
            reviewImportResult['requirementParams'] = reviewParams
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

    def importRequirementSheet(Sheet requirementSheet) {
        log.info "import sheet ${requirementSheet}"
        Map importResult = ['requirements':[]]
        Map reviewParamMap = excelImportService.convertColumnMapOneRow(
                requirementSheet,                               //sheet
                REQUIREMENT_REVIEW_IMPORT_CONFIG,               //config
                REQUIREMENT_REVIEW_IMPORT_CONFIG.startRow      //startRow
        )
        log.info "importRequirementSheet: found Review param map ${reviewParamMap}"
        Review review = Review.createCriteria().get {
            reviewParamMap.each {key,value ->
                delegate.eq(key, value)
            }
        }
        List requirementMapList = excelImportService.convertColumnMapManyRows(requirementSheet,REQUIREMENT_IMPORT_CONFIG,REQUIREMENT_IMPORT_CONFIG.startRow)
        requirementMapList.each { Map requirementParams ->
            log.debug("requirementParams:${requirementParams}")
            def requirementImportResult = [:]
            requirementImportResult['requirementParams'] = requirementParams
            requirementParams['review'] = review
            requirementParams['qualityGroup'] = QualityGroup.findByCode(requirementParams['qualityGroupCode'])
            Requirement requirement = Requirement.findByReqNumber(requirementParams.reqNumber)
            if (requirement) {
                requirement.properties = requirementParams
                requirementImportResult['action'] = 'update'
            } else {
                requirement = new Requirement(requirementParams)
                requirementImportResult['action'] = 'create'
            }
            if (!requirement.save()) {
                println "Requirement not saved, errors = ${requirement.errors}"
                requirementImportResult['status'] = 'Requirement not saved'
                requirementImportResult['errors'] = requirement.errors
            } else {
                requirementImportResult['status'] = 'ok'
            }
            importResult.requirements.add(requirementImportResult)
        }
        log.info("importRequirementSheet() importResult=${importResult}")
        return importResult
    }


    def importArchitecturalDecisionSheet(Sheet architecturalDecisionSheet) {
        log.info "import sheet ${architecturalDecisionSheet}"
        Map importResult = ['architecturalDecisions':[]]
        Map reviewParamMap = excelImportService.convertColumnMapOneRow(
                architecturalDecisionSheet,                               //sheet
                ARCHITECTURAL_DECISION_REVIEW_IMPORT_CONFIG,               //config
                ARCHITECTURAL_DECISION_REVIEW_IMPORT_CONFIG.startRow      //startRow
        )
        log.info "architecturalDecisionSheet: found Review param map ${reviewParamMap}"
        Review review = Review.createCriteria().get {
            reviewParamMap.each {key,value ->
                delegate.eq(key, value)
            }
        }
        List architecturalDecisionMapList = excelImportService.convertColumnMapManyRows(
                architecturalDecisionSheet,
                ARCHITECTURAL_DECISION_IMPORT_CONFIG,
                ARCHITECTURAL_DECISION_IMPORT_CONFIG.startRow)
        architecturalDecisionMapList.each { Map architecturalDecisionParams ->
            log.debug("architecturalDecisionParams:${architecturalDecisionParams}")
            def architecturalDecisionImportResult = [:]
            architecturalDecisionImportResult['architecturalDecisionParams'] = architecturalDecisionParams
            architecturalDecisionParams['review'] = review
            ArchitecturalDecision architecturalDecision = ArchitecturalDecision.findByAdNumber(architecturalDecisionParams.adNumber)
            if (architecturalDecision) {
                architecturalDecision.properties = architecturalDecisionParams
                architecturalDecisionImportResult['action'] = 'update'
            } else {
                architecturalDecision = new ArchitecturalDecision(architecturalDecisionParams)
                architecturalDecisionImportResult['action'] = 'create'
            }
            if (!architecturalDecision.save()) {
                println "architectural decision not saved, errors = ${architecturalDecision.errors}"
                architecturalDecisionImportResult['status'] = 'architectural decision not saved'
                architecturalDecisionImportResult['errors'] = architecturalDecision.errors
            } else {
                architecturalDecisionImportResult['status'] = 'ok'
            }
            importResult.architecturalDecisions.add(architecturalDecisionImportResult)
        }
        log.info("architecturalDecisionSheet() importResult=${importResult}")
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

    static REQUIREMENT_REVIEW_IMPORT_CONFIG = [
        sheet:'Anforderungen',
        startRow: 0,
        lastRow: 0,
        columnMap: [
            'C' : 'company',
            'D' : 'projectName'
        ]
    ]


    static REQUIREMENT_IMPORT_CONFIG = [
            sheet:'Anforderungen',
            startRow: 3,
            columnMap: [
                    'A' : 'reqID',
                    'B' : 'qualityGroupCode',
                    'C' : 'qualityMainGroupName',
                    'D' : 'qualityGroupName',
                    'E' : 'name',
                    'F' : 'description',
                    'G' : 'requierer',
                    'H' : 'stakeholder',
                    'I' : 'source',
                    'J' : 'importance',
                    'K' : 'difficulty',
                    'L' : 'reviewPriority',
                    'M' : 'reqNumber',
                    'N' : 'attractivity'
            ]
    ]

    static ARCHITECTURAL_DECISION_REVIEW_IMPORT_CONFIG = [
            sheet:'Architekturentscheidungen',
            startRow: 0,
            lastRow: 0,
            columnMap: [
                    'C' : 'company',
                    'D' : 'projectName'
            ]
    ]


    static ARCHITECTURAL_DECISION_IMPORT_CONFIG = [
            sheet:'Architekturentscheidungen',
            startRow: 3,
            columnMap: [
                    'A' : 'adNumber',
                    'B' : 'shortCode',
                    'C' : 'area',
                    'D' : 'name',
                    'E' : 'goal',
                    'F' : 'decision',
                    'G' : 'reason',
                    'H' : 'alternatives',
                    'I' : 'remarks',
                    'J' : 'priority'
            ]
    ]
}
