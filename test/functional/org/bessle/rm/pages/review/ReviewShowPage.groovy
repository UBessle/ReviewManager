package org.bessle.rm.pages.review

import geb.Page

class ReviewShowPage extends Page {

    static at = { $('h2').text().startsWith 'Show Review' }

    static content = {
        successMessage { $(".alert-success") }
    }

}