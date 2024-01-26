<%-- 
    Document   : login
    Created on : 13/08/2015, 23:45:25
    Author     : Lucas
--%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
            <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
            <meta name="description" content="">
            <meta name="author" content="">
            <link rel="icon" href="../../favicon.ico">

            <title>Play Damas</title>
            <link rel="stylesheet" href="Index.css">
            <!-- Bootstrap core CSS -->
            <link href="bootstrap.css" rel="stylesheet">


            <!-- Custom styles for this template -->
            <link href="Index.css" rel="stylesheet">

            <script src="Index.js"></script>

            <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
            <!-- <script src="ie-emulation-modes-warning.js"></script> -->
            <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->


            <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
            <!--[if lt IE 9]>
              <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
              <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
            <![endif]-->

            <script>
                <% String feedback = (String) request.getAttribute("feedback");%>
            </script>
            <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

           

        </head>

        <body>


            <nav class="navbar navbar-inverse navbar-fixed-top">

                <div class="container">

                    <div id="navbar" class="navbar-collapse collapse">
                        <div class="navbar-form navbar-right">
                            <div class="form-group">
                                <input id="id" name="id" type="text" placeholder="Usuário" class="form-control">
                            </div>
                            <div class="form-group">
                                <input id="pass" name="pass" type="password" placeholder="Senha" class="form-control">
                            </div>
                            <button onclick="login()" class="btn btn-success">Entrar</button>
                        </div>
                    </div><!--/.navbar-collapse -->
                </div>
            </nav>

            <div class="jumbotron">
                <div class="ljumbotron">
                    <div class="container">  

                        <h1>Bem vindo!</h1>
                        <br>
                        <p><a class="btn btn-primary btn-lg" onclick="visitor()" >Jogar agora &raquo;</a></p>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="form-signin">
                    <div>
                        <h2 class="form-signin-heading">Crie sua conta</h2>
                        <label for="inputEmail" class="sr-only">Nome de usuário</label>
                        <input name="new_account" type="text" id="new_account" class="form-control" placeholder="Nome de Usuário" required autofocus>
                        <label for="inputPassword" class="sr-only">Senha</label>
                        <input name="new_pass" type="password" id="new_pass" class="form-control" placeholder="Senha" required>
                        <label for="inputPassword" class="sr-only">Repita a senha</label>
                        <input name="confirm_pass" type="password" id="confirm_pass" class="form-control" placeholder="Repita a senha" required>

                        <button class="btn btn-lg btn-primary btn-block" onclick="createAccount()">Criar conta</button>
                    </div>                    
                    <div id="feedback" >
                        <%=feedback%>
                    </div>
                </div>
            </div> <!-- /container -->



        </body>
    </html>
