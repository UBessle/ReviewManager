package org.bessle.rm.pages.requirement

import geb.Module
import geb.Page

class RequirementEditPage extends Page {

    static url = "requirement#/create"

    static at = { $('h2').text() == 'Edit Requirement' }

    static content = {
		reviewField {$("select[ng-model='ctrl.requirement.review']")}
		reqNumberField {$("input[ng-model='ctrl.requirement.reqNumber']")}
		qualityGroupField {$("select[ng-model='ctrl.requirement.qualityGroup']")}
		nameField {$("input[ng-model='ctrl.requirement.name']")}
		descriptionField {$("input[ng-model='ctrl.requirement.description']")}
		scenariosField {$("input[ng-model='ctrl.requirement.scenarios']")}
		importanceField {$("input[ng-model='ctrl.requirement.importance']")}
		difficultyField {$("input[ng-model='ctrl.requirement.difficulty']")}
        saveButton { $('button[crud-button="save"]') }
    }

}