package org.bessle.rm

import grails.converters.JSON

class CustomMarshallerRegistrar {
    
    void registerMarshallers() {

		JSON.registerObjectMarshaller(org.bessle.rm.QualityGroup) {
			def map = [:]
			map['id'] = it?.id
			map['code'] = it?.code
			map['characteristic'] = it?.characteristic
			map['subCharacteristic'] = it?.subCharacteristic
			map['definition'] = it?.definition
	    	map['toText'] = it?.toString()
			return map 
		}

		JSON.registerObjectMarshaller(org.bessle.rm.Review) {
			def map = [:]
			map['id'] = it?.id
			map['company'] = it?.company
			map['projectName'] = it?.projectName
	    	map['toText'] = it?.toString()
			return map 
		} 
	}

}