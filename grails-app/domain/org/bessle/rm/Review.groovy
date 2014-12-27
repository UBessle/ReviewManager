package org.bessle.rm

class Review {
    String company
    String projectName

    static constraints = {
        company blank:false, nullable: false
        projectName blank: false, nullable: false
    }
}
