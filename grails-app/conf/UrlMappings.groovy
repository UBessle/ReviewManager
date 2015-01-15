class UrlMappings {

	static mappings = {

		'/architecturalDecision'(view: 'architecturalDecision')
		'/api/architecturalDecision'(resources: 'architecturalDecision')
		'/scenario'(view: 'scenario')
		'/api/scenario'(resources: 'scenario')
//		'/api/review/upload'(controller: 'upload', action:'uploadReview')
		'/review'(view: 'review')
		'/api/review'(resources: 'review') {
			"/upload"(resource: 'upload')
		}
		'/requirement'(view: 'requirement')
		'/api/requirement'(resources: 'requirement')
		'/qualityGroup'(view: 'qualityGroup')
		'/api/qualityGroup'(resources: 'qualityGroup')

		"/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
