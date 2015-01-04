package org.bessle.rm.pages.scenario

import geb.Module
import geb.Page

class ScenarioListPage extends Page {

    static url = "scenario"

    static at = { $('h2').text() == 'Scenario List' }

    static content = {
		requirementFilter {$("select[ng-model='ctrl.filter.requirementId']")}
		scenarioFilter {$("input[ng-model='ctrl.filter.scenario']")}
	
		requirementSort { $("table#list th[property='requirement']") }
		scenarioSort { $("table#list th[property='scenario']") }
    
	    createButton { $("button[crud-button='create']") }
        successMessage { $(".alert-success") }
		
        rows { moduleList ScenarioListRow, $("table#list tbody tr") }
    }

}

class ScenarioListRow extends Module {

	static content = {
		cell { $("td") }
        editButton {$("button[crud-button='edit']")}
        deleteButton {$("button[crud-button='delete']")}
    }

}