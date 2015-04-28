package iguana

import iguana.src.groovy.*
import grails.transaction.Transactional
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.util.slurpersupport.GPathResult



@Transactional(readOnly = true)
class VueloController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def searchFlights()
	{
		try {
			QPXWebServiceClient qpx = new QPXWebServiceClient()

			def slurper = new JsonSlurper()

			def result = new JsonBuilder( qpx.request("BUE", "BOG"))
			def flights = slurper.parseText('{ "flights":' + result + '}')
			def trips = flights.flights.trips.tripOption.sort{a,b ->  Float.parseFloat(a.saleTotal.substring(3,a.saleTotal.length())) <=>  Float.parseFloat(b.saleTotal.substring(3,b.saleTotal.length())) }

			FileUtils.save(result,"result.json")

			def aircrafts
			flights.flights.trips.data.aircraft.each{
				aircrafts += it.name
			}

			def vuelos = "origin|destination|DAY|departureTime|arrivalTime|duration|saleTotal|flight.carrier|flight.number | aircraft <br />"
			trips.each{
				//origin|destination|saleTotal|DAY|departureTime|arrivalTime|duration|flight.carrier|flight.number | aircraft
				vuelos += it.slice[0].segment[0].leg[0].origin + "|" +   it.slice[0].segment[0].leg[0].destination + "|" +
						it.slice[0].segment[0].leg[0].departureTime + "|" +   it.slice[0].segment[0].leg[0].arrivalTime + "|" +  it.slice[0].segment[0].duration/60 + " Hs |"+
						it.saleTotal + "|" + it.slice[0].segment[0].flight.carrier + "|" + it.slice[0].segment[0].flight.number + "|" + it.slice[0].segment[0].leg[0].aircraft + "|"+
						it.slice[0].segment[0].leg[0].changePlane.toString() + "\n"
				//it.slice[0].segment[0].bookingCode + "|" it.slice[0].segment[0].bookingCodeCount
			}
			//println result.toString()

			render "vuelos <br /> ${vuelos}"
		} catch (Exception e) {
			render "#ERROR: " + e.getMessage()
			e.printStackTrace()
		}
	}
	

    def index(Integer max) {
	
        params.max = Math.min(max ?: 10, 100)
        respond Vuelo.list(params), model:[vueloInstanceCount: Vuelo.count()]
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
