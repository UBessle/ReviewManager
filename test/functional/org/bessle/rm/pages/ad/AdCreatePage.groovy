package org.bessle.rm.pages.ad

import geb.Module
import geb.Page

class AdCreatePage extends Page {

    static url = "ad#/create"

    static at = { $('h2').text() == 'Create Ad' }

    static content = { 
		numberField {$("input[ng-model='ctrl.ad.number']")}
		nameField {$("input[ng-model='ctrl.ad.name']")}
		goalField {$("input[ng-model='ctrl.ad.goal']")}
		decisionField {$("input[ng-model='ctrl.ad.decision']")}
		reasonField {$("input[ng-model='ctrl.ad.reason']")}
		alternativesField {$("input[ng-model='ctrl.ad.alternatives']")}
		priorityField {$("input[ng-model='ctrl.ad.priority']")}
        saveButton { $('button[crud-button="save"]') }
    }

}