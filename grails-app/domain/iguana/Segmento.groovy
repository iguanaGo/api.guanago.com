package iguana

import groovy.json.JsonBuilder

class Segmento {

	String aerolinea
	String numeroDeVuelo
	String duracion
	
	static hasMany = [escalas: Escala]
	
	String toString(){
		"${numeroDeVuelo}"
	}
	
	String toJson()
	{
		new JsonBuilder( this ).toPrettyString()
	}
	
  static constraints = {
  }
}
