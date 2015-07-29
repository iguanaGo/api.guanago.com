package iguanago

class ProposalsController {
	def JWTDecoderService

	def whoami(){
		render JWTDecoderService.getUsername(request.getHeader("Authorization"))
	}

    def index() {
    	
    }
}
