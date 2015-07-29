package iguanago

class Importe {

	String details
	float value

	static belongsTo = [price: Precio]
    static constraints = {
    	details blank:false
    }
}