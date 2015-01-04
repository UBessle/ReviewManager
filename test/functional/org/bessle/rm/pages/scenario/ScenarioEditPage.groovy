package org.bessle.rm.pages.scenario

import geb.Module
import geb.Page

class ScenarioEditPage extends Page {

    static url = "scenario#/create"

    static at = { $('h2').text() == 'Edit Scenario' }

    static content = {
		requirementField {$("select[ng-model='ctrl.scenario.requirement']")}
		scenarioField {$("input[ng-model='ctrl.scenario.scenario']")}
        saveButton { $('button[crud-button="save"]') }
    }

}