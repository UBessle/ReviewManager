package org.bessle.rm.pages.qualityGroup

import geb.Module
import geb.Page

class QualityGroupEditPage extends Page {

    static url = "qualityGroup#/create"

    static at = { $('h2').text() == 'Edit QualityGroup' }

    static content = {
		codeField {$("input[ng-model='ctrl.qualityGroup.code']")}
		characteristicField {$("input[ng-model='ctrl.qualityGroup.characteristic']")}
		subCharacteristicField {$("input[ng-model='ctrl.qualityGroup.subCharacteristic']")}
		definitionField {$("input[ng-model='ctrl.qualityGroup.definition']")}
        saveButton { $('button[crud-button="save"]') }
    }

}