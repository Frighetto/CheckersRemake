var selected_player_chat = null;

function render_html_contacts(online, contacts, block, game_players = []){
    document.getElementById("Salvos_tbody").innerHTML = null;
    contacts.forEach(function(player){    
        var contacts_body_tr = document.createElement("tr");
        var contacts_body_td_usuario = document.createElement("td");
        contacts_body_td_usuario.style.textAlign = "center";
        contacts_body_td_usuario.style.fontSize = "Large";
        contacts_body_td_usuario.style.verticalAlign = "middle";
        contacts_body_td_usuario.style.width = "50%";     

        if(online.indexOf(player) != -1){
            var contacts_body_a = document.createElement("a");
            contacts_body_a.setAttribute("onclick", "chat_player('" + player + "')");
            contacts_body_a.style.color = "black";
            contacts_body_a.style.cursor = "pointer";
            contacts_body_a.innerHTML = player;
            contacts_body_td_usuario.appendChild(contacts_body_a);
        } else {
            contacts_body_td_usuario.innerHTML = player;
        }

        var contacts_body_td_participante = document.createElement("td");
        contacts_body_td_participante.style.textAlign = "center";
        contacts_body_td_participante.style.fontSize = "Large";
        contacts_body_td_participante.style.verticalAlign = "middle";
        contacts_body_td_participante.style.width = "50%";  
        var status = online.indexOf(player) != -1 ? "online" : "offline";
        contacts_body_td_participante.innerHTML = status;  

        contacts_body_tr.appendChild(contacts_body_td_usuario);
        contacts_body_tr.appendChild(contacts_body_td_participante);

        document.getElementById("Salvos_tbody").appendChild(contacts_body_tr);
    });
    
    document.getElementById("Deletados_tbody").innerHTML = null;
    block.forEach(function(player){        
        var deletados_body_tr = document.createElement("tr");        

        var deletados_body_td_player = document.createElement("td");
        deletados_body_td_player.style.textAlign = "center";
        deletados_body_td_player.style.fontSize = "Large";
        deletados_body_td_player.style.verticalAlign = "middle";
        deletados_body_td_player.style.width = "50%";  
        deletados_body_td_player.innerHTML = player;     

        var deletados_body_td_remover = document.createElement("td");
        deletados_body_td_remover.style.textAlign = "center";
        deletados_body_td_remover.style.fontSize = "Large";
        deletados_body_td_remover.style.verticalAlign = "middle";
        deletados_body_td_remover.style.width = "50%";    

        deletados_body_tr.appendChild(deletados_body_td_player);
        deletados_body_tr.appendChild(deletados_body_td_remover);

        document.getElementById("Deletados_tbody").appendChild(deletados_body_tr);
    });
    
    if(game_players.length){
        document.getElementById("Jogadores_tbody").innerHTML = null;
    }
    for(var i = 0; i < game_players.length; i++){ 
        var player = game_players[i];
        var game_contacts_body_tr = document.createElement("tr");
        var game_contacts_body_td_usuario = document.createElement("td");
        game_contacts_body_td_usuario.style.textAlign = "center";
        game_contacts_body_td_usuario.style.fontSize = "Large";
        game_contacts_body_td_usuario.style.verticalAlign = "middle";
        game_contacts_body_td_usuario.style.width = "50%";     
    
        var game_contacts_body_a = document.createElement("a");
        game_contacts_body_a.setAttribute("onclick", "chat_player('" + player + "')");
        game_contacts_body_a.style.color = "black";
        game_contacts_body_a.style.cursor = "pointer";
        game_contacts_body_a.innerHTML = player;
    
        game_contacts_body_td_usuario.appendChild(game_contacts_body_a);
    
        var game_contacts_body_td_participante = document.createElement("td");
        game_contacts_body_td_participante.style.textAlign = "center";
        game_contacts_body_td_participante.style.fontSize = "Large";
        game_contacts_body_td_participante.style.verticalAlign = "middle";
        game_contacts_body_td_participante.style.width = "50%";          
        game_contacts_body_td_participante.innerHTML = i > 1 ? "Testemunha" : i < 1 ? "Player 1" : "Player 2";
                                
        game_contacts_body_tr.appendChild(game_contacts_body_td_usuario);
        game_contacts_body_tr.appendChild(game_contacts_body_td_participante);
    
        document.getElementById("Jogadores_tbody").appendChild(game_contacts_body_tr);
    }    
}

