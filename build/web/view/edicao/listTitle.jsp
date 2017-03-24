<%-- 
    Document   : listTitle
    Created on : Jan 7, 2017, 1:16:44 AM
    Author     : vinicius
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.Edicao"%>
<%@page contentType="text/html"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Edição</title>
    </head>
    <body style="background-color: #b9def0">

        <div class="container">
            <table class="table">
                <thead>
                    <tr>
                        <th style="color: orange">Evento</th>
                        <th style="color: orange">Edição</th>
                    </tr>
                </thead>
                <tbody>                    
                    <c:forEach var="ed" items="${ed}">
                        <tr>
                            <td><c:out value="${ed.tituloev}"/></td> 
                            <td><c:out value="${ed.ed}"/></td>
                        </tr>  
                    </c:forEach>
   
                </tbody>
            </table>  
                        
          <div class="form-group">
                <div class="col-sm-offset-12 col-sm-10">
                    <a class="btn btn-warning" href="${pageContext.servletContext.contextPath}/edicao/pesquisar">Voltar</a>
                </div>
            </div>     
        </div>

    </body>
</html>
