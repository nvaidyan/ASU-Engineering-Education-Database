class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:'home', action:"index")
		"500"(controller:'home', action:'error')
                "404"(view:'/404')
	}
}
