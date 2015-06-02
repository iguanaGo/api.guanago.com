package iguana

import groovy.json.JsonBuilder

class Coordenada {
	double latitud
	double longitud
	
	
	static belongsTo = [lugar:Lugar]
}