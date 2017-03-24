<%-- 
    Document   : lista_edicoes
    Created on : Nov 19, 2016, 12:17:57 PM
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
                        <th style="color: orange">Preço</th>
                    </tr>
                </thead>
                <tbody>                    
                    <c:forEach var="edicao" items="${edicaoList}">
                        
                        <c:if test="${edicao.status_inscricoes == 1}">
                        <tr>
                            <td><c:out value="${edicao.tituloev}"/></td> 
                            <td><c:out value="${edicao.ed}"/></td>
                            <td><c:out value="R$ -- ${edicao.preco}"/></td>
                        </tr>  
                        </c:if>

                    </c:forEach>
   
                </tbody>
            </table>  
                        
          <div class="form-group">
                <div class="col-sm-offset-12 col-sm-10">
                    <a class="btn btn-warning" href="${pageContext.servletContext.contextPath}/index.jsp">Voltar</a>
                </div>
            </div>     
        </div>

    </body>
</html>
