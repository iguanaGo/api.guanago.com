package iguanago

class LugarController {
	def CurrencyConverterService
    def index() {
    	render CurrencyConverterService.getConversionRate("USD","ARS")
    }
}
