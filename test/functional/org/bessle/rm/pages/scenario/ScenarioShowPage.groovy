package org.bessle.rm.pages.scenario

import geb.Page

class ScenarioShowPage extends Page {

    static at = { $('h2').text().startsWith 'Show Scenario' }

    static content = {
        successMessage { $(".alert-success") }
    }

}