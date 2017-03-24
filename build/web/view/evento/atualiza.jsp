<%-- 
    Document   : insereUsuario
    Created on : Sep 30, 2016, 3:37:27 PM
    Author     : vinicius
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Atualizar evento</title>
    </head>
    <body style = "background-color: #b9def0">
   
        <h2 style="margin-left: 23%" class="text" style="color: black" ><b>Altere os dados desejados</b></h2><br /><br />
        <form class="form-horizontal" name="formEvento" method="POST" action="${pageContext.servletContext.contextPath}/evento/update">                    
            <input type="hidden" name="id" value="${e.id}">
            <div class = "form-group">
                <label class="control-label col-sm-3">Título</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="titulo" value="${e.titulo}" placeholder="titulo">
                </div>  
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Descrição</label>
                <div  class="col-sm-5">
                    <input type="text" class="form-control" name="descricao" value="${e.descricao}" placeholder="descrição do evento" >
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Informações</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="informacoes" value="${e.informacoes}" placeholder="informações sobre o evento">
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Entidade Promotora</label>
                <div  class="col-sm-5">
                    <select name="entidade_promotora" >
                        <c:forEach var="ep" items="${promotoraList}">
                            <option value="<c:out value="${ep.id}"/>"> <c:out value="${ep.nome}" /> </option>
                        </c:forEach>                  
                    </select> 
                </div>
            </div>                                       
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                    <button type="submit" class="btn btn-success">Atualizar</button>
                    <a style="margin-left: 37%" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/evento">Voltar</a>

                </div>
            </div>          
        </form>                
    </body>
</html>

