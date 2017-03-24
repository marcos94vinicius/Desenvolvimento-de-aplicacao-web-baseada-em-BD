<%-- 
    Document   : index
    Created on : Nov 21, 2016, 4:55:47 PM
    Author     : vinicius
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.Inscricoes"%>
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
                        <th style="color: orange">Nome</th>
                        <th style="color: orange">Data da inscrição</th>
                        <th style="color: orange">Pagamento</th>
                        <th style="color: orange">Preço</th>
                        <th style="color: orange">Ações</th>
                    </tr>
                </thead>
                <tbody>                    
                    <c:forEach var="inc" items="${inscricoesList}">
                        
                        <tr>
                            <td><c:out value="${inc.nome}"/></td> 
                            <td><c:out value="${inc.data_inscricao}"/></td>
                            <td>
                                <c:if test="${inc.pagamento== 0}"> Pagamento não efetuado </c:if>
                                <c:if test="${inc.pagamento== 1}"> Pagamento efetuado </c:if>
                            </td>
                            <td><c:out value="${inc.preco}"/></td>
                            
                            <td>
                                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/inscricao/update?id=${inc.id_inscricao}" >
                                    Receber pagamento/Confirmar presença
                                </a>
                                <c:if test="${inc.pagamento == 1}">    
                                    <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/inscricoes/recibo?id=${inc.id_edicao}" >
                                        Gerar recibo
                                    </a>
                                </c:if>
                                <a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/inscricao/delete?id=${inc.id_inscricao}" >
                                    Cancelar inscrição
                                </a>
                            </td>
                        </tr>  

                    </c:forEach>
   
                </tbody>
            </table>  
                        
          <div class="form-group">
                <div class="col-sm-offset-12 col-sm-10">
                    <c:if test="${usuarioLogado.tipo_user != null}"> 
                        <a class="btn btn-warning" href="${pageContext.servletContext.contextPath}/edicao">Voltar</a>
                    </c:if> 
                </div>
            </div>     
        </div>

    </body>
</html>