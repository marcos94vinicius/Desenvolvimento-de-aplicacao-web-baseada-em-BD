<%-- 
    Document   : update
    Created on : Nov 21, 2016, 7:19:39 PM
    Author     : vinicius
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Receber pagamento ou confirmar presença</title>
    </head>
    <body style = "background-color: #b9def0">
   
        <h2 style="margin-left: 23%" class="text" style="color: black" ><b>Altere o valor desejado</b></h2><br /><br />
        <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/inscricao/update">                    
            <input type="hidden" name="id" value="${i.id}">
            <div class = "form-group">
                <label class="control-label col-sm-3">Pagamento recebido?</label>
                <div  class="col-sm-5">
                    <select name="pagamento">
                        <option value='0' ${i.pagamento == 0 ? 'selected' : ''}>Não</option>
                        <option value='1' ${i.pagamento == 1 ? 'selected' : ''}>Sim</option>
                    </select>
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Compareceu ao evento?</label>
                <div  class="col-sm-5">
                    <select name="status">
                        <option value='0' ${i.status == 0 ? 'selected' : ''}>Não</option>
                        <option value='1' ${i.status == 1 ? 'selected' : ''}>Sim</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                    <button type="submit" class="btn btn-success">Atualizar</button>
                    <a style="margin-left: 37%" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/edicao">Voltar</a>
                </div>
            </div>          
        </form>                
    </body>
</html>