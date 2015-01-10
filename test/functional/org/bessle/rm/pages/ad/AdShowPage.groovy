package org.bessle.rm.pages.ad

import geb.Page

class AdShowPage extends Page {

    static at = { $('h2').text().startsWith 'Show Ad' }

    static content = {
        successMessage { $(".alert-success") }
    }

}