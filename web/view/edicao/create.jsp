<%-- 
    Document   : create
    Created on : Nov 4, 2016, 3:35:03 AM
    Author     : vinicius
--%>

<%@page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Criar Edição</title>
    </head>
    <body style = "background-color: #b9def0">
   
        <h2 style="margin-left: 23%" class="text" style="color: black" ><b>Insira os dados da edição</b></h2><br /><br />
        <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/edicao/create">                    

            <div class = "form-group">
                <label class="control-label col-sm-3">Edição</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="ed" value="${edicao.ed}" placeholder="edição do evento"required autofocus>
                </div>  
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Preço</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="preco" value="${edicao.preco}" placeholder="preço"required autofocus>
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Localização</label>
                <div  class="col-sm-5">
                    <select name="localizacao" >
                        <c:forEach var="l" items="${localizacaoList}">
                            <option value="<c:out value="${l.id}"/>"> <c:out value="${l.nome}" /> </option>
                        </c:forEach>
                    </select> 
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Título do evento</label>
                <div  class="col-sm-5">
                    <select name="tituloev" >
                        <c:forEach var="e" items="${eventoList}">
                            <option value="<c:out value="${e.titulo}"/>"> <c:out value="${e.titulo}" /> </option>
                        </c:forEach>
                            
                    </select> 
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Período</label>
                <div  class="col-sm-5">
                    <table>
                        <thead>
                            <tr>
                                <th style="color: black">Data</th>
                                <th style="color: black">Hora de início</th>
                                <th style="color: black">Hora de fim</th>
                            </tr>
                        </thead>
                        <tbody id="divisao">
                            <tr>
                                <td>
                                    <input type="date" name="dia1" required="true"/>
                                </td>
                                <td>
                                    <input type="time" name="hora1" required="true"/>
                                </td>
                                <td>
                                    <input type="time" name="horaF1" required="true"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <input type="hidden" name="quantidade" value="1" id="quantidade"/>
                    <button type="button" onclick="adiciona()">Adiciona</button>
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
                    <script>
                        function adiciona(){
                            
                            
                            document.getElementById('quantidade').value = parseInt(document.getElementById('quantidade').value)+1;
                           
                            $('#divisao').append('<tr><td><input type=\'date\' name=\'dia'+document.getElementById('quantidade').value+'\'/></td><td> <input type=\'time\' name=\'hora'+document.getElementById('quantidade').value+'\'/></td><td><input type=\'time\' name=\'horaF'+document.getElementById('quantidade').value+'\'/></td></tr>');
                            
                        }
                    </script>
                </div>
            </div>                   
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                    <button type="submit" class="btn btn-success">Criar</button>
                    <a style="margin-left: 37%" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/welcome.jsp">Voltar</a>
                </div>
            </div>          
        </form>                
    </body>
</html>