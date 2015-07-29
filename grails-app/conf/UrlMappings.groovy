class UrlMappings {

	static mappings = {
        "/api/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/signup"(controller: "signup", action: "signUp")

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
