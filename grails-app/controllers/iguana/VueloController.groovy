package iguana

import iguana.src.groovy.*
import grails.transaction.Transactional
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.util.slurpersupport.GPathResult


/**
 * Es el comando que se necesita para hacer un busqueda de vuelos (searchFlights)
 * Al pedir una consulta se debe pasar por POST (puede ser de URL o por form) los comandos origen y destino.
 * 
 * Ex. http://localhost:8080/iguana/vuelo/searchFlights?origen=BUE&destino=SCL
 * 
 * origen y destino tienen que ser de tipo codigo IATA y pueden ser ciudades o aeropuertos
 * 
 *
 */
class BusquedaVueloCommand {
	String origen
	String destino
	String fecha

	static constraints = {
		fecha blank: false, nullable: false
		origen blank: false, nullable: false
		destino blank: false, nullable: false		
		//numeroResultados blank: false, nullable: false
	}
}

/***
 * 
 *
 */
@Transactional(readOnly = true)
class VueloController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	

	def index(Integer max) {
		
		[command: new BusquedaVueloCommand()]
		
		params.max = Math.min(max ?: 10, 100)
		respond Vuelo.list(params), model:[vueloInstanceCount: Vuelo.count()]
	}
	

	/***
	 * @param trips
	 * @param origen
	 * @param destino
	 * @return
	 */
	def cargarVuelosQPX(def trips, String origen, String destino)
	{
		//		//Imprimir salida
		//		def vuelosRender = "origin|destination|DAY|departureTime|arrivalTime|duration|saleTotal|flight.carrier|flight.number | aircraft <br />"
		//		trips.each{
		//			//origin|destination|saleTotal|DAY|departureTime|arrivalTime|duration|flight.carrier|flight.number | aircraft
		//			vuelosRender += it.slice[0].segment[0].leg[0].origin + "|" +   it.slice[0].segment[0].leg[0].destination + "|" +
		//					it.slice[0].segment[0].leg[0].departureTime + "|" +   it.slice[0].segment[0].leg[0].arrivalTime + "|" +  it.slice[0].segment[0].duration/60 + " Hs |"+
		//					it.saleTotal + "|" + it.slice[0].segment[0].flight.carrier + "|" + it.slice[0].segment[0].flight.number + "|" + it.slice[0].segment[0].leg[0].aircraft + "|"+
		//					it.slice[0].segment[0].leg[0].changePlane.toString() + "\n"
		//			//it.slice[0].segment[0].bookingCode + "|" it.slice[0].segment[0].bookingCodeCount
		//		}
		//		render "vuelos <br /> ${trips}"

		Lugar lugarControl = new Lugar()
		trips.each{
			//origin|destination|saleTotal|DAY|departureTime|arrivalTime|duration|flight.carrier|flight.number | aircraft
			Vuelo vuelo = new Vuelo()
	
			vuelo.precioTotal = it.saleTotal
			vuelo.origen =  Lugar.find { sigla  == origen }
			vuelo.destino = Lugar.find{ sigla == destino }
			
			def slices = it.slice			
			slices.each{ sliceIt ->
				def segmentos = sliceIt.segment
				segmentos.each{ segmentoIt ->
					Segmento segmento = new Segmento()
									
					segmento.aerolinea = segmentoIt.flight.carrier
					segmento.numeroDeVuelo = segmentoIt.flight.number
					segmento.duracion =  segmentoIt.duration/60
		
					def escalas = segmentoIt.leg
					escalas.each{ escalaIt ->
						Escala escala = new Escala()
						
						escala.fechaLlegada =  escalaIt.arrivalTime
						escala.fechaSalida =  escalaIt.departureTime
						escala.avion = escalaIt.aircraft
						escala.duracion = escalaIt.duration/60				
						escala.origen = Lugar.find { sigla  == escalaIt.origin }
						escala.destino =  Lugar.find { sigla  == escalaIt.destination}
						
						segmento.addToEscalas(escala)
					}
					
					vuelo.addToSegmentos(segmento)
				}
			}
			
			vuelo.save(flush:true)
		}
	}

	/***
	 * FIXME cambiar nombre de metodo por buscarVuelos
	 * @param command origen y destino del tipo IATA
	 * @return
	 */
	def searchFlights(BusquedaVueloCommand command)
	{
		if (command.hasErrors()) {
			render view: "index", model: [command: command]
			flash.message = "Por favor pase los parametros correspondientes"
			return
		}

		try {

			String origen = command.origen.toString()
			String destino = command.destino.toString()
			String fecha = command.fecha.toString()

			QPXWebServiceClient qpx = new QPXWebServiceClient()

			def slurper = new JsonSlurper()

			//def result = new JsonBuilder( qpx.request(origen, destino, fecha, grailsApplication.config["QPX_KEY"] , grailsApplication.config["QPX_NUMBER_OF_RESULTS"].toInteger() ) )
			def result = new JsonBuilder( qpx.request(origen, destino, fecha, "" , 0 ) )
			def flights = slurper.parseText('{ "flights":' + result + '}')

			//FileUtils.save(result,"result.json") 

			if(flights.flights != null)
			{
				def trips = flights.flights.trips.tripOption.sort{a,b ->  Float.parseFloat(a.saleTotal.substring(3,a.saleTotal.length())) <=>  Float.parseFloat(b.saleTotal.substring(3,b.saleTotal.length())) }
				cargarVuelosQPX(trips, origen, destino)

				//render "vuelos <br /> ${vuelos}"
			}
			else
			{
				render view: "index", model: [command: command]
				flash.message = "EL SERVICIO NO DEVOLVIO DATOS. Para origen " + origen + " destino " + destino
			}


		} catch (Exception e) {
			def rdo = ""
			grailsApplication.config.each { rdo  += it.toString() + "<br />"}
			render "#ERROR: " + e.getMessage() + "<br /> " + rdo 
			e.printStackTrace()
		}
	}


	def show(Vuelo vueloInstance) {
		respond vueloInstance
	}

	def create() {
		respond new Vuelo(params)
	}

	@Transactional
	def save(Vuelo vueloInstance) {
		if (vueloInstance == null) {
			notFound()
			return
		}

		if (vueloInstance.hasErrors()) {
			respond vueloInstance.errors, view:'create'
			return
		}

		vueloInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'vuelo.label', default: 'Vuelo'), vueloInstance.id])
				redirect vueloInstance
			}
			'*' { respond vueloInstance, [status: CREATED] }
		}
	}

	def edit(Vuelo vueloInstance) {
		respond vueloInstance
	}

	@Transactional
	def update(Vuelo vueloInstance) {
		if (vueloInstance == null) {
			notFound()
			return
		}

		if (vueloInstance.hasErrors()) {
			respond vueloInstance.errors, view:'edit'
			return
		}

		vueloInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'Vuelo.label', default: 'Vuelo'), vueloInstance.id])
				redirect vueloInstance
			}
			'*'{ respond vueloInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Vuelo vueloInstance) {

		if (vueloInstance == null) {
			notFound()
			return
		}

		vueloInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'Vuelo.label', default: 'Vuelo'), vueloInstance.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'vuelo.label', default: 'Vuelo'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
