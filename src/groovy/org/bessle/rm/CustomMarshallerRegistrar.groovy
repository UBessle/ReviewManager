package org.bessle.rm

import grails.converters.JSON

class CustomMarshallerRegistrar {
    
    void registerMarshallers() {

		JSON.registerObjectMarshaller(ArchitecturalDecision) {
			def map = [:]
			map['id'] = it?.id
			map['number'] = it?.number
			map['name'] = it?.name
			map['goal'] = it?.goal
			map['decision'] = it?.decision
			map['reason'] = it?.reason
			map['alternatives'] = it?.alternatives
			map['priority'] = it?.priority
	    	map['toText'] = it?.toString()
			return map 
		}

		JSON.registerObjectMarshaller(Scenario) {
//			System.out.println("marshal Scenario ${it}")
			def map = [:]
			map['id'] = it?.id
			map['requirement'] = it?.requirement // ? new RequirementId('id':it?.requirement.id) : null
			map['scenarioNr'] = it?.scenarioNr
			map['scenario'] = it?.scenario
	    	map['toText'] = it?.toString()
			return map 
		}

		JSON.registerObjectMarshaller(Review) {
//			System.out.println("marshal Review ${it}")
			def map = [:]
			map['id'] = it?.id
			map['company'] = it?.company
			map['projectName'] = it?.projectName
	    	map['toText'] = it?.toString()
			return map 
		}

		JSON.registerObjectMarshaller(Requirement) {
//			System.out.println("marshal Requirement ${it}")
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

		JSON.registerObjectMarshaller(QualityGroup) {
//			System.out.println("marshal QualityGroup ${it}")
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