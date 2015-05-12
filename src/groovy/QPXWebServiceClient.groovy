//@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' )
package iguana.src.groovy


import groovyx.net.http.HTTPBuilder
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.util.slurpersupport.GPathResult
import org.apache.http.client.HttpResponseException
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*




/**
* Realiza peticiones al GDS de Google Flights (QPX).
* 
* DOC: https://developers.google.com/qpx-express/v1/trips/search
* 
*  REQUEST BODY
*  ============
*  
*  {
 "request": {
   "passengers": {
     "kind": "qpxexpress#passengerCounts",
     "adultCount": integer,
     "childCount": integer,
     "infantInLapCount": integer,
     "infantInSeatCount": integer,
     "seniorCount": integer
   },
   "slice": [
     {
       "kind": "qpxexpress#sliceInput",
       "origin": string,
       "destination": string,
       "date": string,
       "maxStops": integer,
       "maxConnectionDuration": integer,
       "preferredCabin": string,
       "permittedDepartureTime": {
         "kind": "qpxexpress#timeOfDayRange",
         "earliestTime": string,
         "latestTime": string
       },
       "permittedCarrier": [
         string
       ],
       "alliance": string,
       "prohibitedCarrier": [
         string
       ]
     }
   ],
   "maxPrice": string,
   "saleCountry": string,
   "refundable": boolean,
   "solutions": integer
 }
}

*
*RESPONSE
*=========
*
*{
 "kind": "qpxExpress#tripsSearch",
 "trips": {
   "kind": "qpxexpress#tripOptions",
   "requestId": string,
   "data": {
     "kind": "qpxexpress#data",
     "airport": [
       {
         "kind": "qpxexpress#airportData",
         "code": string,
         "city": string,
         "name": string
       }
     ],
     "city": [
       {
         "kind": "qpxexpress#cityData",
         "code": string,
         "country": string,
         "name": string
       }
     ],
     "aircraft": [
       {
         "kind": "qpxexpress#aircraftData",
         "code": string,
         "name": string
       }
     ],
     "tax": [
       {
         "kind": "qpxexpress#taxData",
         "id": string,ices/clients/QPXWebServiceClient.groovy: 
         "name": string
       }
     ],
     "carrier": [
       {
         "kind": "qpxexpress#carrierData",
         "code": string,
         "name": string
       }
     ]
   },
   "tripOption": [
     {
       "kind": "qpxexpress#tripOption",
       "saleTotal": string,
       "id": string,
       "slice": [
         {
           "kind": "qpxexpress#sliceInfo",
           "duration": integer,
           "segment": [
             {
               "kind": "qpxexpress#segmentInfo",
               "duration": integer,
               "flight": {
                 "carrier": string,
                 "number": string
               },
               "id": string,
               "cabin": string,
               "bookingCode": string,
               "bookingCodeCount": integer,
               "marriedSegmentGroup": string,
               "subjectToGovernmentApproval": boolean,
               "leg": [
                 {
                   "kind": "qpxexpress#legInfo",
                   "id": string,
                   "aircraft": string,
                   "arrivalTime": string,
                   "departureTime": string,
                   "origin": string,
                   "destination": string,
                   "originTerminal": string,
                   "destinationTerminal": string,
                   "duration": integer,
                   "operatingDisclosure": string,
                   "onTimePerformance": integer,
                   "mileage": integer,
                   "meal": string,
                   "secure": boolean,
                   "connectionDuration": integer,
                   "changePlane": boolean
                 }
               ],
               "connectionDuration": integer
             }
           ]
         }
       ],
       "pricing": [
         {
           "kind": "qpxexpress#pricingInfo",
           "fare": [
             {
               "kind": "qpxexpress#fareInfo",
               "id": string,
               "carrier": string,
               "origin": string,
               "destination": string,
               "basisCode": string,
               "private": boolean
             }
           ],
           "segmentPricing": [
             {
               "kind": "qpxexpress#segmentPricing",
               "fareId": string,
               "segmentId": string,
               "freeBaggageOption": [
                 {
                   "kind": "qpxexpress#freeBaggageAllowance",
                   "bagDescriptor": [
                     {
                       "kind": "qpxexpress#bagDescriptor",
                       "commercialName": string,
                       "count": integer,
                       "description": [
                         stringices/clients/QPXWebServiceClient.groovy: 
                       ],
                       "subcode": string
                     }
                   ],
                   "kilos": integer,
                   "kilosPerPiece": integer,
                   "pieces": integer,
                   "pounds": integer
                 }
               ]
             }
           ],
           "baseFareTotal": string,
           "saleFareTotal": string,
           "saleTaxTotal": string,
           "saleTotal": string,
           "passengers": {
             "kind": "qpxexpress#passengerCounts",
             "adultCount": integer,
             "childCount": integer,
             "infantInLapCount": integer,
             "infantInSeatCount": integer,
             "seniorCount": integer
           },
           "tax": [
             {
               "kind": "qpxexpress#taxInfo",
               "id": string,
               "chargeType": string,
               "code": string,
               "country": string,
               "salePrice": string
             }
           ],
           "fareCalculation": string,
           "latestTicketingTime": string,
           "ptc": string,
           "refundable": boolean
         }
       ]
     }
   ]
 }
}
*
*
*
*
*
*
*
*/
public class QPXWebServiceClient {

