package iguana

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Lugar)
class LugarSpec extends Specification {

    def setup() {
		
    }

    def cleanup() {
    }

    void "toString is sigla"() {
		
		when:
		def sigla = "BUE"
		Lugar lugar = new Lugar(sigla: sigla, nombre:"BUENOS AIRES")
		String aux = lugar.toString();
		
		then:
		aux.equals(sigla)
		
    }
	
	void "toJson not empty"() {
		
		when:
		Lugar lugar = new Lugar(sigla: "BUE", nombre:"BUENOS AIRES")
		String aux = lugar.toJson();
		
		then:		
		! aux.dump().empty
		
	}
}
