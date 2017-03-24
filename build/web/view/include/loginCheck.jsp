<%-- 
    Document   : loginCheck
    Created on : 22/09/2016, 18:56:36
    Author     : dskaster
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty sessionScope.usuarioLogado}">
    <c:redirect context="${pageContext.servletContext.contextPath}" url="/"/>
</c:if>