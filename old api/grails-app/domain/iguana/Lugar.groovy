package iguana

class Lugar {
	String sigla
	String nombre	
	Coordenada coordenada

	String toString(){
		"${sigla}"
	}
	
	
	static constraints = {
		nombre blank: false , nullable: false
  }
}