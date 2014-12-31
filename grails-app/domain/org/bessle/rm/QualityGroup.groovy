package org.bessle.rm

class QualityGroup {
    String code
    String characteristic
    String subCharacteristic
    String definition

    static constraints = {
        code maxSize: 2, minSize: 1, blank: false, nullable: false
        characteristic maxSize: 20
        subCharacteristic maxSize: 20
        definition maxSize: 1000
    }
}
