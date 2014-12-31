package org.bessle.rm.pages.qualityGroup

import geb.Module
import geb.Page

class QualityGroupListPage extends Page {

    static url = "qualityGroup"

    static at = { $('h2').text() == 'QualityGroup List' }

    static content = {
		codeFilter {$("input[ng-model='ctrl.filter.code']")}
		characteristicFilter {$("input[ng-model='ctrl.filter.characteristic']")}
		subCharacteristicFilter {$("input[ng-model='ctrl.filter.subCharacteristic']")}
		definitionFilter {$("input[ng-model='ctrl.filter.definition']")}
	
		codeSort { $("table#list th[property='code']") }
		characteristicSort { $("table#list th[property='characteristic']") }
		subCharacteristicSort { $("table#list th[property='subCharacteristic']") }
		definitionSort { $("table#list th[property='definition']") }
    
	    createButton { $("button[crud-button='create']") }
        successMessage { $(".alert-success") }
		
        rows { moduleList QualityGroupListRow, $("table#list tbody tr") }
    }

}

class QualityGroupListRow extends Module {

	static content = {
		cell { $("td") }
        editButton {$("button[crud-button='edit']")}
        deleteButton {$("button[crud-button='delete']")}
    }

}