<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'boletos.label', default: 'Boletos')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-boletos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="list-boletos" class="content scaffold-list" role="main">
                 <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${boletosList}" properties="['nome','valor','vencimento']" />
            <table style="width:50%">
                <div class="col-md-3">

                <tr>
                    <th>Nome</th>
                    <th style="text-align: center">Valor</th>
                    <th style="text-align: center">Vencimento</th>
                </tr>
                <g:each var="boleto" in="${boletosList}">
                    <tr>
                        <td><a href="${boleto.site}">${boleto.nome}</a></td>
                        <td style="text-align: center">${String.format("%.2f",boleto.valor)}</td>
                        <td style="text-align: center">${boleto.vencimento}</td>

                    </tr>
                </g:each>
            </table>


            <div class="pagination">
                <g:paginate total="${boletosCount ?: 0}" />
            </div>
            <g:link controller="boletos" action="rodar" class="btn btn-lg btn-success">RODAR</g:link>
        </div>
    </body>
</html>