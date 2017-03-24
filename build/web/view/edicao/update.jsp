<%-- 
    Document   : update
    Created on : Nov 4, 2016, 3:35:13 AM
    Author     : vinicius
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Atualizar Edição</title>
    </head>
    <body style = "background-color: #b9def0">
   
        <h2 style="margin-left: 23%" class="text" style="color: black" ><b>Modifique os dados desejados</b></h2><br /><br />
        <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/edicao/update">                    
            <input type="hidden" name="id" value="${edi.id}">
            <div class = "form-group">
                <label class="control-label col-sm-3">Nome</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="ed" value="${edi.ed}" placeholder="edição">
                </div>  
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Preço</label>
                <div  class="col-sm-5">
                    <input type="text" class="form-control" name="preco" value="${edi.preco}" placeholder="preço do evento" >
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
                <label class="control-label col-sm-3">Ativar inscrições?</label>
                <div  class="col-sm-5">
                    <select name="status_inscricoes">
                        <option value='0' ${edi.status_inscricoes == 0 ? 'selected' : ''}>Não</option>
                        <option value='1' ${edi.status_inscricoes == 1 ? 'selected' : ''}>Sim</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                    <button type="submit" class="btn btn-success">Atualizar</button>
                    <a style="margin-left: 37%" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/edicao">Voltar</a>
                </div>
            </div>          
        </form>                
    </body>
</html>
