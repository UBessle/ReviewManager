package org.bessle.rm

class ArchitecturalDecision {
    Review review
    int number
    String name
    String goal
    String decision
    String reason
    String alternatives
    int priority

    static belongsTo = [review:Review]

    static constraints = {
        review nullable: false
        number unique: true
        name blank: false, nullable: false, maxSize: 100
        goal maxSize: 1000
        decision maxSize: 1000
        reason maxSize: 1000
        alternatives maxSize: 1000
        priority min: 0, max: 10
    }

    String toString() {
        return "${number}-${name}"
    }
}
