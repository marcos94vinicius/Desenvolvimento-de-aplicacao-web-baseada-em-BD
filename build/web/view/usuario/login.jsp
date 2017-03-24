<%-- 
    Document   : login
    Created on : Oct 1, 2016, 5:34:30 PM
    Author     : vinicius
--%>

<%@page contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/ht<ml; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interfaces/bootstrap/css/bootstrap.css">
        <title>Página de login</title>
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
                        FB.api('/me?fields=id', function (response) {
                            var x = new XMLHttpRequest();

                            x.onreadystatechange = function () {
                                if (x.readyState === 4 && x.status === 200) {
                                    
                                    location.href = "${pageContext.servletContext.contextPath}/welcome.jsp";
                                    document.getElementById("botao").disabled = "true";
                                    


                                }
                            };
                            x.open("post", "${pageContext.servletContext.contextPath}/login?ll=loginFB&id=" + response.id, false, null, null);
                            x.send(null);

                        });
                    } else {
                        window.alert("login não realizado");
                        location.reload();
                    }
                });
            }
        </script>
    </head>
    <body style="background: #b9def0">
        <div style="background: #008B8B">
            <div class="form" style = "margin-top: 15%">
                <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/login" method="POST">
                    <div class="form-group">
                        <label class="control-label col-sm-4">Login/Usuário</label>
                        <div  class="col-sm-4">
                            <input type="text" class="form-control" name="login" placeholder="login name" required autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">Senha</label>
                        <div  class="col-sm-4">
                            <input type="password" class="form-control" name="senha" placeholder="senha" required   >
                        </div>
                    </div> 
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-10">
                            <button type="submit" id="botao" class="btn btn-success btn-lg">Login</button>
                            <a style="margin-left: 24%" class="btn btn-warning btn-lg" href="${pageContext.servletContext.contextPath}/index.jsp">Voltar</a>
                        </div>
                    </div>  
                </form>
                <fb:login-button scope="public_profile" onlogin="pegaDados()">
                    Logar com Facebook
                </fb:login-button>
            </div>
        </div>
    </body>
</html>
