package org.bessle.rm.pages.reviewManager

import geb.Page

class ReviewManagerShowPage extends Page {

    static at = { $('h2').text().startsWith 'Show ReviewManager' }

    static content = {
        successMessage { $(".alert-success") }
    }

}