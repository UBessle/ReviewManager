package org.bessle.rm.pages.ad

import geb.Module
import geb.Page

class AdListPage extends Page {

    static url = "ad"

    static at = { $('h2').text() == 'Ad List' }

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
		
        rows { moduleList AdListRow, $("table#list tbody tr") }
    }

}

class AdListRow extends Module {

	static content = {
		cell { $("td") }
        editButton {$("button[crud-button='edit']")}
        deleteButton {$("button[crud-button='delete']")}
    }

}