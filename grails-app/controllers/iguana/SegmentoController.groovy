package iguana



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SegmentoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Segmento.list(params), model:[segmentoInstanceCount: Segmento.count()]
    }

    def show(Segmento segmentoInstance) {
        respond segmentoInstance
    }

    def create() {
        respond new Segmento(params)
    }

    @Transactional
    def save(Segmento segmentoInstance) {
        if (segmentoInstance == null) {
            notFound()
            return
        }

        if (segmentoInstance.hasErrors()) {
            respond segmentoInstance.errors, view:'create'
            return
        }

        segmentoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'segmento.label', default: 'Segmento'), segmentoInstance.id])
                redirect segmentoInstance
            }
            '*' { respond segmentoInstance, [status: CREATED] }
        }
    }

    def edit(Segmento segmentoInstance) {
        respond segmentoInstance
    }

    @Transactional
    def update(Segmento segmentoInstance) {
        if (segmentoInstance == null) {
            notFound()
            return
        }

        if (segmentoInstance.hasErrors()) {
            respond segmentoInstance.errors, view:'edit'
            return
        }

        segmentoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Segmento.label', default: 'Segmento'), segmentoInstance.id])
                redirect segmentoInstance
            }
            '*'{ respond segmentoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Segmento segmentoInstance) {

        if (segmentoInstance == null) {
            notFound()
            return
        }

        segmentoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Segmento.label', default: 'Segmento'), segmentoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'segmento.label', default: 'Segmento'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
