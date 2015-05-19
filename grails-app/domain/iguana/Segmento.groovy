package iguana

class Segmento {

	String aerolinea
	String numeroDeVuelo
	String duracion
	
	static hasMany = [escalas: Escala]
	
	String toString(){
		"${numeroDeVuelo}"
	}
	
  static constraints = {
  }
}
