package org.bessle.rm

import org.bessle.rm.pages.review.*
import geb.spock.GebReportingSpec


class ReviewFunctionalSpec extends GebReportingSpec {

	def "should be able to view list page"() {
		when:
		to ReviewListPage

		then:
		at ReviewListPage
	}
	
	def "should be able to create a valid Review"() {
		when:
		to ReviewListPage

		and:
		createButton.click()

		then:
		at ReviewCreatePage

		when:
		companyField = "Foo"
		projectNameField = "Foo"
			
		and:
		saveButton.click()

		then:
		at ReviewShowPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "Review was successfully created"
	}
	
	def "should be able to sort the Review List"() {
		given:
		to ReviewListPage

		when:
		companySort.click()
		
		then:
		companySort.classes().contains("asc")

		when:
		projectNameSort.click()
		
		then:
		projectNameSort.classes().contains("asc")
	
	}
	
	def "should be able to filter the Review List"() {
		given:
		to ReviewListPage

		when:
		companyFilter = "Foo"
		
		then:
		waitFor { rows.size() > 0 }

		when:
		projectNameFilter = "Foo"
		
		then:
		waitFor { rows.size() > 0 }
	
	}
	
	def "should be able to edit the first Review"() {
		when:
		to ReviewListPage

		and:
		rows.first().editButton.click()

		then:
		at ReviewEditPage
		
		when:
		companyField = "Foo!"
		projectNameField = "Foo!"
		
		and:
		saveButton.click()
		
		then:
		at ReviewShowPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "Review was successfully updated"
	}
	
	def "should be able to delete the first Review"() {
		when:
		to ReviewListPage

		and:
		rows.first().deleteButton.click()

		then:
		at ReviewListPage

		and:
		successMessage.displayed

		and:
		successMessage.text().contains "Review was successfully deleted"
      }
	
}