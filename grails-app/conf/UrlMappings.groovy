class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

		"/admin/post/$action?/$id?"{
			controller="adminPost"
		}

        "/"(controller: "posts", action: "index")
        "500"(view:'/error')
	}
}
