import grails.converters.JSON
import iguanago.*
import iguanago.auth.*

class BootStrap {
	def grailsApplication

    def init = { servletContext ->

        Usuario user = new Usuario(username: "test", password: "test123")
        user.save()
 
        Rol roleUser = new Rol(authority: "ROLE_USER")
        roleUser.save()
 
        new UsuarioRol(usuario: user, rol: roleUser).save()

		if (!Lugar.count()) {
	        // add Lugares
	        def filePath = "resources/places.json"
	        def text = grailsApplication.getParentContext().getResource("classpath:$filePath").getInputStream().getText()
	        def places = JSON.parse(text)

	        for (jsonPlace in places["features"] ) {
	        	def coordinates = new Coordenadas(
	        			latitude: jsonPlace ["properties"] ["latitude"],
	        			longitude: jsonPlace ["properties"] ["longitude"]
	        		)
	            def place = new Lugar(
	                name: jsonPlace ["properties"] ["name"],
	                coordinates: coordinates,
	                code: "test"
	            ).save(failOnError: true);
	        }
	    }

    }
    def destroy = {
    }
}
