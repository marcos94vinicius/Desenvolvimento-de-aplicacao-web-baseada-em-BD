<%-- 
    Document   : list
    Created on : Nov 24, 2016, 8:21:43 PM
    Author     : vinicius
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.Inscricoes"%>
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
                        <th style="color: orange">Nome</th>
                    </tr>
                </thead>
                <tbody>                    
                    <c:forEach var="inc" items="${inscricoesList}">
                        
                        <tr>
                            <td><c:out value="${inc.nome}"/></td> 
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
