package org.bessle.rm

class Review {
    String company
    String projectName
    Set<Requirement> requirements

    static hasMany = [requirements:Requirement]

    static constraints = {
        company blank:false, nullable: false
        projectName blank: false, nullable: false
        requirements nullable: true
    }

    String toString() {
        return "${company}-${projectName}"
    }
}
