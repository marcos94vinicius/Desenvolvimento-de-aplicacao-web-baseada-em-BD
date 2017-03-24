<%-- 
    Document   : inscricaoEdicao
    Created on : Nov 19, 2016, 7:00:13 PM
    Author     : vinicius
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.InscricaoEdicao"%>
<%@page contentType="text/html"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Inscrições</title>
    </head>
    <body style="background-color: #b9def0">

        <div class="container">
            <table class="table">
                <thead>
                    <tr>
                        <th style="color: orange">Evento</th>
                        <th style="color: orange">Edição</th>
                        <th style="color: orange">Ações</th>
                    </tr>
                </thead>
                <tbody>                    
                    <c:forEach var="inscricaoEd" items="${inscricaoEdicaoList}">
                        
                        <tr>
                            <td><c:out value="${inscricaoEd.tituloev}"/></td> 
                            <td><c:out value="${inscricaoEd.ed}"/></td>
                        </tr>  

                    </c:forEach>
   
                </tbody>
            </table>  
                        
          <div class="form-group">
                <div class="col-sm-offset-12 col-sm-10">
                    <c:if test="${usuarioLogado.tipo_user != null}"> 
                        <a class="btn btn-warning" href="${pageContext.servletContext.contextPath}/welcome.jsp">Voltar</a>
                    </c:if> 
                </div>
            </div>     
        </div>

    </body>
</html>

