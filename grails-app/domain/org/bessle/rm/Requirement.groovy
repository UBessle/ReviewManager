package org.bessle.rm

import java.math.RoundingMode

class Requirement {

    Review review
    int reqNumber
    QualityGroup qualityGroup
    String name
    String description
    List scenarios
    int importance
    int difficulty
    double reviewPriority
    double actionAttractiveness

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

    void calculateReviewPriority() {
        reviewPriority = ((2*importance + difficulty)/3).setScale(1,RoundingMode.HALF_UP)
        log.debug("calculateReviewPriority(): ReviewPriority(importance=${importance}, difficulty=${difficulty})=${reviewPriority}")
    }

    void calculateActionAttractiveness() {
        actionAttractiveness = ((2*importance + 11 - difficulty)/3).setScale(1,RoundingMode.HALF_UP)
        log.debug("calculateActionAttractiveness(): actionAttractiveness(importance=${importance}, difficulty=${difficulty})=${actionAttractiveness}")
    }

    void calculateDerivedFields() {
        calculateReviewPriority()
        calculateActionAttractiveness()
    }

    void setImportance(int newImportance) {
        log.debug("setImportance(newImportance=${newImportance})")
        importance = newImportance
        calculateDerivedFields()
    }

    void setDifficulty(int newDifficulty) {
        log.debug("setDifficulty(newDifficulty=${newDifficulty})")
        difficulty = newDifficulty
        calculateDerivedFields()
    }

    void setActionAttractiveness(double newActionAttractiveness) {
        log.debug("try to set ActionAttractiveness to ${newActionAttractiveness}")
        return
    }

    void setReviewPriority(double newReviewPriority) {
        log.debug("try to set ReviewPriority to ${newReviewPriority}")
        return
    }
    String toString() {
        return "${qualityGroup?.code}-${reqNumber}:${name}"
    }
}
