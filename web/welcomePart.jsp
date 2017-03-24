<%-- 
    Document   : welcome
    Created on : 22/09/2016, 18:31:28
    Author     : dskaster
--%>
<%@include file="/view/include/loginCheck.jsp"%>

<%@page contentType="text/html"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>JSP Page</title>
    </head>
    <body style="background-color: #b9def0">
        <img src="${pageContext.servletContext.contextPath}/foto?id=${usuarioLogado.uid}" height="200" width="300"/>
        <div class="container" style = "margin-top: 10%">
            <h1 style="margin-left: 25%"> <b>Bem-vindo, <c:out value="${usuarioLogado.nome}"/>!</b></h1>
                        
            <p>
            <div class="form-group" style = "margin-top: 3%" >
                <div class="col-sm-offset-3 col-sm-7">
                    <a class="btn btn-success btn-lg" href="${pageContext.servletContext.contextPath}/usuario/update?id=${usuarioLogado.uid}">Atualizar dados</a>
                    <a class="btn btn-success btn-lg" href="${pageContext.servletContext.contextPath}/inscricao/create">Inscrever no evento</a>
                    <a class="btn btn-success btn-lg" href="${pageContext.servletContext.contextPath}/inscricao/inscricaoEdicao?uid=${usuarioLogado.uid}">Eventos que participo</a>
                    <a class="btn btn-danger btn-lg " href="${pageContext.servletContext.contextPath}/logout">Logout</a>
                </div>
            </div>
            </p>
        </div>
    </body>
</html>
