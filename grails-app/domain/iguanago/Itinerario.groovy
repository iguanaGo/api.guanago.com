package iguanago

class Itinerario {

	boolean isPublic
	Stack vuelos

    static hasOne = [budget: Presupuesto]
	static hasMany = [vuelos: Vuelo]
    static constraints = {
        budget nullable: false
    }

    void addVuelo(Vuelo vuelo) {
        if(!vuelos.empty()){
            lastFlight = vuelos.peek()

            if(lastFlight.arrivalDate.after(vuelo.departureDate)){
                //trhow ex
                return
            }

            if(!budget.hasEnoughFor(vuelo)){
                //trhow ex
                return
            }
        }
        vuelos.push(vuelo)
    }

    Itinerario(budget){
        this.budget = budget
    }
}