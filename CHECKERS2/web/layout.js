const body = document.getElementsByTagName("body")[0];                        
body.addEventListener("mousemove", function (event) {			
    xy = "(" + event.clientX + "," + event.clientY + ")";    
});         
const nav_height = document.getElementById("top-bar").offsetHeight;
if(body.offsetWidth > body.offsetHeight){
    var boardgame_size = body.offsetHeight - nav_height - (body.offsetHeight * 0.02);
} else if(body.offsetWidth < body.offsetHeight){
    var boardgame_size = body.offsetWidth - (body.offsetWidth * 0.02);
}
var boardgame_unit_size = boardgame_size / 8;                
var boardgame_marginLeft = parseInt(body.offsetWidth - boardgame_size) / 2;                        
var boardgame_marginTop = nav_height + parseInt(body.offsetHeight * 0.01);                
var boardgame_element = document.getElementById("boardgame");
if(boardgame_element !== null){    
    boardgame_element.style.marginTop = nav_height + (body.offsetHeight * 0.01) + "px";            
    boardgame_element.style.width = boardgame_size + "px";
    boardgame_element.style.height = boardgame_size + "px";
    boardgame_element.style.backgroundColor = "black";
} else {
    var lobbyTable = document.getElementById("lobby");
    lobbyTable.style.marginTop = nav_height + (body.offsetHeight * 0.01) + "px";            
    lobbyTable.style.width = boardgame_size + "px";
    lobbyTable.style.height = boardgame_size + "px";
}

var players_div_height = null;
var players_element = document.getElementById("players");
if(body.offsetWidth > body.offsetHeight){                                           
    players_element.style.marginTop = nav_height + (body.offsetHeight * 0.01) + "px";
    players_element.style.marginLeft = "1%";
    if(body.offsetWidth > (body.offsetHeight * 2 + (body.offsetWidth * 0.03))){ 
        players_element.style.width = boardgame_size + "px";
    } else {                     
        players_element.style.width = (body.offsetWidth - boardgame_size - (body.offsetWidth * 0.03)) + "px";
    }    
    players_div_height = boardgame_size;            
    players_element.style.height = players_div_height + "px";
                                                                   
} else if(body.offsetWidth < body.offsetHeight){                 
    players_element.style.marginTop = "1%";
    players_element.style.width = boardgame_size + "px";
    var top_size = nav_height + (body.offsetHeight * 0.03);

    if(body.offsetHeight > (body.offsetWidth * 2 + top_size)){ 
        players_div_height = boardgame_size;                    
    } else {                     
        players_div_height = (body.offsetHeight - top_size - boardgame_size);    
    }                                         
    players_element.style.height = players_div_height + "px";

    var mobile = document.createElement("div");
    mobile.setAttribute("class", "flex-centralizado");
    mobile.appendChild(players_element); 
    body.appendChild(mobile);                
}

function setHeights(){
    var tabs = document.getElementById("tabs");
    var game_panel = document.getElementById("game-panel");

    if(game_panel === null){ 
        var game_panelOffSetHeight = 0;   
    } else { 
        var game_panelOffSetHeight = game_panel.offsetHeight;      
    }
    var contacts_heigth = ((players_div_height - tabs.offsetHeight - game_panelOffSetHeight) / 2) + "px"; 

    var jogadores_div = document.getElementById("Jogadores");
    if(jogadores_div !== null){
        jogadores_div.style.height = contacts_heigth;
    }

    var salvos_div = document.getElementById("Salvos");
    salvos_div.style.height = contacts_heigth;

    var deletados_div = document.getElementById("Deletados");
    deletados_div.style.height = contacts_heigth;

    var chat_input = document.getElementById("chat-input");
    var chat_messages = document.getElementById("chat-messages");
    chat_messages.style.height = ((players_div_height - tabs.offsetHeight - game_panelOffSetHeight) / 2) - chat_input.offsetHeight + "px";
}

setHeights();