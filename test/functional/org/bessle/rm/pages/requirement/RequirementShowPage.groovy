package org.bessle.rm.pages.requirement

import geb.Page

class RequirementShowPage extends Page {

    static at = { $('h2').text().startsWith 'Show Requirement' }

    static content = {
        successMessage { $(".alert-success") }
    }

}