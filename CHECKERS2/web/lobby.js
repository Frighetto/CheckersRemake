
function actionClass(action){
    switch (action){
        case 'Aceitar' : return "btn btn-success";
        case 'Cancelar' : return "btn btn-primary";
        case 'Entrar' : return "btn";
        case 'Desafiar' : return "btn btn-danger";
        default : return "btn btn-default";
    }
}

var listed_players = [];
var blank_time = [];

function time_limit(time, limit){        
    return time + limit < new Date().getTime();
}

function render_html_players(player_action){               
    for(var i = 0; i < listed_players.length; i++){
        var listed_player = listed_players[i];       
        if(listed_player !== null && player_action[listed_player] === undefined){
            listed_players[i] = null;
            blank_time[(i + '')] = new Date().getTime();
        }
    }    
    while(listed_players.length > 0 && player_action[listed_players[listed_players.length - 1]] === undefined){
        listed_players.pop();
    }
    var enter_game_button = null;
    var tbody = document.createElement("tbody");
    for(var i = 0; i < listed_players.length; i++){
        var player = listed_players[i];                
        
        var td_player = document.createElement("td");
        td_player.setAttribute('style', 'text-align: center; vertical-align: middle; width: 40%; font-size: Large');
            
        var tr = document.createElement("tr");
        tr.appendChild(td_player);

        var td_action = document.createElement("td");
        td_action.setAttribute('style', 'text-align: center; width: auto;');  
        
        if(player !== null){
            var player_a = document.createElement("a");
            player_a.innerHTML = player;
            player_a.setAttribute("onclick", "chat_player('" + player + "')");
            player_a.style.cursor = "pointer";
            player_a.style.color = "black";
            
            td_player.appendChild(player_a);
            
            var action = player_action[player];  
           
            if(action === 'Entrar' || action === 'Assistir'){                
                var form_element = document.createElement("form");                
                form_element.setAttribute("action", "Game");
                form_element.setAttribute("method", "post");                                                   
                var button_element = document.createElement("button");
                button_element.setAttribute('class', actionClass(action));    
                button_element.setAttribute('style', 'width: 100px; font-size: Large');     
                button_element.setAttribute("type", "submit");
                button_element.setAttribute("name", "secret_identifier");
                button_element.setAttribute("value", secret_identifier);
                button_element.innerHTML = action;      
                form_element.appendChild(button_element);
                var game_player_element = document.createElement("input");
                game_player_element.setAttribute("hidden", true);
                game_player_element.setAttribute("name", "game_player");
                game_player_element.setAttribute("value", player);
                form_element.appendChild(game_player_element);
                td_action.appendChild(form_element);  
                if(action === 'Entrar'){
                    enter_game_button = button_element;                    
                }
            } else if(action !== undefined) {    
                var button = document.createElement("button");  
                button.setAttribute('style', 'width: 100px; font-size: Large');                     
                button.setAttribute('onclick', 'challenge("' + player + '", "' + action + '")');                                                      
                button.setAttribute('class', actionClass(action));                   
                button.innerHTML = action;                       
                td_action.appendChild(button);     
            } 
        } else {
            var button = document.createElement("button");  
            button.setAttribute('style', 'width: 100px; font-size: Large');  
            button.setAttribute('disabled', true);                                                                          
            button.setAttribute('class', actionClass(null));  
            button.innerHTML = '...';
            td_action.appendChild(button);
        }
        
        tr.appendChild(td_action);
        tbody.appendChild(tr);
    } 
    
    document.getElementById("lobbyTable").innerHTML = null;
    document.getElementById("lobbyTable").appendChild(tbody);
    if(enter_game_button !== null){        
        enter_game_button.click();
    }
}

function challenge(player, action){    
    url = "LobbyInteraction?secret_identifier=" + secret_identifier + "&action=" + action + "&target=" + player;  

    var http_request = new XMLHttpRequest();			
    http_request.open("GET", url, true);			
    http_request.responseType = "responseText";
    http_request.onload = function(e) {
        var raw_lobby = http_request.response;
        attributes_to_html(raw_lobby);
    };
    http_request.send();    
}

