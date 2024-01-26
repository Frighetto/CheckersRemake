<!DOCTYPE html>
<html lang="pt" style="height: 100%">
    <head>
        <title>Play Damas</title>
        <meta charset="UTF-8">
        <meta name="application-name" content="Play Damas">
        <meta name="description" content="Checkers game with real players and bots">
        <meta name="keywords" content="Players, Checkers">
        <meta name="author" content="Lucas Fernando Frighetto">
        <meta name="viewport" content="width=device-width, initial-scale=0.5, maximum-scale=0.5">
        <link rel="icon" href="icon.png">                    
        <link href="bootstrap.css" rel="stylesheet">        
        <link href="boardgame.css" rel="stylesheet"> 
        <link href="contacts.css" rel="stylesheet"> 
        <style>
            .flex-centralizado {
                display: flex;
                justify-content: center;
            }             
        </style>
    </head>
    <body style="overflow-y: none; background-color: #eee; min-height: 100%; margin: 0; overflow: hidden">            
        <nav id="top-bar" class="navbar navbar-inverse navbar-fixed-top">        
            <div class="container">            
                <div id="navbar" class="navbar-collapse collapse">                                           
                    <div class="navbar-form navbar-right form-group">                        
                        <form action="LogOut" method="POST">
                            <button id="exit" name="secret_identifier" type="submit" class="btn btn-default">Sair</button>
                        </form> 
                    </div>
                </div>
            </div>
        </nav>       
        <div class="flex-centralizado">
            <div id="boardgame">
            </div>                   
            <div id="players">
                <div id="game-panel" style="width: 100%; background-color: red;">                    
                    <div id="players-panel">                                                                        
                    </div>                              
                    <div class="flex-centralizado">     
                        <form action="Lobby" method="POST">
                            <button id="lobby" name="secret_identifier" type="submit" style="width: 100px; margin: 1%; font-size: Large" class="btn btn-default">Lobby</button>
                        </form>                                              
                    </div>            
                </div>                               
                <div id="tabs" class="tab">
                    <button class="tablinks active" onclick="tab(event, 'Jogadores')">Jogadores</button>
                    <button class="tablinks" onclick="tab(event, 'Salvos')">Salvos</button>
                    <button class="tablinks" onclick="tab(event, 'Deletados')">Deletados</button>
                </div>
                
                <div id="Jogadores" class="tabcontent" style="display: block; overflow-y: scroll;">                
                    <table id="game-panel" class="table">
                        <tbody id="Jogadores_tbody">                            
                        </tbody>                    
                    </table>
                </div>

                <div id="Salvos" class="tabcontent" style="display: none; overflow-y: scroll; margin: 0; padding: 0;">
                    <table class="table">   
                        <thead style="position: sticky; top: 0; margin: 0; padding: 0; background-color: #eee">
                            <tr id="opt_salvos">
                                <td style="text-align: center; vertical-align: middle; width: 50%; font-size: Large">
                                    <a onclick="exibir_salvos_adicionar()" style="color: black; cursor: pointer;">
                                        <u>salvar (+)</u>
                                    </a>
                                </td>
                                <td style="text-align: center; vertical-align: middle; width: 50%; font-size: Large">
                                    <a onclick="exibir_salvos_remover()" style="color: black; cursor: pointer;">
                                        <u>remover (-)</u>
                                    </a>
                                </td>
                            </tr>
                            <tr id="salvos_adicionar" style="display: none">
                                <td style="text-align: center; vertical-align: middle; width: 50%; font-size: Large">
                                    <input id="input_salvos_adicionar" maxlength="25"/>
                                </td>
                                <td style="text-align: center; vertical-align: middle; width: 50%; font-size: Large">
                                    <a onclick="salvos_adicionar()" style="color: black; cursor: pointer;">
                                        <u>salvar</u>
                                    </a>
                                </td>
                            </tr>
                            <tr id="salvos_remover" style="display: none">
                                <td style="text-align: center; vertical-align: middle; width: 50%; font-size: Large">
                                    <input id="input_salvos_remover" maxlength="25"/>
                                </td>
                                <td style="text-align: center; vertical-align: middle; width: 50%; font-size: Large">
                                    <a onclick="salvos_remover()" style="color: black; cursor: pointer;">
                                        <u>remover</u>
                                    </a>
                                </td>
                            </tr>
                        </thead>                     
                        <tbody id="Salvos_tbody">                                                 
                        </tbody>                    
                    </table>
                </div>

                <div id="Deletados" class="tabcontent" style="overflow-y: scroll; margin: 0; padding: 0;">
                    <table class="table">
                        <thead style="position: sticky; top: 0; margin: 0; padding: 0; background-color: #eee">
                            <tr id="opt_deletados">
                                <td style="text-align: center; vertical-align: middle; width: 50%; font-size: Large">
                                    <a onclick="exibir_deletados_adicionar()" style="color: black; cursor: pointer;">
                                        <u>deletar (+)</u>
                                    </a>
                                </td>
                                <td style="text-align: center; vertical-align: middle; width: 50%; font-size: Large">
                                    <a onclick="exibir_deletados_remover()" style="color: black; cursor: pointer;">
                                        <u>remover (-)</u>
                                    </a>
                                </td>
                            </tr>
                            <tr id="deletados_adicionar" style="display: none">
                                <td style="text-align: center; vertical-align: middle; width: 50%; font-size: Large">
                                    <input id="input_deletados_adicionar" maxlength="25"/>
                                </td>
                                <td style="text-align: center; vertical-align: middle; width: 50%; font-size: Large">
                                    <a onclick="deletados_adicionar()" style="color: black; cursor: pointer;">
                                        <u>deletar</u>
                                    </a>
                                </td>
                            </tr>
                            <tr id="deletados_remover" style="display: none">
                                <td style="text-align: center; vertical-align: middle; width: 50%; font-size: Large">
                                    <input id="input_deletados_remover" maxlength="25"/>
                                </td>
                                <td style="text-align: center; vertical-align: middle; width: 50%; font-size: Large">
                                    <a onclick="deletados_remover()" style="color: black; cursor: pointer;">
                                        <u>remover</u>
                                    </a>
                                </td>
                            </tr>
                        </thead>                
                        <tbody id="Deletados_tbody">                                            
                        </tbody>
                    </table>
                </div>
                
                <div id="chat" style="width: 100%;">
                    <textarea id="chat-messages" readonly="readonly" style="background-color: white; width: 100%; display:block; resize: none; font-size: Large">Bem vindo!</textarea>
                    <input id="chat-input" onkeydown="check_enter_keypress('Game')" style="width: 100%; font-size: Large" maxlength="92">
                </div>
            </div> 
        </div>        
        
        <script src="layout.js"></script>
        <script src="game.js"></script>
        <script src="contacts.js"></script>
        <script>                                                           		            
            const secret_identifier = '<%= request.getParameter("secret_identifier") %>';                 
            document.getElementById("lobby").setAttribute("value", secret_identifier);
            document.getElementById("exit").setAttribute("value", secret_identifier);
            request();
            setInterval(()=> { request(); }, 3141);            
        </script>
    </body>
</html>
