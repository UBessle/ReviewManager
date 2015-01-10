package org.bessle.rm.pages.architecturalDecision

import geb.Page

class ArchitecturalDecisionShowPage extends Page {

    static at = { $('h2').text().startsWith 'Show ArchitecturalDecision' }

    static content = {
        successMessage { $(".alert-success") }
    }

}