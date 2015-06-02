package iguana

import groovy.json.JsonBuilder
class Vuelo {
	Lugar origen
	Lugar destino
	String precioTotal
	
	
	static hasMany = [segmentos: Segmento, origenVuelo: Lugar, destinoVuelo: Lugar]
	static mappedBy = [origen: "sigla", destino: "sigla"]

  static constraints = {
  }
  
  String toJson()
  {
	  new JsonBuilder( this ).toPrettyString()
  }
}