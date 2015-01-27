package org.bessle.rm

class ArchitecturalDecision {
    Review review
    int adNumber
    String area
    String name
    String goal
    String decision
    String reason
    String alternatives
    String remarks
    Integer priority

    static belongsTo = [review:Review]

    static constraints = {
        review          nullable: false
        adNumber          unique: true
        area            blank: true, nullable: true, maxSize: 50
        name            blank: false, nullable: false, maxSize: 100
        goal            blank: true, nullable: true, maxSize: 1000
        decision        blank: true, nullable: true, maxSize: 1000
        reason          blank: true, nullable: true, maxSize: 1000
        alternatives    blank: true, nullable: true, maxSize: 1000
        remarks         blank: true, nullable: true, maxSize: 1000
        priority        min: 0, max: 10, nullable:true
    }

    String toString() {
        return "${adNumber}-${name}"
    }
}
