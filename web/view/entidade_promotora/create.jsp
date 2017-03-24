<%-- 
    Document   : insereUsuario
    Created on : Sep 30, 2016, 3:37:27 PM
    Author     : vinicius
--%>

<%@page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Inserir Evento</title>
    </head>
    <body style = "background-color: #b9def0">
   
        <h2 style="margin-left: 23%" class="text" style="color: black" ><b>Insira os dados da entidade promotora</b></h2><br /><br />
        <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/entidade_promotora/create">                    

            <div class = "form-group">
                <label class="control-label col-sm-3">Nome</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="nome" value="${ep.nome}" placeholder="Nome da entidade promotora"required autofocus>
                </div>  
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Descrição</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="descricao" value="${ep.descricao}" placeholder="descrição"required autofocus>
                </div>
            </div>
                                        
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                    <button type="submit" class="btn btn-success">Cadastrar</button>
                    <a style="margin-left: 37%" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/welcome.jsp">Voltar</a>
                </div>
            </div>          
        </form>                
    </body>
</html>

