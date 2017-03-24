<%-- 
    Document   : index
    Created on : Nov 4, 2016, 3:34:52 AM
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
                        <th style="color: orange">Renda total</th>
                        <th style="color: orange">Listar ordenado por:</th>
                        <th style="color: orange">Ações</th>
                    </tr>
                </thead>
                <tbody>                    
                    <c:forEach var="edicao" items="${edicaoList}">
                        
                        <tr>
                            <td><c:out value="${edicao.tituloev}"/></td> 
                            <td><c:out value="${edicao.ed}"/></td>
                            <td><c:out value="R$ ${edicao.renda}"/></td>
                            
                            <c:if test="${usuarioLogado.tipo_user != null}">
                                
                            <td>
                                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/inscricoes?id_edicao=${edicao.id}" >
                                    Nome
                                </a>
                                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/inscricoes2?id_edicao=${edicao.id}" >
                                    Data
                                </a>
                                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/inscricoes3?id_edicao=${edicao.id}" >
                                    Status
                                </a>
                            </td>
                            <td>
                                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/edicao/update?id=${edicao.id}" >
                                    Editar
                                </a>
                                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/inscricoes4?id_edicao=${edicao.id}" >
                                    Lista de convidados
                                </a>
                                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/estatisticas?id_edicao=${edicao.id}" >
                                    Estatísticas
                                </a>
                                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/estatisticas/multiplas" >
                                    Multiplas Est.
                                </a>
                                <a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/edicao/delete?id=${edicao.id}" >
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