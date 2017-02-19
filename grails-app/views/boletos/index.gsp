<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'boletos.label', default: 'Boletos')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>

        <asset:javascript src="jquery-2.2.0.min.js"/>
        <asset:javascript src="jquery-ui.js"/>
        <asset:stylesheet src="jquery-ui.css"/>

    </head>
    <body>
        <a href="#list-boletos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <div class="col-md-12 text-center">
                <g:link controller="boletos" action="rodar" class="btn btn-lg btn-success">BUSCAR NOVOS BOLETOS</g:link>
            </div>
    <br/>
    <br/>
    <br/>

    <div class="input-sm" style="text-align:center;">
        <g:form nome="formBusca" controller="boletos" action="busca">
            <label>Buscar nome:</label>
            <input name="busca_nome" style="color:#ff0000"/>
            <g:submitButton name="busca" value="Buscar"/>
        </g:form>
            </div>
            <br/>
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