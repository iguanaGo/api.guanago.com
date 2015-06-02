package iguana

import groovy.json.JsonBuilder

class Lugar {
	String sigla
	String nombre
	//Coordenada coordenada

	String toString(){
		"${sigla}"
	}

	String toJson()
	{
		new JsonBuilder( this ).toPrettyString()
	}

	static constraints = {
		nombre blank: false , nullable: false
	}



//	static void main(String[] args) {
//		Lugar lugar = new Lugar(sigla: "BUE", nombre:"BUENOS AIRES")
//
//		String aux = lugar.toJson();
//
//		println aux.dump()
//	}

}


