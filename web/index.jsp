<%-- 0
    Document   : index
    Created on : 22/09/2016, 18:25:40
    Author     : dskaster
--%>

<%@page contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Página inicial</title>
     
    </head>
    <body style = "background-color: #b9def0">
        <div style = "margin-top: 17%;">
            <a href = "${pageContext.servletContext.contextPath}/usuario/create" class="btn btn-primary btn-lg btn-block" type = "submit">
                Inserir novo usuário
            </a>
            <a href = "${pageContext.servletContext.contextPath}/usuario/login" class="btn btn-primary btn-lg btn-block">
                Fazer login
            </a>
            <a href = "${pageContext.servletContext.contextPath}/edicao/lista_edicoes" class="btn btn-primary btn-lg btn-block">
                Lista de eventos/edições disponíveis
            </a>
        </div>
    </body>
</html>
