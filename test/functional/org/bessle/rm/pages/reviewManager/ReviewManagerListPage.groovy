package org.bessle.rm.pages.reviewManager

import geb.Module
import geb.Page

class ReviewManagerListPage extends Page {

    static url = "reviewManager"

    static at = { $('h2').text() == 'ReviewManager List' }

    static content = {
		scenarioFilter {$("input[ng-model='ctrl.filter.scenario']")}
	
		scenarioSort { $("table#list th[property='scenario']") }
    
	    createButton { $("button[crud-button='create']") }
        successMessage { $(".alert-success") }
		
        rows { moduleList ReviewManagerListRow, $("table#list tbody tr") }
    }

}

class ReviewManagerListRow extends Module {

	static content = {
		cell { $("td") }
        editButton {$("button[crud-button='edit']")}
        deleteButton {$("button[crud-button='delete']")}
    }

}