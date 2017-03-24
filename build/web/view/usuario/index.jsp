<%-- 
    Document   : index
    Created on : 19/09/2016, 10:59:46
    Author     : dskaster
--%>
<%@include file="/view/include/loginCheck.jsp"%>

<%@page import="java.util.List"%>
<%@page import="model.Usuario"%>
<%@page contentType="text/html"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Usuários</title>
    </head>
    <body style="background-color: #b9def0">

        <div class="container">
            <table class="table">
                <thead>
                    <th>Login</th>
                    <th>Nome</th>
                    <th>Ações</th>
                </thead>
                <tbody>
                    
                <c:if test="${usuarioLogado.tipo_user == 1}" >
                    <c:forEach var="u" items="${usuarioList}">
                        <c:if test="${u.tipo_user != usuarioLogado.tipo_user}" >
                        <tr>
                            <td><c:out value="${u.login}"/></td>
                            <td><a href="${pageContext.servletContext.contextPath}/usuario/lista_usuarios?id=${u.uid}"> <c:out value="${u.nome}"/></a></td>
                            <td>
                            
                                <a class="btn btn-info" href="${pageContext.servletContext.contextPath}/usuario/update?id=${u.uid}" >
                                    Editar
                                </a>
                                <a class="btn btn-danger link_excluir_usuario" href="${pageContext.servletContext.contextPath}/usuario/delete?id=${u.uid}">
                                    Excluir
                                </a>
                                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/inscricao/create2?id=${u.uid}">
                                    Inscrever em evento
                                </a> 
                            </td>
                        </tr>  
                        </c:if>
                    </c:forEach>
                </c:if>  
                <c:if test="${usuarioLogado.tipo_user == 2}" >

                    <c:forEach var="u" items="${usuarioList}">
                        <c:if test="${(u.tipo_user!=1) && (usuarioLogado.tipo_user!=u.tipo_user)}" >
                        <tr>
                            <td><c:out value="${u.login}"/></td>
                            <td><a href="${pageContext.servletContext.contextPath}/usuario/lista_usuarios?id=${u.uid}"><c:out value="${u.nome}"/></a></td>
                            <td>
                            
                                <a class="btn btn-info" href="${pageContext.servletContext.contextPath}/usuario/update?id=${u.uid}" >
                                    Editar
                                </a>
                            </td>
                        </tr>                    
                        </c:if>
                    </c:forEach>
                </c:if>         
                </tbody>
            </table>  
                  <div class="form-group">
                <div class=" col-sm-10">
                    <a  class="btn btn-success" href="${pageContext.servletContext.contextPath}/usuario/create">
                         Inserir novo usuário
                    </a> 
                    <a style="margin-left: 37%" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/welcome.jsp">Voltar</a>
                </div>
            </div>                       

        </div>

    </body>
</html>
