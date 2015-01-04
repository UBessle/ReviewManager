package org.bessle.rm

import org.bessle.rm.pages.requirement.*
import geb.spock.GebReportingSpec


class RequirementFunctionalSpec extends GebReportingSpec {

	def "should be able to view list page"() {
		when:
		to RequirementListPage

		then:
		at RequirementListPage
	}
	
	def "should be able to create a valid Requirement"() {
		when:
		to RequirementListPage

		and:
		createButton.click()

		then:
		at RequirementCreatePage

		when:
		reviewField = reviewField.find('option').value()
		reqNumberField = reqNumberField.find('option').value()
		qualityGroupField = qualityGroupField.find('option').value()
		nameField = "Foo"
		descriptionField = "Foo"
		scenariosField = scenariosField.find('option').value()
		importanceField = importanceField.find('option').value()
		difficultyField = difficultyField.find('option').value()
			
		and:
		saveButton.click()

		then:
		at RequirementShowPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "Requirement was successfully created"
	}
	
	def "should be able to sort the Requirement List"() {
		given:
		to RequirementListPage

		when:
		reviewSort.click()
		
		then:
		reviewSort.classes().contains("asc")

		when:
		reqNumberSort.click()
		
		then:
		reqNumberSort.classes().contains("asc")

		when:
		qualityGroupSort.click()
		
		then:
		qualityGroupSort.classes().contains("asc")

		when:
		nameSort.click()
		
		then:
		nameSort.classes().contains("asc")
	
	}
	
	def "should be able to filter the Requirement List"() {
		given:
		to RequirementListPage

		when:
		reviewFilter = reviewFilter.find('option').value()
		
		then:
		waitFor { rows.size() > 0 }

		when:
		reqNumberFilter = reqNumberFilter.find('option').value()
		
		then:
		waitFor { rows.size() > 0 }

		when:
		qualityGroupFilter = qualityGroupFilter.find('option').value()
		
		then:
		waitFor { rows.size() > 0 }

		when:
		nameFilter = "Foo"
		
		then:
		waitFor { rows.size() > 0 }
	
	}
	
	def "should be able to edit the first Requirement"() {
		when:
		to RequirementListPage

		and:
		rows.first().editButton.click()

		then:
		at RequirementEditPage
		
		when:
		reviewField = reviewField.find('option').value()
		reqNumberField = reqNumberField.find('option').value()
		qualityGroupField = qualityGroupField.find('option').value()
		nameField = "Foo!"
		descriptionField = "Foo!"
		scenariosField = scenariosField.find('option').value()
		importanceField = importanceField.find('option').value()
		difficultyField = difficultyField.find('option').value()
		
		and:
		saveButton.click()
		
		then:
		at RequirementShowPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "Requirement was successfully updated"
	}
	
	def "should be able to delete the first Requirement"() {
		when:
		to RequirementListPage

		and:
		rows.first().deleteButton.click()

		then:
		at RequirementListPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "Requirement was successfully deleted"
      }
	
}