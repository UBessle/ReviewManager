package org.bessle.rm

import org.bessle.rm.pages.qualityGroup.*
import geb.spock.GebReportingSpec


class QualityGroupFunctionalSpec extends GebReportingSpec {

	def "should be able to view list page"() {
		when:
		to QualityGroupListPage

		then:
		at QualityGroupListPage
	}
	
	def "should be able to create a valid QualityGroup"() {
		when:
		to QualityGroupListPage

		and:
		createButton.click()

		then:
		at QualityGroupCreatePage

		when:
		codeField = "Foo"
		characteristicField = "Foo"
		subCharacteristicField = "Foo"
		definitionField = "Foo"
			
		and:
		saveButton.click()

		then:
		at QualityGroupShowPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "QualityGroup was successfully created"
	}
	
	def "should be able to sort the QualityGroup List"() {
		given:
		to QualityGroupListPage

		when:
		codeSort.click()
		
		then:
		codeSort.classes().contains("asc")

		when:
		characteristicSort.click()
		
		then:
		characteristicSort.classes().contains("asc")

		when:
		subCharacteristicSort.click()
		
		then:
		subCharacteristicSort.classes().contains("asc")

		when:
		definitionSort.click()
		
		then:
		definitionSort.classes().contains("asc")
	
	}
	
	def "should be able to filter the QualityGroup List"() {
		given:
		to QualityGroupListPage

		when:
		codeFilter = "Foo"
		
		then:
		waitFor { rows.size() > 0 }

		when:
		characteristicFilter = "Foo"
		
		then:
		waitFor { rows.size() > 0 }

		when:
		subCharacteristicFilter = "Foo"
		
		then:
		waitFor { rows.size() > 0 }

		when:
		definitionFilter = "Foo"
		
		then:
		waitFor { rows.size() > 0 }
	
	}
	
	def "should be able to edit the first QualityGroup"() {
		when:
		to QualityGroupListPage

		and:
		rows.first().editButton.click()

		then:
		at QualityGroupEditPage
		
		when:
		codeField = "Foo!"
		characteristicField = "Foo!"
		subCharacteristicField = "Foo!"
		definitionField = "Foo!"
		
		and:
		saveButton.click()
		
		then:
		at QualityGroupShowPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "QualityGroup was successfully updated"
	}
	
	def "should be able to delete the first QualityGroup"() {
		when:
		to QualityGroupListPage

		and:
		rows.first().deleteButton.click()

		then:
		at QualityGroupListPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "QualityGroup was successfully deleted"
      }
	
}