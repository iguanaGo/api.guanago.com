package iguana

class Coordenada {
	double latitud
	double longitud
	
	static belongsTo = [lugar:Lugar]
}