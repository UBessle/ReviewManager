package org.bessle.rm.pages.architecturalDecision

import geb.Module
import geb.Page

class ArchitecturalDecisionListPage extends Page {

    static url = "architecturalDecision"

    static at = { $('h2').text() == 'ArchitecturalDecision List' }

    static content = {
		numberFilter {$("input[ng-model='ctrl.filter.number']")}
		nameFilter {$("input[ng-model='ctrl.filter.name']")}
		goalFilter {$("input[ng-model='ctrl.filter.goal']")}
		decisionFilter {$("input[ng-model='ctrl.filter.decision']")}
		reasonFilter {$("input[ng-model='ctrl.filter.reason']")}
		alternativesFilter {$("input[ng-model='ctrl.filter.alternatives']")}
		priorityFilter {$("input[ng-model='ctrl.filter.priority']")}
	
		numberSort { $("table#list th[property='number']") }
		nameSort { $("table#list th[property='name']") }
		goalSort { $("table#list th[property='goal']") }
		decisionSort { $("table#list th[property='decision']") }
    
	    createButton { $("button[crud-button='create']") }
        successMessage { $(".alert-success") }
		
        rows { moduleList ArchitecturalDecisionListRow, $("table#list tbody tr") }
    }

}

class ArchitecturalDecisionListRow extends Module {

	static content = {
		cell { $("td") }
        editButton {$("button[crud-button='edit']")}
        deleteButton {$("button[crud-button='delete']")}
    }

}