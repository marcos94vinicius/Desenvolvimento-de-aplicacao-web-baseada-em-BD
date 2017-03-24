<%-- 
    Document   : insereUsuario
    Created on : Sep 30, 2016, 3:37:27 PM
    Author     : vinicius
--%>

<%@page contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/localiza_cep.js"></script>
        <title>Inserir Usuário</title>
        <script>
            window.fbAsyncInit = function () {
                FB.init({
                    appId: '1725829377442987',
                    xfbml: true,
                    cookie: true,
                    version: 'v2.8'
                });
                FB.AppEvents.logPageView();
            };

            (function (d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id)) {
                    return;
                }
                js = d.createElement(s);
                js.id = id;
                js.src = "//connect.facebook.net/en_US/sdk.js";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));
        </script>  
        <script>
            function pegaDados() {

                FB.getLoginStatus(function (response1) {
                    if (response1.status === 'connected') {
                        FB.api('/me?fields=id,name,email', function (response) {
                            document.getElementById("nome").value=response.name;
                            document.getElementById("email").value=response.email;
                            document.getElementById("id").value=response.id;
                        });
                    } else {
                        window.alert("login não realizado");
                        location.reload();
                    }
                });
            }
        </script>
    </head>
    <body style = "background-color: #b9def0">
   
        <h2 style="margin-left: 23%" class="text" style="color: black" ><b>Por favor, preencha os campos abaixo</b></h2><br /><br />
        
        <div class="col-sm-offset-3 col-sm-12">
            <fb:login-button scope="public_profile,email" onlogin="pegaDados()">
                Cadastrar com Facebook
            </fb:login-button>
        </div></br></br>
        <form class="form-horizontal" name="formUsuario" method="POST" enctype="multipart/form-data" action="${pageContext.servletContext.contextPath}/usuario/create">                    
            <div class="col-sm-offset-3 col-sm-12">
                <input type="hidden" id="id" name="id"/>
                <label>Foto</label></br>
                <input type="file" name="foto" accept="image/*"/>
            </div></br></br></br>
            <div class = "form-group">
                <label class="control-label col-sm-3">Login</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="login" value="${u.login}" placeholder="login"required autofocus>
                </div>  
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Senha</label>
                <div  class="col-sm-5">
                    <input type="password" class="form-control" id="inputPassword3" name="senha" placeholder="Password" required>
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Nome</label>
                <div  class="col-sm-5">
                    <input class="form-control" id="nome" type="text" name="nome" value="${u.nome}" placeholder="nome"required>
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">CPF</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="cpf" value="${u.cpf}" placeholder="cpf">
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">RG</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="rg" value="${u.rg}" placeholder="rg">
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Nome no cracha</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="cracha" value="${u.cracha}" placeholder="cracha">
                </div>
            </div>  
            <div class = "form-group">
                <label class="control-label col-sm-3">E-mail</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" id="email" name="e_mail" value="${u.e_mail}" placeholder="e-mail">
                </div>
            </div>  
            <div class = "form-group">
                <label class="control-label col-sm-3">Data de nascimento</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="dt_nasc" value="${u.dt_nasc}" placeholder="aaaa-mm-dd">
                </div>
            </div>  
            <div class = "form-group">
                <label class="control-label col-sm-3">Estado Civil</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="estado_civil" value="${u.estado_civil}" placeholder="estado cilvil">
                </div>
            </div> 
             <div class = "form-group">
                <label class="control-label col-sm-3">Escolaridade</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="escolaridade" value="${u.escolaridade}" placeholder="escolaridade">
                </div>
            </div> 
             <div class = "form-group">
                <label class="control-label col-sm-3">Profissão</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="profissao" value="${u.profissao}" placeholder="profissao">
                </div>
            </div> 
             <div class = "form-group">
                <label class="control-label col-sm-3">Instituição de origem</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="instituicao_origem" value="${u.instituicao_origem}" placeholder="instituição de origem">
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">CEP</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="cep" value="${u.cep}" onblur="pesquisacep(this.value);" placeholder="cep" id="cep">
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Logradouro</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="logradouro" value="${u.logradouro}" placeholder="logradouro" id="rua">
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Complemento</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="complemento" value="${u.complemento}" placeholder="complemento">
                </div>
            </div>  
             <div class = "form-group">
                <label class="control-label col-sm-3">Bairro</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="bairro" value="${u.bairro}" placeholder="bairro" id="bairro">
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Cidade</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="cidade" value="${u.cidade}" placeholder="cidade" id="cidade">
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Estado</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="estado" value="${u.estado}" placeholder="estado" id="uf">
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Fone tipo</label>
                <div  class="col-sm-5">
                    <select name="tel_tipo">
                        <option value="residencial" selected>Residencial</option>
                        <option value="celular">Celular</option>
                        <option value="comercial">Comercial</option>
                        <option value="recado">Recado</option>
                    </select> 
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">DDD</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="ddd" value="${u.ddd}" placeholder="ddd">
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Número do telefone</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="tel_numero" value="${u.tel_numero}" placeholder="tel_numero">
                </div>
            </div>   
            <div class = "form-group">
                <label class="control-label col-sm-3">Idade</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="idade" value="${u.idade}" placeholder="idade">
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Sexo</label>
                <div  class="col-sm-5">
                    <select name="sexo">
                        <option value="Masculino">Masculino</option>
                        <option value="Feminino">Feminino</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                    <button type="submit" class="btn btn-success">Inserir</button>
                    <%--<c:if test="${u.tipo_user != null}">
                        <a style="margin-left: 37%" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/usuario">Voltar</a>
                    </c:if>
                    <c:if test="${u.tipo_user == null}">
                        <a style="margin-left: 37%" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/index.jsp">Voltar</a>
                    </c:if>--%>              
                </div>
            </div>          
        </form>                
    </body>
</html>

