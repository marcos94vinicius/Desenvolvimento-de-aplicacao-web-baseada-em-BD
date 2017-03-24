<%-- 
    Document   : insereUsuario
    Created on : Sep 30, 2016, 3:37:27 PM
    Author     : vinicius
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Dados usuário</title>
    </head>
    <body style = "background-color: #b9def0">
   
        <h2 style="margin-left: 23%" class="text" style="color: black" ><b>Altere os dados desejados</b></h2><br /><br />
        <form class="form-horizontal" name="formUsuario" method="POST" action="${pageContext.servletContext.contextPath}/usuario/update">                    
            <input type="hidden" name="id" value="${u.uid}">
            <div class = "form-group">
                <label class="control-label col-sm-3">Login</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="login" value="${u.login}" placeholder="login" disabled>
                </div>  
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Senha</label>
                <div  class="col-sm-5">
                    <input type="password" class="form-control" id="inputPassword3" name="senha" placeholder="Password" disabled>
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Nome</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="nome" value="${u.nome}" placeholder="nome" disabled>
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">CPF</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="cpf" value="${u.cpf}" placeholder="cpf" disabled>
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">RG</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="rg" value="${u.rg}" placeholder="rg" disabled>
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Nome no cracha</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="cracha" value="${u.cracha}" placeholder="cracha" disabled>
                </div>
            </div>  
            <div class = "form-group">
                <label class="control-label col-sm-3">E-mail</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="e_mail" value="${u.e_mail}" placeholder="e-mail" disabled>
                </div>
            </div>  
            <div class = "form-group">
                <label class="control-label col-sm-3">Data de nascimento</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="dt_nasc" value="${u.dt_nasc}" placeholder="aaaa-mm-dd" disabled>
                </div>
            </div>  
            <div class = "form-group">
                <label class="control-label col-sm-3">Estado Civil</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="estado_civil" value="${u.estado_civil}" placeholder="estado cilvil" disabled>
                </div>
            </div> 
             <div class = "form-group">
                <label class="control-label col-sm-3">Escolaridade</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="escolaridade" value="${u.escolaridade}" placeholder="escolaridade" disabled>
                </div>
            </div> 
             <div class = "form-group">
                <label class="control-label col-sm-3">Profissão</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="profissao" value="${u.profissao}" placeholder="profissao" disabled>
                </div>
            </div> 
             <div class = "form-group">
                <label class="control-label col-sm-3">Instituição de origem</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="instituicao_origem" value="${u.instituicao_origem}" placeholder="instituição de origem" disabled>
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">CEP</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="cep" value="${u.cep}" 
                           onblur="pesquisacep(this.value);" placeholder="cep" disabled>
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Logradouro</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="logradouro" value="${u.logradouro}" placeholder="logradouro" disabled>
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Complemento</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="complemento" value="${u.complemento}" placeholder="complemento" disabled> 
                </div>
            </div>  
             <div class = "form-group">
                <label class="control-label col-sm-3">Bairro</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="bairro" value="${u.bairro}" placeholder="bairro" disabled>
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Cidade</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="cidade" value="${u.cidade}" placeholder="cidade" disabled>
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Estado</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="estado" value="${u.estado}" placeholder="estado" disabled>
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Fone tipo</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="tel_tipo" value="${u.tel_tipo}" placeholder="tel_tipo" disabled>
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">DDD</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="ddd" value="${u.ddd}" placeholder="ddd" disabled>
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Número do telefone</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="tel_numero" value="${u.tel_numero}" placeholder="tel_numero" disabled>
                </div>
            </div>                                   
            <div class="form-group">
                <div class="col-sm-offset-7 col-sm-10">
                    <a  class="btn btn-warning" href="${pageContext.servletContext.contextPath}/usuario">Voltar</a>
                </div>
            </div>          
        </form>                
    </body>
</html>