	def grailsApplication
	
	public def request(String from, String destination, String fecha, String keyApi, Integer numeroResultados) 
	{
		
		//paulofer85 key: AIzaSyCdcaBHbsLjm8Vtp4lCg9UYJXsVraO-hwA
		//paulo.miguel.fernandez: AIzaSyCiUGOjUJWSZiBNn5KRmp3hEVswYjk9Djg
		//keyApi = keyApi?.trim() ? keyApi : "AIzaSyCdcaBHbsLjm8Vtp4lCg9UYJXsVraO-hwA"
		//numeroResultados = numeroResultados? numeroResultados : 20
		
		HTTPBuilder builder = new HTTPBuilder("https://www.googleapis.com/qpxExpress/v1/trips/search?key=" +  "AIzaSyCdcaBHbsLjm8Vtp4lCg9UYJXsVraO-hwA")
		def result = builder.request(POST, JSON) 
		{ req ->
			body = ["request": 
						["passengers": ["adultCount": 1]
							,"slice": 
							[
									["origin": from,"destination": destination,"date": fecha]
									//, ["origin": destination,"destination": from,"date": "2015-05-23"]
							]
						,"solutions": 20
						]
					]
			response.success = {resp, json ->
				println "JSON POST Success: ${resp.statusLine}"
				return json
			}

			response.failure = {resp, html ->
				println "JSON POST Failed: ${resp.statusLine}"
			}
		}
		return result
	}


	static void main(String[] args) {
		QPXWebServiceClient qpx = new QPXWebServiceClient()
		def slurper = new JsonSlurper()
		
		def result = new JsonBuilder( qpx.request(args[0], args[1]))
		def flights = slurper.parseText('{ "flights":' + result + '}')
		def trips = flights.flights.trips.tripOption.sort{a,b ->  Float.parseFloat(a.saleTotal.substring(3,a.saleTotal.length())) <=>  Float.parseFloat(b.saleTotal.substring(3,b.saleTotal.length())) }
		
		//FileUtils.save(result,"result.json")

		println "============================================AIRCRAFTS============================================"
		flights.flights.trips.data.aircraft.each{ 
				println it.name
		}
		println "============================================END OF AIRCRAFTS============================================"
					

		println ("\n")
		println "============================================SALE TOTAL============================================"
		trips.each{
			//origin|destination|saleTotal|DAY|departureTime|arrivalTime|duration|flight.carrier|flight.number | aircraft
			println it.slice[0].segment[0].leg[0].origin + "|" +   it.slice[0].segment[0].leg[0].destination + "|" +  
					it.slice[0].segment[0].leg[0].departureTime + "|" +   it.slice[0].segment[0].leg[0].arrivalTime + "|" +  it.slice[0].segment[0].duration/60 + " Hs |"+
					it.saleTotal + "|" + it.slice[0].segment[0].flight.carrier + "|" + it.slice[0].segment[0].flight.number + "|" + it.slice[0].segment[0].leg[0].aircraft + "|"+  
					it.slice[0].segment[0].leg[0].changePlane.toString()
					//it.slice[0].segment[0].bookingCode + "|" it.slice[0].segment[0].bookingCodeCount
		}
		println "============================================END SALE TOTAL============================================"
		println ("\n")
		//println result.toString()
	}
}



