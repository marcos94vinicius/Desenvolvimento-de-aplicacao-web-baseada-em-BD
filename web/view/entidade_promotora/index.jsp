<%-- 
    Document   : index
    Created on : Oct 28, 2016, 4:21:48 PM
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
        <title>Entidade Promotora</title>
    </head>
    <body style="background-color: #b9def0">

        <div class="container">
            <table class="table">
                <thead>
                    <tr>
                        <th style="color: orange">Nome</th>
                        <th style="color: orange">Descrição</th>
                        <th style="color: orange">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <c:forEach var="ep" items="${promotoraList}">
                        
                        <tr>
                            <td><c:out value="${ep.nome}"/></td>
                            <td><c:out value="${ep.descricao}"/></td>
                            <c:if test="${usuarioLogado.tipo_user != null}">
                            <td>
                                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/entidade_promotora/update?id=${ep.id}" >
                                    Editar
                                </a>
                                <a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/entidade_promotora/delete?id=${ep.id}" >
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