function request(){
    var url = "LobbyInteraction?secret_identifier=" + secret_identifier + "&action=Refresh";

    var http_request = new XMLHttpRequest();			
    http_request.open("GET", url, true);			
    http_request.responseType = "responseText";
    http_request.onload = function(e) {
        var raw_lobby = http_request.response;        
        if(raw_lobby === ''){
            window.location = "/CHECKERS2";            
        } else {
            attributes_to_html(raw_lobby);
        }
    };
    http_request.send();
}

function attributes_to_html(raw_lobby){                
    
    var other_players_index = 'other_players=['.length;
    var other_players_length = raw_lobby.indexOf(']') - other_players_index;        
    var other_players = raw_lobby.substr(other_players_index, other_players_length);        
    other_players = other_players === '' ? [] : other_players.split(', ');
    raw_lobby = raw_lobby.substr(raw_lobby.indexOf(']') + 1);

    var contact_index = 'contact=['.length;
    var contact_length = raw_lobby.indexOf(']') - contact_index;        
    var contact = raw_lobby.substr(contact_index, contact_length);        
    contact = contact === '' ? [] : contact.split(', ');
    raw_lobby = raw_lobby.substr(raw_lobby.indexOf(']') + 1);

    var block_index = 'block=['.length;
    var block_length = raw_lobby.indexOf(']') - block_index;        
    var block = raw_lobby.substr(block_index, block_length);        
    block = block === '' ? [] : block.split(', ');
    raw_lobby = raw_lobby.substr(raw_lobby.indexOf(']') + 1);

    var playing_index = 'playing=['.length;
    var playing_length = raw_lobby.indexOf(']') - playing_index;  
    var playing = raw_lobby.substr(playing_index, playing_length);
    playing = playing === '' ? [] : playing.split(', ');        
    raw_lobby = raw_lobby.substr(raw_lobby.indexOf(']') + 1);

    var challenger_index = 'challenger=['.length;
    var challenger_length = raw_lobby.indexOf(']') - challenger_index;  
    var challenger = raw_lobby.substr(challenger_index, challenger_length);
    challenger = challenger === '' ? [] : challenger.split(', ');                
    raw_lobby = raw_lobby.substr(raw_lobby.indexOf(']') + 1);

    var challenged_index = 'challenged=['.length;
    var challenged_length = raw_lobby.indexOf(']') - challenged_index;  
    var challenged = raw_lobby.substr(challenged_index, challenged_length);
    challenged = challenged === '' ? [] : challenged.split(', ');                  
    raw_lobby = raw_lobby.substr(raw_lobby.indexOf(']') + 1);                

    var player_action = [];    
    for(var i = 0; i < other_players.length; i++){
        var player = other_players[i];
        add_listed_player(player);
        if(challenger.indexOf(player) !== -1 && challenged.indexOf(player) !== -1){
            player_action[player] = 'Entrar';   
        } else if(challenger.indexOf(player) !== -1){
            player_action[player] = 'Aceitar';           
        } else if(playing.indexOf(player) !== -1){
            player_action[player] = 'Assistir';              
        } else if(challenged.indexOf(player) !== -1){
            player_action[player] = 'Cancelar';            
        } else {
            player_action[player] = 'Desafiar';            
        }
    }

    render_html_players(player_action);
    render_html_contacts(other_players, contact, block);
    render_html_chat(raw_lobby);
    
}

function add_listed_player(player){
    if(!listed_players.includes(player)){
        var blank_index = listed_players.indexOf(null);
        if(blank_index !== -1){
            var time = blank_time[(blank_index + '')];
            if(time === null || time_limit(time, 31416)){
                listed_players[blank_index] = player;
            } else {
                listed_players.push(player);
            }
        } else {
            listed_players.push(player);
        }
    }
}

