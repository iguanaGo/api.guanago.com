package iguana

class Coordenadas {
	double latitud
	double longitud
}

class Lugar {
	String nombre
	String siglas
	Coordenadas coordenadas

	static embedded = ['coordenadas']

  static constraints = {
  	nombre blank: false , nullable: false
  }
}