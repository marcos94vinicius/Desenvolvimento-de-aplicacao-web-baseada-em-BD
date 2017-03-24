<%-- 
    Document   : bloco
    Created on : Jan 26, 2017, 9:01:10 AM
    Author     : vinicius
--%>

<%@page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Fazer inscrição</title>
    </head>
    <body style = "background-color: #b9def0">
   
        <h2 style="margin-left: 23%" class="text" style="color: black" ><b>Insira os dados do evento</b></h2><br /><br />
        <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/inscricao/importar">                    

            
            <div class = "form-group">
                <label class="control-label col-sm-3">Importar de:</label>
                <div  class="col-sm-5">
                    <select name="id_edicao" >
                        <c:forEach var="ed" items="${edicaoList}">
                            <option value="<c:out value="${ed.id}"/>"> <c:out value="${ed.tituloev} - ${ed.ed}" /> </option>
                        </c:forEach>
                    </select> 
                </div>
            </div>   
            <div class = "form-group">
                <label class="control-label col-sm-3">para:</label>
                <div  class="col-sm-5">
                    <select name="id_edicao2" >
                        <c:forEach var="ed" items="${edicaoList}">
                            <option value="<c:out value="${ed.id}"/>"> <c:out value="${ed.tituloev} - ${ed.ed}" /> </option>
                        </c:forEach>
                    </select> 
                </div>
            </div> 
            <div class="col-sm-offset-3 col-sm-10">
                <button type="submit" class="btn btn-success">Importar</button>
            </div>
        </form>                
    </body>
</html>

