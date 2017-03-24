<%-- 
    Document   : pesquisar
    Created on : Jan 7, 2017, 12:25:15 AM
    Author     : vinicius
--%>

<%@page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Pesquisar evento</title>
    </head>
    <body style = "background-color: #b9def0">
   
        <h2 style="margin-left: 23%" class="text" style="color: black" ><b>Digite no campo que desejar e marque o check-box</b></h2><br /><br />
        <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/edicao/pesquisar">                    

            <div class = "form-group">
                <label class="control-label col-sm-3">Evento</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="nome" placeholder="Digite o nome do evento">
                </div>  
                <input type="checkbox" name="c1" value="1"><br>
            </div>    
            <div class = "form-group">
                <label class="control-label col-sm-3">Entidade Promotora</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="entidade" placeholder="Digite a entidade promotora">
                </div>  
                <input type="checkbox" name="c1" value="2"><br>
            </div>  
            
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                    <button type="submit" class="btn btn-success">Pesquisar</button>
                </div>
            </div>         
        </form>   
    </body>
</html>
