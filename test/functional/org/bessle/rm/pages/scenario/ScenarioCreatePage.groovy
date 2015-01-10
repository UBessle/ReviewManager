package org.bessle.rm.pages.scenario

import geb.Module
import geb.Page

class ScenarioCreatePage extends Page {

    static url = "scenario#/create"

    static at = { $('h2').text() == 'Create Scenario' }

    static content = { 
		requirementField {$("select[ng-model='ctrl.scenario.requirement']")}
		scenarioNrField {$("input[ng-model='ctrl.scenario.scenarioNr']")}
		scenarioField {$("input[ng-model='ctrl.scenario.scenario']")}
        saveButton { $('button[crud-button="save"]') }
    }

}