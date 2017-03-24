<%-- 
    Document   : foto
    Created on : Jan 4, 2017, 11:04:16 PM
    Author     : vinicius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <img src="${pageContext.servletContext.contextPath}/foto?id=${usuarioLogado.uid}" height="200" width="300"/>
    </body>
</html>
