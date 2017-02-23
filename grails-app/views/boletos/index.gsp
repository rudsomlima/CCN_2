<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'agendaConsulta.label', default: 'AgendaConsulta')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
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
            <g:field name="busca_nome" type="search" style="color:#ff0000"/>
            <g:submitButton name="busca" value="Buscar"/>
        </g:form>
    </div>

%{--<div>--}%
%{--<g:form action="busca">--}%
    %{--<fieldset class="form">--}%
        %{--<div class="ui-widget">--}%
            %{--<f:field bean="boletos" property="nome" />--}%
        %{--</div>--}%
    %{--</fieldset>--}%
%{--</g:form>--}%
%{--</div>--}%








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

    <g:javascript>
        // alert("Javascript funcionando!");
        $(document).ready(function () {
            $( "#busca_nome" ).autocomplete({
                minLength: 3,
                source: function( request, response ) {
                    $.ajax({
                        url:'${g.createLink(controller: 'boletos', action: 'busca')}',
                        dataType: 'json',
                        data: {codigo: $('#busca_nome').val()},
                        success: function (data) {
                            response(data);
                        },
                        error: function (request, status, error) {
                            console.log('Deu erro');
                        }
                    });
                },
                change: function( event, ui ) {
                    $("#busca_nome").val(ui.item.value.substring(0,3));
                }

            });
        });

    </g:javascript>
    </body>
</html>