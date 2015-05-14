package iguana

class Lugar {
	String sigla
	String nombre	
	Coordenada coordenada

	static constraints = {
		nombre blank: false , nullable: false
  }
}