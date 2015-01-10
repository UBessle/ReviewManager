package org.bessle.rm

import org.bessle.rm.pages.scenario.*
import geb.spock.GebReportingSpec


class ScenarioFunctionalSpec extends GebReportingSpec {

	def "should be able to view list page"() {
		when:
		to ScenarioListPage

		then:
		at ScenarioListPage
	}
	
	def "should be able to create a valid Scenario"() {
		when:
		to ScenarioListPage

		and:
		createButton.click()

		then:
		at ScenarioCreatePage

		when:
		requirementField = requirementField.find('option').value()
		scenarioNrField = scenarioNrField.find('option').value()
		scenarioField = "Foo"
			
		and:
		saveButton.click()

		then:
		at ScenarioShowPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "Scenario was successfully created"
	}
	
	def "should be able to sort the Scenario List"() {
		given:
		to ScenarioListPage

		when:
		requirementSort.click()
		
		then:
		requirementSort.classes().contains("asc")

		when:
		scenarioNrSort.click()
		
		then:
		scenarioNrSort.classes().contains("asc")

		when:
		scenarioSort.click()
		
		then:
		scenarioSort.classes().contains("asc")
	
	}
	
	def "should be able to filter the Scenario List"() {
		given:
		to ScenarioListPage

		when:
		requirementFilter = requirementFilter.find('option').value()
		
		then:
		waitFor { rows.size() > 0 }

		when:
		scenarioNrFilter = scenarioNrFilter.find('option').value()
		
		then:
		waitFor { rows.size() > 0 }

		when:
		scenarioFilter = "Foo"
		
		then:
		waitFor { rows.size() > 0 }
	
	}
	
	def "should be able to edit the first Scenario"() {
		when:
		to ScenarioListPage

		and:
		rows.first().editButton.click()

		then:
		at ScenarioEditPage
		
		when:
		requirementField = requirementField.find('option').value()
		scenarioNrField = scenarioNrField.find('option').value()
		scenarioField = "Foo!"
		
		and:
		saveButton.click()
		
		then:
		at ScenarioShowPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "Scenario was successfully updated"
	}
	
	def "should be able to delete the first Scenario"() {
		when:
		to ScenarioListPage

		and:
		rows.first().deleteButton.click()

		then:
		at ScenarioListPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "Scenario was successfully deleted"
      }
	
}