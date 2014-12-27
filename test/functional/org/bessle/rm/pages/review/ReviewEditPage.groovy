package org.bessle.rm.pages.review

import geb.Module
import geb.Page

class ReviewEditPage extends Page {

    static url = "review#/create"

    static at = { $('h2').text() == 'Edit Review' }

    static content = {
		companyField {$("input[ng-model='ctrl.review.company']")}
		projectNameField {$("input[ng-model='ctrl.review.projectName']")}
        saveButton { $('button[crud-button="save"]') }
    }

}