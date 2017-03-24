<%-- 
    Document   : index
    Created on : Dec 20, 2016, 1:56:37 PM
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
        
        <div class="container" style = "margin-top: 1%">
            
            <p>
                
            <div class="form-group" style = "margin-top: 1%" >
                <div class="col-sm-offset-0 col-sm-12">
                    <a class="btn btn-info btn-lg" href="${pageContext.servletContext.contextPath}/estatisticas/total?id=${estatisticas.edicao_id}">Total</a><br></br>
                    <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/estatisticas/total_sexo?id_edicao=${estatisticas.edicao_id}">                    
                        <div class = "form-group">
                            <div  class="col-sm-5">
                                <button type="submit" class="btn btn-info btn-lg">Total por sexo</button>
                                <select name="sexo">
                                    <option value="Masculino">Masculino</option>
                                    <option value="Feminino">Feminino</option>
                                </select>
                            </div>
                        </div>                     
                    </form>
                    <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/estatisticas/total_idade?id_edicao=${estatisticas.edicao_id}">                    
                        <table>
                            <tr>
                                <td><button type="submit" class="btn btn-info btn-lg">Total por faixa etária</button></td>
                                <td><label class="control-label col-sm-5">Idade mínima</label></td>
                                <td><input class="form-control" type="text" name="id_min" placeholder="0"> <br /></td>
                                <td><label class="control-label col-sm-5">Idade máxima</label></td>
                                <td><input class="form-control" type="text" name="id_max" placeholder="100"> <br /></td>
                            </tr>
                        </table>
                    </form></br>
                        
                    <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/estatisticas/combinacao?id_edicao=${estatisticas.edicao_id}">                    
                        <table>
                            <tr>
                                <td><button type="submit" class="btn btn-info btn-lg">Total por combinação</button></td>
                                <td><label class="control-label col-sm-5">Idade mínima</label></td>
                                <td><input class="form-control" type="text" name="id_min" placeholder="0"> <br /></td>
                                <td><label class="control-label col-sm-5">Idade máxima</label></td>
                                <td><input class="form-control" type="text" name="id_max" placeholder="100"> <br /></td>
                                <td>Escolha o sexo: </td>
                                <td>
                                    <select name="sexo">
                                        <option value="Masculino">Masculino</option>
                                        <option value="Feminino">Feminino</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </form></br>
                    
                    <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/estatisticas/instituicao?id_edicao=${estatisticas.edicao_id}">                    
                        <table>
                            <tr>
                                <td><button type="submit" class="btn btn-info btn-lg">Total por instituição de origem</button></td>
                                <td><label class="control-label col-sm-5">Nome da instituição</label></td>
                                <td><input class="form-control" type="text" name="instituicao" placeholder="UEL"> <br /></td>
                            </tr>
                        </table>
                    </form></br>
                    
                </div></br>                   
                    
                    
                   
                    
                <a class="btn btn-warning btn-lg" href="${pageContext.servletContext.contextPath}/edicao">Voltar</a>
            </div>
            </p>
        </div>

    </body>
</html>