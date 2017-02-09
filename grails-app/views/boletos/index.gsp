<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'boletos.label', default: 'Boletos')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    </head>
    <body>
        <a href="#list-boletos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <div class="col-md-12 text-center">
                <g:link controller="boletos" action="rodar" class="btn btn-lg btn-success">RODAR</g:link>
            </div>

            <table style="width:50%">
                <div class="col-md-3">

                <tr>
                    <th>Nome</th>
                    <th style="text-align: center">Valor</th>
                    <th style="text-align: center">Vencimento</th>
                </tr>
                <g:each var="boleto" in="${boletosList}">
                    <tr>
                        <td><a href="http://www.sysca.com.br/ver_boleto.php?cod=${boleto.site}">${boleto.nome}</a></td>
                        <td style="text-align: center">${boleto.valor}</td>
                        <td style="text-align: center">${boleto.vencimento}</td>

                    </tr>
                </g:each>
            </table>



            <div class="pagination">
                <g:paginate total="${boletosCount ?: 0}" />
            </div>

    </body>
</html>