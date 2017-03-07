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
    <div class="container span8">
        <a href="#list-boletos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

        <div class="row justify-content-center">
            <div class="col-md-8 col-md-offset-2">
                <g:if test="${flash.message}">
                    <div class="alert alert-info" role="status">${flash.message}</div>
                </g:if>

                <div class="text-center">
                    <g:link controller="boletos" action="rodar"
                            class="btn btn-lg btn-success">BUSCAR NOVOS BOLETOS</g:link>
                </div>

                <br/>
                <br/>


                <g:form nome="formBusca" controller="boletos" action="busca">
                    <div class="input-group input-group-lg col-md-8 col-md-offset-2">
                        <g:field name="busca_nome" type="text" style="color:#ff0000" class="form-control"
                                 placeholder="Insira nome a buscar"/>
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                <i class="glyphicon glyphicon-search"></i>
                            </button>
                        </div>
                    </div>
                </g:form>

                <br/>
                <br/>

                <table style="text-align: center">
                    <div>
                        <tr>
                            <th>Nome</th>
                            <th style="text-align: center">Valor</th>
                            <th style="text-align: center">Vencimento</th>
                        </tr>
                        <g:each var="boleto" in="${boletosList}">
                            <tr>
                                <td><a href="http://www.sysca.com.br/ver_boleto.php?cod=${boleto.site}">${boleto.nome}</a>
                                </td>
                                <td style="text-align: center">${boleto.valor}</td>
                                <td style="text-align: center">${boleto.vencimento}</td>

                            </tr>
                        </g:each>
                    </div>
                </table>

                <div class="pagination">
                    <g:paginate total="${boletosCount ?: 0}"/>
                </div>
            </div>
        </div>
    </div>

    <g:javascript>
        // alert("Javascript funcionando!");
        $(document).ready(function () {
            $( "#busca_nome" ).focus();
            $( "#busca_nome" ).autocomplete({
                minLength: 3,
                source: function( request, response ) {
                    $.ajax({
                        url:'${g.createLink(controller: 'boletos', action: 'ajaxBusca')}',
                        dataType: 'json',
                        data: {ajax: $('#busca_nome').val()},
                        success: function (data) {
                            //$("input[name='busca_nome']").val(data);
                            response(data);
                            //alert(data);
                        },
                        error: function (request, status, error) {
                            console.log('Deu erro');
                        }
                    });
                }


            });
        });

    </g:javascript>
    </body>
</html>