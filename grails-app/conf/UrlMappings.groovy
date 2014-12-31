class UrlMappings {

	static mappings = {

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
