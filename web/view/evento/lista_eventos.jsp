<%-- 
    Document   : index
    Created on : 19/09/2016, 10:59:46
    Author     : dskaster
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.Evento"%>
<%@page contentType="text/html"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Eventos</title>
    </head>
    <body style="background-color: #b9def0">

        <div class="container">
            <table class="table">
                <thead>
                    <tr>
                        <th style="color: orange">Título</th>
                        <th style="color: orange">Descrição</th>
                        <th style="color: orange">Informações</th>
                        <c:if test="${usuarioLogado.tipo_user != null}">
                            <th style="color: orange">Ações</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    
                    <c:forEach var="e" items="${eventoList}">
                        
                        <tr>
                            <td><c:out value="${e.titulo}"/></td>
                            <td><c:out value="${e.descricao}"/></td>
                            <td><c:out value="${e.informacoes}"/></td>
                            <c:if test="${usuarioLogado.tipo_user != null}">
                            <td>
                                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/evento/update?id=${e.id}" >
                                    Editar
                                </a>
                                <a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/evento/delete?id=${e.id}" >
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
                    <a class="btn btn-warning" href="${pageContext.servletContext.contextPath}/welcome.jsp">Voltar</a>
                </div>
            </div>     
        </div>
    </body>
</html>