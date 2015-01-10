package org.bessle.rm

class Scenario {
    Requirement requirement
    int scenarioNr
    String scenario

    static belongsTo = [requirement:Requirement]


    static constraints = {
        requirement nullable: false
        scenarioNr min: 1
        scenario nullable: false, blank: false
    }
}
