package iguanago

import grails.transaction.Transactional
import grails.plugins.rest.client.RestBuilder

@Transactional
class CurrencyConverterService {

    def getConversionRate(from,to) {
    	def rest = new RestBuilder()
    	def resp = rest.get("http://rate-exchange.herokuapp.com/fetchRate?from=${from}&to=${to}")
		resp.json["Rate"]
    }
}
