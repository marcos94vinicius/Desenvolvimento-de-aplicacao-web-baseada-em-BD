<%-- 
    Document   : create
    Created on : Nov 3, 2016, 10:01:07 PM
    Author     : vinicius
--%>

<%@page contentType="text/html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/localiza_cep.js"></script>
        <title>Inserir localização</title>
    </head>
    <body style = "background-color: #b9def0">
   
        <h2 style="margin-left: 23%" class="text" style="color: black" ><b>Por favor, preencha os campos abaixo</b></h2><br /><br />
        <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/localizacao/create">                    

            <div class = "form-group">
                <label class="control-label col-sm-3">Nome</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="nome" value="${l.nome}" placeholder="nome"required>
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">CEP</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="cep" value="${l.cep}" onblur="pesquisacep(this.value);" placeholder="cep" id="cep">
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Logradouro</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="logradouro" value="${l.logradouro}" placeholder="logradouro" id="rua">
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Complemento</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="complemento" value="${l.complemento}" placeholder="complemento">
                </div>
            </div>  
             <div class = "form-group">
                <label class="control-label col-sm-3">Bairro</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="bairro" value="${l.bairro}" placeholder="bairro" id="bairro">
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Cidade</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="cidade" value="${l.cidade}" placeholder="cidade" id="cidade">
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">Estado</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="estado" value="${l.estado}" placeholder="estado" id="uf">
                </div>
            </div>
             <div class = "form-group">
                <label class="control-label col-sm-3">DDD</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="ddd" value="${l.ddd}" placeholder="ddd">
                </div>
            </div>
            <div class = "form-group">
                <label class="control-label col-sm-3">Número do telefone</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="tel_numero" value="${l.tel_numero}" placeholder="tel_numero">
                </div>
            </div>   
            <div class = "form-group">
                <label class="control-label col-sm-3">Coordenadas Geográficas</label>
                <div  class="col-sm-5">
                    <input class="form-control" type="text" name="coordenadas" value="${l.coordenadas}" placeholder="coordenadas">
                </div>
            </div>   
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                    <button type="submit" class="btn btn-success">Inserir</button>        
                </div>
            </div>          
        </form>                
    </body>
</html>