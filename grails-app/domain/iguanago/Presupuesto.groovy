package iguanago
import grails.converters.JSON

class Presupuesto {
	def CurrencyConverterService

	String currency
	float amount

	static belongsTo = [itinerario: Itinerario]
    static constraints = {
    }

    float convertToCurrency(Precio price){
		float conversionRate = 1
		if(currency != price.currency){
			conversionRate = CurrencyConverterService.getConversionRate(price.currency,currency)
		}
		return price.totalValue() * conversionRate
    }

	float usedAmount() {
		float total = 0
		itinerario.vuelos.each { 
			total += convertToCurrency(it.price)
		}
		return total
	}


    boolean hasEnoughFor( flight ){
    	return amount >= usedAmount() + convertToCurrency(flight.price)
    }
}