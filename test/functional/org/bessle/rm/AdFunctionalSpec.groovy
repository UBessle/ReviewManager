package org.bessle.rm

import org.bessle.rm.pages.ad.*
import geb.spock.GebReportingSpec


class AdFunctionalSpec extends GebReportingSpec {

	def "should be able to view list page"() {
		when:
		to AdListPage

		then:
		at AdListPage
	}
	
	def "should be able to create a valid Ad"() {
		when:
		to AdListPage

		and:
		createButton.click()

		then:
		at AdCreatePage

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
		at AdShowPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "Ad was successfully created"
	}
	
	def "should be able to sort the Ad List"() {
		given:
		to AdListPage

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
	
	def "should be able to filter the Ad List"() {
		given:
		to AdListPage

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
	
	def "should be able to edit the first Ad"() {
		when:
		to AdListPage

		and:
		rows.first().editButton.click()

		then:
		at AdEditPage
		
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
		at AdShowPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "Ad was successfully updated"
	}
	
	def "should be able to delete the first Ad"() {
		when:
		to AdListPage

		and:
		rows.first().deleteButton.click()

		then:
		at AdListPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "Ad was successfully deleted"
      }
	
}