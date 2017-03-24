<%-- 
    Document   : index2
    Created on : Jan 7, 2017, 3:19:14 PM
    Author     : vinicius
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.EntPromotora"%>
<%@page contentType="text/html"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Resultado</title>
    </head>
    <body style="background-color: #b9def0">

        <div class="container">
            <table class="table">
                <thead>
                    <tr>
                        <th style="color: orange">Evento</th>
                        <th style="color: orange">Edição</th>
                        <th style="color: orange">Quantidade</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <c:forEach var="aux" items="${aux}">
                        
                        <tr>
                            <td><c:out value="${aux.titulo}"/></td>
                            <td><c:out value="${aux.edicao}"/></td>
                            <td><c:out value="${aux.quantidade}"/></td>
                        </tr>  

                    </c:forEach>
   
                </tbody>
            </table>  
                        
          <div class="form-group">
                <div class="col-sm-offset-12 col-sm-10">
                    <c:if test="${usuarioLogado.tipo_user != null}"> 
                        <a class="btn btn-warning" href="${pageContext.servletContext.contextPath}/edicao">Voltar</a>
                    </c:if> 
                </div>
            </div>     
        </div>

    </body>
</html>
