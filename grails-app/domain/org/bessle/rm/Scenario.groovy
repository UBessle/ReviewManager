package org.bessle.rm

class Scenario {
    Requirement requirement
    String scenario

    static belongsTo = [requirement:Requirement]


    static constraints = {
        requirement nullable: false
        scenario nullable: false, blank: false
    }
}
