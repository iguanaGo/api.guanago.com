package iguana

class Vuelo {
	String origen
	String destino
	String precioTotal
	
	static hasMany = [segmentos: Segmento]

    static constraints = {
    }
}