function chat_player(player_name){
    var chat_input = document.getElementById("chat-input");    
    var already_selected = chat_input.getAttribute('placeholder') === player_name;
    if(already_selected){        
        chat_input.removeAttribute('placeholder', null);
        selected_player_chat = null;
    } else {        
        chat_input.setAttribute('placeholder', player_name);    
        selected_player_chat = player_name;
    }              
    chat_input.value = null;
    chat_input.focus();      
}

function render_html_chat(messages){
    var chat_messages = document.getElementById('chat-messages');
    var chat_height = chat_messages.style.height;
    chat_height = parseInt(chat_height.substr(0, chat_height.length - 2));          
    if(chat_messages.scrollHeight - chat_messages.scrollTop <= chat_height){         
        chat_messages.value += messages;
        chat_messages.scrollTop += chat_messages.scrollHeight;
    } else {
        chat_messages.value += messages;       
    }    
}

function check_enter_keypress(context) {
    var key = window.event.keyCode;    
    const esc = 27;
    const backspace = 8;
    const enter = 13;
    var chat_input = document.getElementById("chat-input");        
    if (key === enter && chat_input !== '') {
        var receiver = selected_player_chat === null ? context : selected_player_chat;
        var message = chat_input.value;
        var url = "Message?secret_identifier=" + secret_identifier + "&receiver=" + receiver + "&message=" + message;
        
        var http_request = new XMLHttpRequest();			
        http_request.open("GET", url, true);			
        http_request.responseType = "responseText";
        http_request.onload = function(e) {
            var messages = http_request.response;
            render_html_chat(messages);            
        };
        http_request.send();
        chat_input.value = null;
    }    
    if (key === esc || (key === backspace && chat_input.value === '')){
        chat_input.removeAttribute('placeholder', null);
        selected_player_chat = null;
        chat_input.value = null;
    }
}

function tab(evt, id) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(id).style.display = "block";
    evt.currentTarget.className += " active";
}

function exibir_opt_salvos(){
    document.getElementById("opt_salvos").removeAttribute("style");
    document.getElementById("salvos_adicionar").style.display = "none";
    document.getElementById("salvos_remover").style.display = "none";
}

function exibir_salvos_adicionar(){
    document.getElementById("opt_salvos").style.display = "none";
    document.getElementById("salvos_adicionar").removeAttribute("style");
    document.getElementById("salvos_remover").style.display = "none";
}

function exibir_salvos_remover(){
    document.getElementById("opt_salvos").style.display = "none";
    document.getElementById("salvos_adicionar").style.display = "none";
    document.getElementById("salvos_remover").removeAttribute("style");
}

function exibir_opt_deletados(){
    document.getElementById("opt_deletados").removeAttribute("style");
    document.getElementById("deletados_adicionar").style.display = "none";
    document.getElementById("deletados_remover").style.display = "none";
}

function exibir_deletados_adicionar(){
    document.getElementById("opt_deletados").style.display = "none";
    document.getElementById("deletados_adicionar").removeAttribute("style");
    document.getElementById("deletados_remover").style.display = "none";
}

function exibir_deletados_remover(){
    document.getElementById("opt_deletados").style.display = "none";
    document.getElementById("deletados_adicionar").style.display = "none";
    document.getElementById("deletados_remover").removeAttribute("style");
}

function salvos_adicionar(){
    var contact = document.getElementById("input_salvos_adicionar").value;
    contact_interaction("Save", contact);
    exibir_opt_salvos();
    document.getElementById("input_salvos_adicionar").value = null;
    request();
}

function salvos_remover(){
    var contact = document.getElementById("input_salvos_remover").value;
    contact_interaction("Unsave", contact);
    exibir_opt_salvos();
    document.getElementById("input_salvos_remover").value = null;
    request();
}

function deletados_adicionar(){
    var contact = document.getElementById("input_deletados_adicionar").value;
    contact_interaction("Block", contact);
    document.getElementById("input_deletados_adicionar").value = null;
    request();
    exibir_opt_deletados();
}

function deletados_remover(){
    var contact = document.getElementById("input_deletados_remover").value;
    contact_interaction("Unblock", contact);
    document.getElementById("input_deletados_remover").value = null;
    request();
    exibir_opt_deletados();
}

function contact_interaction(action, target){
    var url = "Contact?secret_identifier=" + secret_identifier + "&action=" + action + "&target=" + target;

    var http_request = new XMLHttpRequest();			
    http_request.open("GET", url, true);			
    http_request.responseType = "responseText";    
    http_request.send();
}