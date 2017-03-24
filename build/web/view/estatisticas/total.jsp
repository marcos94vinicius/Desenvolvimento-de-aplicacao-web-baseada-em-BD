<%-- 
    Document   : index
    Created on : Dec 20, 2016, 1:56:37 PM
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
                        <th style="color: orange">Total</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><c:out value="${inscritos}"/></td> 
                    </tr>
                </tbody>
            </table>     
        </div>

    </body>
</html>