class UrlMappings {

	static mappings = {

        																		'/review'(view: 'review')
		'/api/review'(resources: 'review')
'/requirement'(view: 'requirement')
		'/api/requirement'(resources: 'requirement')
'/scenario'(view: 'scenario')
		'/api/scenario'(resources: 'scenario')
'/review'(view: 'review')
		'/api/review'(resources: 'review')
'/requirement'(view: 'requirement')
		'/api/requirement'(resources: 'requirement')
'/scenario'(view: 'scenario')
		'/api/scenario'(resources: 'scenario')
'/qualityGroup'(view: 'qualityGroup')
		'/api/qualityGroup'(resources: 'qualityGroup')
'/review'(view: 'review')
		'/api/review'(resources: 'review')
"/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
