package org.bessle.rm.pages.architecturalDecision

import geb.Module
import geb.Page

class ArchitecturalDecisionCreatePage extends Page {

    static url = "architecturalDecision#/create"

    static at = { $('h2').text() == 'Create ArchitecturalDecision' }

    static content = { 
		numberField {$("input[ng-model='ctrl.architecturalDecision.number']")}
		nameField {$("input[ng-model='ctrl.architecturalDecision.name']")}
		goalField {$("input[ng-model='ctrl.architecturalDecision.goal']")}
		decisionField {$("input[ng-model='ctrl.architecturalDecision.decision']")}
		reasonField {$("input[ng-model='ctrl.architecturalDecision.reason']")}
		alternativesField {$("input[ng-model='ctrl.architecturalDecision.alternatives']")}
		priorityField {$("input[ng-model='ctrl.architecturalDecision.priority']")}
        saveButton { $('button[crud-button="save"]') }
    }

}