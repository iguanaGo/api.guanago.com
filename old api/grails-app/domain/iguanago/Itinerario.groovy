package iguanago

class Itinerario {
	Presupuesto budget
	boolean isPublic
	SortedSet vuelos

	static hasMany = [vuelos: Vuelo]
    static constraints = {
    }

    void addVuelo(Etapa vuelo) {
    	// TODO checkiar presupuesto suficiente y fechas validas
    	vuelos add vuelo
    }
}