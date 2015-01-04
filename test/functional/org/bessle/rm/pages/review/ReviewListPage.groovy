package org.bessle.rm.pages.review

import geb.Module
import geb.Page

class ReviewListPage extends Page {

    static url = "review"

    static at = { $('h2').text() == 'Review List' }

    static content = {
		companyFilter {$("input[ng-model='ctrl.filter.company']")}
		projectNameFilter {$("input[ng-model='ctrl.filter.projectName']")}
		requirementsFilter {$("input[ng-model='ctrl.filter.requirements']")}
	
		companySort { $("table#list th[property='company']") }
		projectNameSort { $("table#list th[property='projectName']") }
		requirementsSort { $("table#list th[property='requirements']") }
    
	    createButton { $("button[crud-button='create']") }
        successMessage { $(".alert-success") }
		
        rows { moduleList ReviewListRow, $("table#list tbody tr") }
    }

}

class ReviewListRow extends Module {

	static content = {
		cell { $("td") }
        editButton {$("button[crud-button='edit']")}
        deleteButton {$("button[crud-button='delete']")}
    }

}