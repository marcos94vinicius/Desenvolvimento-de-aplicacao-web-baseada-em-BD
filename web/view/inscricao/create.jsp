<%-- 
    Document   : create
    Created on : Nov 7, 2016, 4:55:11 PM
    Author     : vinicius
--%>

<%@page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Fazer inscrição</title>
    </head>
    <body style = "background-color: #b9def0">
   
        <h2 style="margin-left: 23%" class="text" style="color: black" ><b>Insira os dados do evento</b></h2><br /><br />
        <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/inscricao/create">                    

            <div class = "form-group">
                <label class="control-label col-sm-3">Vai acompanhado</label>
                <div  class="col-sm-5">
                    <select name="vai_com_outra_pessoa">
                        <option value='0' ${i.vai_com_outra_pessoa == 0 ? 'selected' : ''}>Não</option>
                        <option value='1' ${i.vai_com_outra_pessoa == 1 ? 'selected' : ''}>Sim</option>
                    </select>
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Como ficou sabedo?</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="ficou_sabendo" placeholder="como descobriu ?">
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Digite a data de hoje</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="data_inscricao" placeholder="aaaa-mm-dd">
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Formas de pagamento</label>
                <div  class="col-sm-5">
                    <select name="formas_pagamento">
                        <option value="Dinheirou ou cheuqe à vista">Dinheiro ou cheque a vista</option>
                        <option value="Cheque em 2X">Cheque em 2X</option>
                        <option value="Cheque em 2X">Cheque em 5X</option>
                        <option value="Cheque em 2X">Cheque em 7X</option>
                        <option value="Cheque em 2X">Cheque em 10X</option>
                    </select>
                </div>
            </div>
            <input type="hidden" name="id_usuario" value="${usuarioLogado.uid}">
            <div class = "form-group">
                <label class="control-label col-sm-3">Edição-evento</label>
                <div  class="col-sm-5">
                    <select name="id_edicao" >
                        <c:forEach var="ed" items="${edicaoList}">
                            <c:if test="${ed.status_inscricoes == 1}">
                                <option value="<c:out value="${ed.id}"/>"> <c:out value="${ed.tituloev} - ${ed.ed} -- R$ ${ed.preco}" /> </option>
                            </c:if>
                        </c:forEach>
                        
                    </select> 
                </div>
            </div>
                                          
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                    <button type="submit" class="btn btn-success">Criar</button>
                    <c:if test="${usuarioLogado.tipo_user != 3}">
                        <a style="margin-left: 37%" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/welcome.jsp">Voltar</a>
                    </c:if>
                    <c:if test="${usuarioLogado.tipo_user == 3}">
                        <a style="margin-left: 37%" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/welcomePart.jsp">Voltar</a>
                    </c:if>
                </div>
            </div>          
        </form>                
    </body>
</html>
