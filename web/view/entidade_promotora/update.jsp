<%-- 
    Document   : update
    Created on : Oct 31, 2016, 5:32:37 PM
    Author     : vinicius
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Atualizar Entidade Promotora</title>
    </head>
    <body style = "background-color: #b9def0">
   
        <h2 style="margin-left: 23%" class="text" style="color: black" ><b>Modifique os dados desejados</b></h2><br /><br />
        <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/entidade_promotora/update">                    
            <input type="hidden" name="id" value="${ep.id}">
            <div class = "form-group">
                <label class="control-label col-sm-3">Nome</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="nome" value="${ep.nome}" placeholder="nome da entidade promotora">
                </div>  
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Descrição</label>
                <div  class="col-sm-5">
                    <input type="text" class="form-control" name="descricao" value="${ep.descricao}" placeholder="descrição da entidade promotora" >
                </div>
            </div>
                                       
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                    <button type="submit" class="btn btn-success">Atualizar</button>
                    <a style="margin-left: 37%" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/entidade_promotora">Voltar</a>
                </div>
            </div>          
        </form>                
    </body>
</html>