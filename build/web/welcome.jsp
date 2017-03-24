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
        <div class="container" style = "margin-top: 2%">
            <h1 style="margin-left: 30%"> <b>Bem-vindo, <c:out value="${usuarioLogado.nome}"/>!</b></h1>
            
            <p>
            <div class="form-group" style = "margin-top: 3%" >
                <div class="col-sm-offset-1 col-sm-12">
                    <c:if test="${usuarioLogado.tipo_user != 3}">    
                        <a class="btn btn-info btn-lg" href="${pageContext.servletContext.contextPath}/usuario">Cadastro de Usuários</a>
                        <a class="btn btn-info btn-lg" href="${pageContext.servletContext.contextPath}/evento/lista_eventos">Cadastro de eventos</a>
                        <a class="btn btn-info btn-lg" href="${pageContext.servletContext.contextPath}/entidade_promotora">Cadastro de entidades</a>
                        <a class="btn btn-info btn-lg" href="${pageContext.servletContext.contextPath}/localizacao">Cadastro de localizações</a>
                        <a class="btn btn-info btn-lg" href="${pageContext.servletContext.contextPath}/edicao">Cadastro de edições</a><br></br>
                        <a class="btn btn-success btn-lg" href="${pageContext.servletContext.contextPath}/usuario/update?id=${usuarioLogado.uid}">Atualizar dados</a>
                        <a class="btn btn-success btn-lg" href="${pageContext.servletContext.contextPath}/evento/create">Criar Evento</a>
                        <a class="btn btn-success btn-lg" href="${pageContext.servletContext.contextPath}/entidade_promotora/create">Cadastrar Entidade Promotora</a>
                        <a class="btn btn-success btn-lg" href="${pageContext.servletContext.contextPath}/localizacao/create">Cadastrar localização</a>
                        <a class="btn btn-success btn-lg" href="${pageContext.servletContext.contextPath}/edicao/create">Criar Edição</a><br></br>
                        <a class="btn btn-success btn-lg" href="${pageContext.servletContext.contextPath}/inscricao/create">Inscrever no evento</a>
                        <a class="btn btn-success btn-lg" href="${pageContext.servletContext.contextPath}/inscricao/inscricaoEdicao?uid=${usuarioLogado.uid}">Eventos que participo</a>
                        <a class="btn btn-success btn-lg" href="${pageContext.servletContext.contextPath}/edicao/pesquisar">Pesquisar evento</a>
                        <a class="btn btn-success btn-lg" href="${pageContext.servletContext.contextPath}/inscricao/bloco">Importar inscrições em bloco</a>
                        <a class="btn btn-danger btn-lg " href="${pageContext.servletContext.contextPath}/logout">Logout</a>
                    </c:if>
                    <c:if test="${usuarioLogado.tipo_user == 3}">  
                        <a class="btn btn-success btn-lg" href="${pageContext.servletContext.contextPath}/usuario/update?id=${usuarioLogado.uid}">Atualizar dados</a>
                        <a class="btn btn-success btn-lg" href="${pageContext.servletContext.contextPath}/inscricao/create">Inscrever no evento</a>
                        <a class="btn btn-success btn-lg" href="${pageContext.servletContext.contextPath}/inscricao/inscricaoEdicao?uid=${usuarioLogado.uid}">Eventos que participo</a>
                        <a class="btn btn-danger btn-lg " href="${pageContext.servletContext.contextPath}/logout">Logout</a>
                    </c:if>
                </div>
            </div>
            </p>
        </div>
    </body>
</html>
