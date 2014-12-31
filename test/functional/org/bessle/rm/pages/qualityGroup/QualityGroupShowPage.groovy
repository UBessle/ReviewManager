package org.bessle.rm.pages.qualityGroup

import geb.Page

class QualityGroupShowPage extends Page {

    static at = { $('h2').text().startsWith 'Show QualityGroup' }

    static content = {
        successMessage { $(".alert-success") }
    }

}