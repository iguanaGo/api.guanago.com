package iguana

import grails.rest.RestfulController

class LugarController extends RestfulController {
    static scaffold = true

	static responseFormats = ['json', 'xml']
	LugarController() {
		super(Lugar)
	}
}
