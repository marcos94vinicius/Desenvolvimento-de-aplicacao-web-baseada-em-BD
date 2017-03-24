<%-- 
    Document   : multiplas
    Created on : Jan 7, 2017, 1:55:51 PM
    Author     : vinicius
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.Edicao"%>
<%@page contentType="text/html"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Edições</title>
    </head>
    <body style="background-color: #b9def0">

        <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/estatisticas/index2"> 
        <div class="container">
            <table class="table">
                <thead>
                    <tr>
                        <th style="color: orange">Evento</th>
                        <th style="color: orange">Edição</th>
                        <th style="color: orange">Marque os eventos que você deseja</th>
                    </tr>
                </thead>
                <tbody>                    
                    <c:set var="i" value="1" />
                    <c:forEach var="edi" items="${edi}">
                        
                        <tr>
                            <td><c:out value="${edi.tituloev}"/></td> 
                            <td><c:out value="${edi.ed}"/></td>
                            <td><input type="checkbox" name="escolha_${i}" value="${edi.id}"><br></td>             
                        </tr>  
                        <c:set var="i" value="${i+1}" />
                    </c:forEach>
                    <input type="hidden" name="quantidade_eventos" value="${i}">
                </tbody>
            </table>
                
            <div class = "form-group">
                <div  class="col-sm-5">
                    <button type="submit" class="btn btn-info btn-lg" name="b0" value="1">Total</button>
                </div>
            </div>  
                
            <div class = "form-group">
                <div  class="col-sm-5">
                    <button type="submit" class="btn btn-info btn-lg" name="b1" value="1">Total por sexo</button>
                    <select name="sexo">
                        <option value="Masculino">Masculino</option>
                        <option value="Feminino">Feminino</option>
                    </select>
                </div>
            </div>  
                
            <table>
                <tr>
                    <td><button type="submit" class="btn btn-info btn-lg" name="b2" value="1">Total por faixa etária</button></td>
                    <td><label class="control-label col-sm-5">Idade mínima</label></td>
                    <td><input class="form-control" type="text" name="id_min" placeholder="0"> <br /></td>
                    <td><label class="control-label col-sm-5">Idade máxima</label></td>
                    <td><input class="form-control" type="text" name="id_max" placeholder="100"> <br /></td>
                </tr>
            </table></br>
                
            <table>
                <tr>
                    <td><button type="submit" class="btn btn-info btn-lg" name="b3" value="1">Total por combinação</button></td>
                    <td><label class="control-label col-sm-5">Idade mínima</label></td>
                    <td><input class="form-control" type="text" name="id_min2" placeholder="0"> <br /></td>
                    <td><label class="control-label col-sm-5">Idade máxima</label></td>
                    <td><input class="form-control" type="text" name="id_max2" placeholder="100"> <br /></td>
                    <td>Escolha o sexo: </td>
                    <td>
                        <select name="sexo2">
                            <option value="Masculino">Masculino</option>
                            <option value="Feminino">Feminino</option>
                        </select>
                    </td>
                </tr>
            </table></br>
            
            <table>
                <tr>
                    <td><button type="submit" class="btn btn-info btn-lg" name="b4" value="1">Total por instituição de origem</button></td>
                    <td><label class="control-label col-sm-5">Nome da instituição</label></td>
                    <td><input class="form-control" type="text" name="instituicao" placeholder="UEL"> <br /></td>
                </tr>
            </table>
            
        </div>
        </form>

    </body>
</html>
