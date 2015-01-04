package org.bessle.rm

import grails.converters.JSON

class CustomMarshallerRegistrar {
    
    void registerMarshallers() {

		JSON.registerObjectMarshaller(org.bessle.rm.Review) {
			def map = [:]
			map['id'] = it?.id
			map['company'] = it?.company
			map['projectName'] = it?.projectName
			map['requirements'] = it?.requirements
	    	map['toText'] = it?.toString()
			return map 
		}

		JSON.registerObjectMarshaller(org.bessle.rm.Requirement) {
			def map = [:]
			map['id'] = it?.id
			map['review'] = it?.review
			map['reqNumber'] = it?.reqNumber
			map['qualityGroup'] = it?.qualityGroup
			map['name'] = it?.name
			map['description'] = it?.description
			map['scenarios'] = it?.scenarios
			map['importance'] = it?.importance
			map['difficulty'] = it?.difficulty
	    	map['toText'] = it?.toString()
			return map 
		}

		JSON.registerObjectMarshaller(org.bessle.rm.Scenario) {
			def map = [:]
			map['id'] = it?.id
			map['requirement'] = it?.requirement
			map['scenario'] = it?.scenario
	    	map['toText'] = it?.toString()
			return map 
		}

		

		

		

		

		

		

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

		 
	}

}