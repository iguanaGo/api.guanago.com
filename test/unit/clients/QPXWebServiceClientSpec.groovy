package api.guanago.com.services.clients

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(QPXWebServiceClientService)
class QPXWebServiceClientServiceSpec extends Specification {

    def setup() {

    }

    def cleanup() {
    }

    void "test something"() {
		def qpx = QPXServiceClient()
		
		qpx.request() 
    }
}
