package iguana



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EscalaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Escala.list(params), model:[escalaInstanceCount: Escala.count()]
    }

    def show(Escala escalaInstance) {
        respond escalaInstance
    }

    def create() {
        respond new Escala(params)
    }

    @Transactional
    def save(Escala escalaInstance) {
        if (escalaInstance == null) {
            notFound()
            return
        }

        if (escalaInstance.hasErrors()) {
            respond escalaInstance.errors, view:'create'
            return
        }

        escalaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'escala.label', default: 'Escala'), escalaInstance.id])
                redirect escalaInstance
            }
            '*' { respond escalaInstance, [status: CREATED] }
        }
    }

    def edit(Escala escalaInstance) {
        respond escalaInstance
    }

    @Transactional
    def update(Escala escalaInstance) {
        if (escalaInstance == null) {
            notFound()
            return
        }

        if (escalaInstance.hasErrors()) {
            respond escalaInstance.errors, view:'edit'
            return
        }

        escalaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Escala.label', default: 'Escala'), escalaInstance.id])
                redirect escalaInstance
            }
            '*'{ respond escalaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Escala escalaInstance) {

        if (escalaInstance == null) {
            notFound()
            return
        }

        escalaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Escala.label', default: 'Escala'), escalaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'escala.label', default: 'Escala'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
