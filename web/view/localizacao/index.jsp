<%-- 
    Document   : index
    Created on : Nov 3, 2016, 10:00:57 PM
    Author     : vinicius
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.Localizacao"%>
<%@page contentType="text/html"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Localizações</title>
    </head>
    <body style="background-color: #b9def0">

        <div class="container">
            <table class="table">
                <thead>
                    <tr>
                        <th style="color: orange">Nome</th>
                        <th style="color: orange">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <c:forEach var="l" items="${localizacaoList}">
                        
                        <tr>
                            <td><c:out value="${l.nome}"/></td>
                            <c:if test="${usuarioLogado.tipo_user != null}">
                            <td>
                                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/localizacao/update?id=${l.id}" >
                                    Editar
                                </a>
                                <a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/localizacao/delete?id=${l.id}" >
                                    Excluir
                                </a>
                            </td>
                            </c:if>
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