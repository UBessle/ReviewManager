package org.bessle.rm

class Requirement {

    Review review
    int reqNumber
    QualityGroup qualityGroup
    String name
    String description
    List scenarios
    int importance
    int difficulty

    static belongsTo = [review:Review]

    static hasMany = [scenarios:Scenario]

    static constraints = {
        review nullable: false
        reqNumber unique: true
        qualityGroup nullable: false
        name nullable: false, blank: false, maxSize: 500
        description nullable: true, blank: true, maxSize: 2000
        scenarios nullable: true
        importance min: 0, max: 10
        difficulty min: 0, max: 10
    }

    double getReviewPriority() {
        return (2*importance+difficulty)/3
    }

    double getActionAttractiveness() {
        return (2*importance + 11 - difficulty)/3
    }

    String toString() {
        return "${qualityGroup?.code}-${reqNumber}:${name}"
    }
}
