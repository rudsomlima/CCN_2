package ccn_2

class ConsultaJob {

    def BoletosService /*Regras de Negocio*/

    static triggers = {
      //simple repeatInterval: 5000l // execute job once in 5 second
        //
        cron 'cronTrigger', cronExpression: '0 20 13 * * ?'  //0 15 10 15 * ?
    }

    def execute() {
       println "passou no teste"
       BoletosService.extrair()
    }
}
