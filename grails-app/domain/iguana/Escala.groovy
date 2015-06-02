package iguana

import groovy.json.JsonBuilder

class Escala {
	String avion
	Lugar origen
	Lugar destino
	String fechaSalida //2015-05-13T03:05-03:00
	String fechaLlegada //2015-05-13T21:35+03:00
	String duracion

	static hasMany = [origenEscala: Lugar, destinoEscala: Lugar]
	static mappedBy = [origen: "sigla", destino: "sigla"]
	
	String toString(){
		"${origen.nombre} DESTINO ${destino.nombre} "
	}
	
	String toJson()
	{
		new JsonBuilder( this ).toPrettyString()
	}

	static constraints = {
	}
}
