package org.bessle.rm

import org.bessle.rm.pages.reviewManager.*
import geb.spock.GebReportingSpec


class ReviewManagerFunctionalSpec extends GebReportingSpec {

	def "should be able to view list page"() {
		when:
		to ReviewManagerListPage

		then:
		at ReviewManagerListPage
	}
	
	def "should be able to create a valid ReviewManager"() {
		when:
		to ReviewManagerListPage

		and:
		createButton.click()

		then:
		at ReviewManagerCreatePage

		when:
		scenarioField = "Foo"
			
		and:
		saveButton.click()

		then:
		at ReviewManagerShowPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "ReviewManager was successfully created"
	}
	
	def "should be able to sort the ReviewManager List"() {
		given:
		to ReviewManagerListPage

		when:
		scenarioSort.click()
		
		then:
		scenarioSort.classes().contains("asc")
	
	}
	
	def "should be able to filter the ReviewManager List"() {
		given:
		to ReviewManagerListPage

		when:
		scenarioFilter = "Foo"
		
		then:
		waitFor { rows.size() > 0 }
	
	}
	
	def "should be able to edit the first ReviewManager"() {
		when:
		to ReviewManagerListPage

		and:
		rows.first().editButton.click()

		then:
		at ReviewManagerEditPage
		
		when:
		scenarioField = "Foo!"
		
		and:
		saveButton.click()
		
		then:
		at ReviewManagerShowPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "ReviewManager was successfully updated"
	}
	
	def "should be able to delete the first ReviewManager"() {
		when:
		to ReviewManagerListPage

		and:
		rows.first().deleteButton.click()

		then:
		at ReviewManagerListPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "ReviewManager was successfully deleted"
      }
	
}