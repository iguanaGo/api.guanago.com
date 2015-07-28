package iguanago

class Vuelo {

	Precio price
	Lugar from
	Lugar destination
	Date departureDate
	Date arrivalDate

    static constraints = {
    	price nullable: false
    	from nullable: false
    	destination nullable: false
    	departureDate nullable: false
    	arrivalDate nullable: false
    }
}