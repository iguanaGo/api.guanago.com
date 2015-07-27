package iguanago

class Coordenadas {
	double latitude
	double longitude

	static constraints = {
		latitude blank: false
		longitude blank: false
    }
}

class Lugar {
	Coordenadas coordinates
	String name
	String code

	static embedded = ['coordinates']
    static constraints = {
    	coordinates nullable: false
    	name blank: false
    	code blank: true
    }
}