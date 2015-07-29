 package iguanago
 
class Precio {
	String currency
	float totalValue

	static hasMany = [importes: Importe]
    static constraints = {
    	currency blank: false
    }

	Precio(importes, currency){
		this.importes = importes
		this.currency = currency
		this.totalValue = 0
		importes.each { this.totalValue += it.value }
	}
} 