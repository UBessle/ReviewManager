package org.bessle.rm

import org.bessle.rm.pages.architecturalDecision.*
import geb.spock.GebReportingSpec


class ArchitecturalDecisionFunctionalSpec extends GebReportingSpec {

	def "should be able to view list page"() {
		when:
		to ArchitecturalDecisionListPage

		then:
		at ArchitecturalDecisionListPage
	}
	
	def "should be able to create a valid ArchitecturalDecision"() {
		when:
		to ArchitecturalDecisionListPage

		and:
		createButton.click()

		then:
		at ArchitecturalDecisionCreatePage

		when:
		numberField = numberField.find('option').value()
		nameField = "Foo"
		goalField = "Foo"
		decisionField = "Foo"
		reasonField = "Foo"
		alternativesField = "Foo"
		priorityField = priorityField.find('option').value()
			
		and:
		saveButton.click()

		then:
		at ArchitecturalDecisionShowPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "ArchitecturalDecision was successfully created"
	}
	
	def "should be able to sort the ArchitecturalDecision List"() {
		given:
		to ArchitecturalDecisionListPage

		when:
		numberSort.click()
		
		then:
		numberSort.classes().contains("asc")

		when:
		nameSort.click()
		
		then:
		nameSort.classes().contains("asc")

		when:
		goalSort.click()
		
		then:
		goalSort.classes().contains("asc")

		when:
		decisionSort.click()
		
		then:
		decisionSort.classes().contains("asc")
	
	}
	
	def "should be able to filter the ArchitecturalDecision List"() {
		given:
		to ArchitecturalDecisionListPage

		when:
		numberFilter = numberFilter.find('option').value()
		
		then:
		waitFor { rows.size() > 0 }

		when:
		nameFilter = "Foo"
		
		then:
		waitFor { rows.size() > 0 }

		when:
		goalFilter = "Foo"
		
		then:
		waitFor { rows.size() > 0 }

		when:
		decisionFilter = "Foo"
		
		then:
		waitFor { rows.size() > 0 }
	
	}
	
	def "should be able to edit the first ArchitecturalDecision"() {
		when:
		to ArchitecturalDecisionListPage

		and:
		rows.first().editButton.click()

		then:
		at ArchitecturalDecisionEditPage
		
		when:
		numberField = numberField.find('option').value()
		nameField = "Foo!"
		goalField = "Foo!"
		decisionField = "Foo!"
		reasonField = "Foo!"
		alternativesField = "Foo!"
		priorityField = priorityField.find('option').value()
		
		and:
		saveButton.click()
		
		then:
		at ArchitecturalDecisionShowPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "ArchitecturalDecision was successfully updated"
	}
	
	def "should be able to delete the first ArchitecturalDecision"() {
		when:
		to ArchitecturalDecisionListPage

		and:
		rows.first().deleteButton.click()

		then:
		at ArchitecturalDecisionListPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "ArchitecturalDecision was successfully deleted"
      }
	
}