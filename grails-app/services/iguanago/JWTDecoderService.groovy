package iguanago

import grails.transaction.Transactional
import grails.converters.JSON

@Transactional
class JWTDecoderService {

    def getUsername(jwt) {
    	List split = jwt.tokenize "."
    	String claims = new String(split[1].decodeBase64())
    	JSON.parse(claims)["sub"]
    }
}
