package org.bessle.rm.pages.reviewManager

import geb.Module
import geb.Page

class ReviewManagerCreatePage extends Page {

    static url = "reviewManager#/create"

    static at = { $('h2').text() == 'Create ReviewManager' }

    static content = { 
		scenarioField {$("input[ng-model='ctrl.reviewManager.scenario']")}
        saveButton { $('button[crud-button="save"]') }
    }

}