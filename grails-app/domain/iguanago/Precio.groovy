 package iguanago
 
class Precio {
	String currency
	
	static hasMany = [importes: Importe]
    static constraints = {
    	currency blank: false
    }

	float totalValue() {
		float total = 0
		importes.each { total += it.value }
		return total
	}

	Precio(importes){
		this.importes = importes
	}
} 