package iguanago

class Viajero {
 
	String username
	String email
	
	static hasMany = [itinerarios: Itinerario]

    static constraints = {
    	username blank: false, unique: true
		email 	 email: true, blank: false
    }    
} 