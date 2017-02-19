package ccn_2

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class BoletosController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def boletosService

    def rodar(){
        boletosService.extrair()
        redirect(controller: "boletos", view: "index")
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 50, 100)
        respond Boletos.list(params), model:[boletosCount: Boletos.count()]
    }

    def busca() {
        def nome_buscado = Boletos.findAllByNomeIlike("%$params.busca_nome%")
        //nome_buscado.vencimento = Date.parse("dd/MM/yyyy", nome_buscado.vencimento);
        //nome_buscado.sort{it.vencimento}.reverse()
        //println nome_buscado.nome
        //flash.message = "teste"
        println params.busca_nome
        render(view: "index", model: [boletosList: nome_buscado])
    }

    def show(Boletos boletos) {
        respond boletos
    }

    def create() {
        respond new Boletos(params)
    }

    @Transactional
    save(Boletos boletos) {
        if (boletos == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (boletos.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond boletos.errors, view:'create'
            return
        }

        boletos.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'boletos.label', default: 'Boletos'), boletos.id])
                redirect boletos
            }
            '*' { respond boletos, [status: CREATED] }
        }
    }

    def edit(Boletos boletos) {
        respond boletos
    }

    @Transactional
    update(Boletos boletos) {
        if (boletos == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (boletos.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond boletos.errors, view:'edit'
            return
        }

        boletos.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'boletos.label', default: 'Boletos'), boletos.id])
                redirect boletos
            }
            '*'{ respond boletos, [status: OK] }
        }
    }

    @Transactional
    delete(Boletos boletos) {

        if (boletos == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        boletos.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'boletos.label', default: 'Boletos'), boletos.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'boletos.label', default: 'Boletos'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
