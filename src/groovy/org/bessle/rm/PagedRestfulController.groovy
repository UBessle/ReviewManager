package org.bessle.rm

import grails.rest.RestfulController
import grails.gorm.PagedResultList
import org.codehaus.groovy.grails.commons.GrailsClassUtils

class PagedRestfulController<T> extends RestfulController<T> {
	static allowedMethods = [save: "POST", update: "PUT", patch: "PATCH", delete: "DELETE"]

	static responseFormats = ['json']

	PagedRestfulController(Class<T> resource) {
		super(resource)
	}

	def index(Integer max) {
		log.info("${this.resourceName}.index() : params=${params}")
		params.page = params.int('page') ?: 1
		params.max = grailsApplication.config.angular.pageSize ?: 25
		params.offset = ((params.page - 1) * params.max)
		
		def results = loadPagedResults(params)

		response.setHeader('Content-Range', getContentRange((int)results.totalCount, params.offset, params.max))

		log.debug("index() method results: ${results}")
		respond results, formats: ['json', 'html']
	}

	protected PagedResultList loadPagedResults(params) {
        List pagedResultList = resource.createCriteria().list(max: params.max, offset: params.offset) {
			
			params.filter?.each { String name, String value ->
               setDefaultCriteria(delegate, name, value)
            }
         
            if (params.sort) {
              	order(params.sort, params.order == "asc" ? "asc" : "desc")
            }
        }
		if (params.sort) {
			return pagedResultList
		} else {
			return pagedResultList.sort { entry -> entry.toString() }
		}
    }

    protected void setDefaultCriteria(criteria, String propertyName, String propertyValue) {
		String declaredPropertyName = propertyName - 'Id'
		Class propertyClass = GrailsClassUtils.getPropertyType(resource, declaredPropertyName)
		log.info("setDefaultCriteria(propertyName:${propertyName}, propertyValue:${propertyValue}, propertyClass:${propertyClass}")
		if (!propertyClass) {
			return
		}	
		else if (propertyName.endsWith('Id')) {
			criteria."${declaredPropertyName}" {
				eq('id', propertyValue.toLong())
			}
		}
		else {
			switch (propertyClass) {
				case String:
					criteria.ilike(propertyName, "%${propertyValue}%")
					break

				case [Float, Integer, BigDecimal]:
					if (propertyValue.isNumber()) {
						criteria.eq(propertyName, propertyValue.asType(propertyClass))
					}
					else {
						criteria.eq(propertyName, null)
					}
					break

				case [float, double, int, long]:
					if (propertyValue.isNumber()) {
						criteria.eq(propertyName, propertyValue.asType(propertyClass))
					}
					break

				case Date:
					def dateFormats = grailsApplication.config.grails.databinding?.dateFormats
					def dateProperty = params.date("filter.${propertyName}", dateFormats)
        
					if (dateProperty) {
						criteria.ge(propertyName, dateProperty)
					}
					else {
						criteria.eq(propertyName, null)
					}
					break
                
				default:
					criteria.eq(propertyName, propertyValue.asType(propertyClass))					
			}			
		}
    }

	protected String getContentRange(int totalCount, int offset, int max) {
		int startRange = (totalCount == 0) ? 0 : offset + 1;
		int endRange = Math.min(offset + max, totalCount)

		"${startRange}-${endRange}/${totalCount}"
	}
}
