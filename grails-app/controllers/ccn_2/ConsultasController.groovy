package ccn_2

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ConsultasController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Consultas.list(params), model:[consultasCount: Consultas.count()]
    }

    def show(Consultas consultas) {
        respond consultas
    }

    def create() {
        respond new Consultas(params)
    }

    @Transactional
    def save(Consultas consultas) {
        if (consultas == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (consultas.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond consultas.errors, view:'create'
            return
        }

        consultas.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'consultas.label', default: 'Consultas'), consultas.id])
                redirect consultas
            }
            '*' { respond consultas, [status: CREATED] }
        }
    }

    def edit(Consultas consultas) {
        respond consultas
    }

    @Transactional
    def update(Consultas consultas) {
        if (consultas == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (consultas.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond consultas.errors, view:'edit'
            return
        }

        consultas.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'consultas.label', default: 'Consultas'), consultas.id])
                redirect consultas
            }
            '*'{ respond consultas, [status: OK] }
        }
    }

    @Transactional
    def delete(Consultas consultas) {

        if (consultas == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        consultas.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'consultas.label', default: 'Consultas'), consultas.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'consultas.label', default: 'Consultas'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
