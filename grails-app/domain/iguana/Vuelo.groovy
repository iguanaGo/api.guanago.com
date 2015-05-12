package iguana

class Vuelo {
	Lugar origen
	Lugar destino
	String precioTotal
	
	static hasMany = [segmentos: Segmento]

  static constraints = {
  }
}