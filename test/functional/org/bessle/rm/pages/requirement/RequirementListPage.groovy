package org.bessle.rm.pages.requirement

import geb.Module
import geb.Page

class RequirementListPage extends Page {

    static url = "requirement"

    static at = { $('h2').text() == 'Requirement List' }

    static content = {
		reviewFilter {$("select[ng-model='ctrl.filter.reviewId']")}
		reqNumberFilter {$("input[ng-model='ctrl.filter.reqNumber']")}
		qualityGroupFilter {$("select[ng-model='ctrl.filter.qualityGroupId']")}
		nameFilter {$("input[ng-model='ctrl.filter.name']")}
		descriptionFilter {$("input[ng-model='ctrl.filter.description']")}
		scenariosFilter {$("input[ng-model='ctrl.filter.scenarios']")}
		importanceFilter {$("input[ng-model='ctrl.filter.importance']")}
		difficultyFilter {$("input[ng-model='ctrl.filter.difficulty']")}
	
		reviewSort { $("table#list th[property='review']") }
		reqNumberSort { $("table#list th[property='reqNumber']") }
		qualityGroupSort { $("table#list th[property='qualityGroup']") }
		nameSort { $("table#list th[property='name']") }
    
	    createButton { $("button[crud-button='create']") }
        successMessage { $(".alert-success") }
		
        rows { moduleList RequirementListRow, $("table#list tbody tr") }
    }

}

class RequirementListRow extends Module {

	static content = {
		cell { $("td") }
        editButton {$("button[crud-button='edit']")}
        deleteButton {$("button[crud-button='delete']")}
    }

}