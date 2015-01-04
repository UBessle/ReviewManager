package org.bessle.rm.pages.review

import geb.Module
import geb.Page

class ReviewCreatePage extends Page {

    static url = "review#/create"

    static at = { $('h2').text() == 'Create Review' }

    static content = { 
		companyField {$("input[ng-model='ctrl.review.company']")}
		projectNameField {$("input[ng-model='ctrl.review.projectName']")}
		requirementsField {$("input[ng-model='ctrl.review.requirements']")}
        saveButton { $('button[crud-button="save"]') }
    }

}