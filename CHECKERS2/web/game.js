
const margin_top = [0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7];
const margin_left = [1, 3, 5, 7, 2, 4, 6, 8, 1, 3, 5, 7, 2, 4, 6, 8, 1, 3, 5, 7, 2, 4, 6, 8, 1, 3, 5, 7, 2, 4, 6, 8];

var moves = [];           
var captures = []; 

const PLAYER1_COLOR = 'red';
const PLAYER2_COLOR = 'white';

function player1_boardgame(){    
    //put white squares first to not be over checker pieces at move animation
    add_boardgame_unit("A8", boardgame_unit_size, 0, 0, "white");    
    add_boardgame_unit("C8", boardgame_unit_size, 0, 2, "white");    
    add_boardgame_unit("E8", boardgame_unit_size, 0, 4, "white");    
    add_boardgame_unit("G8", boardgame_unit_size, 0, 6, "white");
                                                               
    add_boardgame_unit("B7", boardgame_unit_size, 1, 1, "white");    
    add_boardgame_unit("D7", boardgame_unit_size, 1, 3, "white");    
    add_boardgame_unit("F7", boardgame_unit_size, 1, 5, "white");    
    add_boardgame_unit("H7", boardgame_unit_size, 1, 7, "white"); 
                                          
    add_boardgame_unit("A6", boardgame_unit_size, 2, 0, "white");    
    add_boardgame_unit("C6", boardgame_unit_size, 2, 2, "white");    
    add_boardgame_unit("E6", boardgame_unit_size, 2, 4, "white");    
    add_boardgame_unit("G6", boardgame_unit_size, 2, 6, "white");       
                                                  
    add_boardgame_unit("B5", boardgame_unit_size, 3, 1, "white");    
    add_boardgame_unit("D5", boardgame_unit_size, 3, 3, "white");    
    add_boardgame_unit("F5", boardgame_unit_size, 3, 5, "white");    
    add_boardgame_unit("H5", boardgame_unit_size, 3, 7, "white");                         
            
    add_boardgame_unit("A4", boardgame_unit_size, 4, 0, "white");    
    add_boardgame_unit("C4", boardgame_unit_size, 4, 2, "white");    
    add_boardgame_unit("E4", boardgame_unit_size, 4, 4, "white");    
    add_boardgame_unit("G4", boardgame_unit_size, 4, 6, "white");
                                    
    add_boardgame_unit("B3", boardgame_unit_size, 5, 1, "white");    
    add_boardgame_unit("D3", boardgame_unit_size, 5, 3, "white");    
    add_boardgame_unit("F3", boardgame_unit_size, 5, 5, "white");    
    add_boardgame_unit("H3", boardgame_unit_size, 5, 7, "white");   
                                                                   
    add_boardgame_unit("A2", boardgame_unit_size, 6, 0, "white");    
    add_boardgame_unit("C2", boardgame_unit_size, 6, 2, "white");    
    add_boardgame_unit("E2", boardgame_unit_size, 6, 4, "white");    
    add_boardgame_unit("G2", boardgame_unit_size, 6, 6, "white");
                                                   
    add_boardgame_unit("B1", boardgame_unit_size, 7, 1, "white");    
    add_boardgame_unit("D1", boardgame_unit_size, 7, 3, "white");    
    add_boardgame_unit("F1", boardgame_unit_size, 7, 5, "white");    
    add_boardgame_unit("H1", boardgame_unit_size, 7, 7, "white");  
    
    add_boardgame_unit("B8", boardgame_unit_size, 0, 1);
    add_boardgame_unit("D8", boardgame_unit_size, 0, 3);
    add_boardgame_unit("F8", boardgame_unit_size, 0, 5);
    add_boardgame_unit("H8", boardgame_unit_size, 0, 7);  

    add_boardgame_unit("A7", boardgame_unit_size, 1, 0);
    add_boardgame_unit("C7", boardgame_unit_size, 1, 2);
    add_boardgame_unit("E7", boardgame_unit_size, 1, 4);
    add_boardgame_unit("G7", boardgame_unit_size, 1, 6);

    add_boardgame_unit("B6", boardgame_unit_size, 2, 1);
    add_boardgame_unit("D6", boardgame_unit_size, 2, 3);
    add_boardgame_unit("F6", boardgame_unit_size, 2, 5);
    add_boardgame_unit("H6", boardgame_unit_size, 2, 7);
    
    add_boardgame_unit("A5", boardgame_unit_size, 3, 0);
    add_boardgame_unit("C5", boardgame_unit_size, 3, 2);
    add_boardgame_unit("E5", boardgame_unit_size, 3, 4);
    add_boardgame_unit("G5", boardgame_unit_size, 3, 6);
    
    add_boardgame_unit("B4", boardgame_unit_size, 4, 1);
    add_boardgame_unit("D4", boardgame_unit_size, 4, 3);
    add_boardgame_unit("F4", boardgame_unit_size, 4, 5);
    add_boardgame_unit("H4", boardgame_unit_size, 4, 7);   
    
    add_boardgame_unit("A3", boardgame_unit_size, 5, 0);
    add_boardgame_unit("C3", boardgame_unit_size, 5, 2);
    add_boardgame_unit("E3", boardgame_unit_size, 5, 4);
    add_boardgame_unit("G3", boardgame_unit_size, 5, 6);
    
    add_boardgame_unit("B2", boardgame_unit_size, 6, 1);
    add_boardgame_unit("D2", boardgame_unit_size, 6, 3);
    add_boardgame_unit("F2", boardgame_unit_size, 6, 5);
    add_boardgame_unit("H2", boardgame_unit_size, 6, 7); 
    
    add_boardgame_unit("A1", boardgame_unit_size, 7, 0);
    add_boardgame_unit("C1", boardgame_unit_size, 7, 2);
    add_boardgame_unit("E1", boardgame_unit_size, 7, 4);
    add_boardgame_unit("G1", boardgame_unit_size, 7, 6);
}


function player2_and_viewer_boardgame(){
    //put white squares first to not be over checker pieces at move animation            
    add_boardgame_unit("H1", boardgame_unit_size, 0, 0, "white");                            
    add_boardgame_unit("F1", boardgame_unit_size, 0, 2, "white");    
    add_boardgame_unit("D1", boardgame_unit_size, 0, 4, "white");    
    add_boardgame_unit("B1", boardgame_unit_size, 0, 6, "white");    
                        
    add_boardgame_unit("G2", boardgame_unit_size, 1, 1, "white");    
    add_boardgame_unit("E2", boardgame_unit_size, 1, 3, "white");    
    add_boardgame_unit("C2", boardgame_unit_size, 1, 5, "white");    
    add_boardgame_unit("A2", boardgame_unit_size, 1, 7, "white");
                       
    add_boardgame_unit("H3", boardgame_unit_size, 2, 0, "white");                            
    add_boardgame_unit("F3", boardgame_unit_size, 2, 2, "white");    
    add_boardgame_unit("D3", boardgame_unit_size, 2, 4, "white");    
    add_boardgame_unit("B3", boardgame_unit_size, 2, 6, "white");
                     
    add_boardgame_unit("G4", boardgame_unit_size, 3, 1, "white");   
    add_boardgame_unit("E4", boardgame_unit_size, 3, 3, "white");    
    add_boardgame_unit("C4", boardgame_unit_size, 3, 5, "white");    
    add_boardgame_unit("A4", boardgame_unit_size, 3, 7, "white");
    
    add_boardgame_unit("H5", boardgame_unit_size, 4, 0, "white");                            
    add_boardgame_unit("F5", boardgame_unit_size, 4, 2, "white");    
    add_boardgame_unit("D5", boardgame_unit_size, 4, 4, "white");    
    add_boardgame_unit("B5", boardgame_unit_size, 4, 6, "white");    
                        
    add_boardgame_unit("G6", boardgame_unit_size, 5, 1, "white");   
    add_boardgame_unit("E6", boardgame_unit_size, 5, 3, "white");   
    add_boardgame_unit("C6", boardgame_unit_size, 5, 5, "white");    
    add_boardgame_unit("A6", boardgame_unit_size, 5, 7, "white");           
        
    add_boardgame_unit("H7", boardgame_unit_size, 6, 0, "white");                           
    add_boardgame_unit("F7", boardgame_unit_size, 6, 2, "white");    
    add_boardgame_unit("D7", boardgame_unit_size, 6, 4, "white");    
    add_boardgame_unit("B7", boardgame_unit_size, 6, 6, "white");
                             
    add_boardgame_unit("G8", boardgame_unit_size, 7, 1, "white");    
    add_boardgame_unit("E8", boardgame_unit_size, 7, 3, "white");    
    add_boardgame_unit("C8", boardgame_unit_size, 7, 5, "white");    
    add_boardgame_unit("A8", boardgame_unit_size, 7, 7, "white");
    
    add_boardgame_unit("G1", boardgame_unit_size, 0, 1);
    add_boardgame_unit("E1", boardgame_unit_size, 0, 3);
    add_boardgame_unit("C1", boardgame_unit_size, 0, 5);
    add_boardgame_unit("A1", boardgame_unit_size, 0, 7);
    
    add_boardgame_unit("H2", boardgame_unit_size, 1, 0);  
    add_boardgame_unit("F2", boardgame_unit_size, 1, 2);
    add_boardgame_unit("D2", boardgame_unit_size, 1, 4);
    add_boardgame_unit("B2", boardgame_unit_size, 1, 6);
    
    add_boardgame_unit("G3", boardgame_unit_size, 2, 1);
    add_boardgame_unit("E3", boardgame_unit_size, 2, 3);
    add_boardgame_unit("C3", boardgame_unit_size, 2, 5);
    add_boardgame_unit("A3", boardgame_unit_size, 2, 7);
    
    add_boardgame_unit("H4", boardgame_unit_size, 3, 0);          
    add_boardgame_unit("F4", boardgame_unit_size, 3, 2);
    add_boardgame_unit("D4", boardgame_unit_size, 3, 4);
    add_boardgame_unit("B4", boardgame_unit_size, 3, 6);
    
    add_boardgame_unit("G5", boardgame_unit_size, 4, 1);
    add_boardgame_unit("E5", boardgame_unit_size, 4, 3);
    add_boardgame_unit("C5", boardgame_unit_size, 4, 5);
    add_boardgame_unit("A5", boardgame_unit_size, 4, 7);
        
    add_boardgame_unit("H6", boardgame_unit_size, 5, 0);    
    add_boardgame_unit("F6", boardgame_unit_size, 5, 2);
    add_boardgame_unit("D6", boardgame_unit_size, 5, 4);
    add_boardgame_unit("B6", boardgame_unit_size, 5, 6);          
    
    add_boardgame_unit("G7", boardgame_unit_size, 6, 1);
    add_boardgame_unit("E7", boardgame_unit_size, 6, 3);
    add_boardgame_unit("C7", boardgame_unit_size, 6, 5);
    add_boardgame_unit("A7", boardgame_unit_size, 6, 7);
    
    add_boardgame_unit("H8", boardgame_unit_size, 7, 0);  
    add_boardgame_unit("F8", boardgame_unit_size, 7, 2);
    add_boardgame_unit("D8", boardgame_unit_size, 7, 4);
    add_boardgame_unit("B8", boardgame_unit_size, 7, 6);
}

                       
var dragover_unit = null;
var dragover_xy = null;
var dragleave_xy = null;

function add_boardgame_unit(id, boardgame_unit_size, marginTop, marginLeft, backgroundColor = null){ 
    var boardgame_unit = document.createElement("div");
    boardgame_unit.setAttribute("id", id);
    boardgame_unit.style.width = boardgame_unit_size + "px";
    boardgame_unit.style.height = boardgame_unit_size + "px";
    boardgame_unit.style.padding = 0;
    boardgame_unit.style.marginTop = (boardgame_unit_size * marginTop) + "px";
    boardgame_unit.style.marginLeft = (boardgame_unit_size * marginLeft) + "px";                             
    boardgame_unit.style.position = "absolute";  
    boardgame_unit.style.backgroundColor = backgroundColor; 
                   
    boardgame_unit.addEventListener('dragover', (e) => {
        e.dataTransfer.dropEffect = "move";
        e.preventDefault();
        dragover_xy = xy;
        dragover_unit = id;                
    });                
    boardgame_unit.addEventListener('dragleave', (e) => {                  
        dragleave_xy = xy;
        if(dragover_xy !== dragleave_xy){
            dragover_unit = null;
        }                                 
    });
    
    document.getElementById("boardgame").appendChild(boardgame_unit);    
}


var x = null;
var y = null;   
var draged_checker = null;
var origin_draged_checker = null;
boardgame_element.addEventListener('touchmove', (e) => { 
    var touchLocation = e.targetTouches[0];  
    x = parseInt(touchLocation.pageX) - boardgame_marginLeft;
    y = parseInt(touchLocation.pageY) - boardgame_marginTop;   
    draged_checker.style.marginLeft = parseInt(x - (boardgame_unit_size / 2)) + 'px';
    draged_checker.style.marginTop = parseInt(y - (boardgame_unit_size / 2)) + 'px';         
});

boardgame_element.addEventListener('touchend', (e) => {   
    boardgame_element.removeChild(draged_checker);
    draged_checker.style.width = null;
    draged_checker.style.height = null;
    draged_checker.style.borderRadius = null;
    draged_checker.style.marginLeft = null;
    draged_checker.style.marginTop = null;
    draged_checker.style.position = null;
    draged_checker.classList.add("circle");        
        
    var horizontal = parseInt(x / boardgame_unit_size) + 1; 
    var vertical = parseInt(y / boardgame_unit_size) + 1; 
    var letras_player1 = [null, "A", "B", "C", "D", "E", "F", "G", "H"];
    var letras_player2 = [null, "H", "G", "F", "E", "D", "C", "B", "A"];        
    var animation_moves_done = origin_moves_queue.length === 0;          
    var checker_draged = draged_checker !== null;    
    var destiny_square = draged_checker.style.backgroundColor === PLAYER1_COLOR ? letras_player1[horizontal] + (9 - vertical) : letras_player2[horizontal] + vertical;                
    var destiny_square_exist = destiny_square !== null;     
    
    var moves_exist = !(moves[origin_draged_checker] === null || moves[origin_draged_checker] === undefined);   
    var valid_move = destiny_square_exist && moves_exist && moves[origin_draged_checker].indexOf(destiny_square) !== -1;
            
    if(animation_moves_done && checker_draged && valid_move){        
        document.getElementById(destiny_square).appendChild(draged_checker);           
        try_catch_checker(origin_draged_checker, destiny_square);                        
        try_print_king(destiny_square);                        
        request_move(origin_draged_checker, destiny_square);               
    } else {       
        document.getElementById(origin_draged_checker).appendChild(draged_checker);         
    }       
   
    draged_checker = null;
    origin_draged_checker = null;
    
});  

function try_print_king(square){        
    var square_element = document.getElementById(square);    
    if(square_element.childNodes.length !== 0){ 
        var element = square_element.childNodes[0];        
        if(element.childNodes.length === 0){
            if(element.style.backgroundColor === PLAYER2_COLOR){
                if(square === A1 || square === C1 || square === E1 || square === G1){        
                    crown_toe(element);
                }
            }
            if(element.style.backgroundColor === PLAYER1_COLOR){
                if(square === B8 || square === D8 || square === F8 || square === H8){
                    crown_toe(element);
                }
            } 
        }      
    }
}

function crown_toe(checker){
    var inner_circle = document.createElement("div");                
    inner_circle.setAttribute("class", "inner-circle");    
    inner_circle.style.backgroundColor = checker.style.backgroundColor;

    var ring = document.createElement("div");                
    ring.setAttribute("class", "ring");  
    ring.appendChild(inner_circle);     
    checker.appendChild(ring);    
}

function add_boardgame_checker(position, checker){       
    var color = checker === 'STARTER_PIECE' || checker === 'STARTER_KING' ? PLAYER1_COLOR : PLAYER2_COLOR;    
    var circle = document.createElement("div");  
    var checkerID = position;
    circle.style.backgroundColor = color;       
    circle.setAttribute("class", "circle");              
    circle.setAttribute("draggable", "true");
    circle.addEventListener('dragstart', (event) => {
        circle.style.opacity = 0.92;         
        checkerID = circle.parentElement.getAttribute('id');             
    });                  
        
    circle.addEventListener('dragend', (event) => {                                        
        if(origin_moves_queue.length === 0 && dragover_unit !== null){                   
            if(moves[checkerID] !== null && moves[checkerID].indexOf(dragover_unit) !== -1) {                                
                circle.parentNode.innerHTML = null;                
                document.getElementById(dragover_unit).appendChild(circle);  
                try_catch_checker(checkerID, dragover_unit);                 
                try_print_king(dragover_unit);
                request_move(checkerID, dragover_unit);
            }                    
        }                          
        dragover_unit = null;
        dragover_xy = null;
        dragleave_xy = null;
        circle.style.opacity = null;
    });
    
    circle.addEventListener('touchstart', (e) => {                 
        origin_draged_checker = circle.parentElement.getAttribute('id');   
        var moves_exist = !(moves[origin_draged_checker] === null || moves[origin_draged_checker] === undefined);
        if(moves_exist){
            draged_checker = circle;
            circle.parentNode.innerHTML = null;
            circle.classList.remove("circle");
            circle.style.width = 0.9 * boardgame_unit_size + "px";
            circle.style.height = 0.9 * boardgame_unit_size + "px";
            circle.style.borderRadius = '100%';
            circle.style.position = 'absolute';

            var touchLocation = e.targetTouches[0];  
            x = parseInt(touchLocation.pageX) - boardgame_marginLeft;
            y = parseInt(touchLocation.pageY) - boardgame_marginTop;   
            draged_checker.style.marginLeft = parseInt(x - (boardgame_unit_size / 2)) + 'px';
            draged_checker.style.marginTop = parseInt(y - (boardgame_unit_size / 2)) + 'px';                         

            boardgame_element.appendChild(circle);     
        } else {
            origin_draged_checker = null;
        }
    });            
    
    if(checker === 'STARTER_KING' || checker === 'SECONDARY_KING'){
        crown_toe(circle);
    }        
    document.getElementById(position).appendChild(circle);    
}

function request_move(position, move){
    var url = "GameInteraction?secret_identifier=" + secret_identifier + "&position=" + position + "&move=" + move;
    if(index_moves !== null){
        index_moves = index_moves + 1;        
    } else {
        index_moves = 0;
    }
    url += "&index_moves=" + index_moves;
    
    var http_request = new XMLHttpRequest();			
    http_request.open("POST", url, true);			
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

function catch_finally(checker){
    document.getElementById(checker).innerHTML = null;    
}
function catch_checker(origin_move, destiny_move, origin, checker, destiny){
    if(origin_move === origin && destiny_move === destiny){
        catch_finally(checker);
    }
}

function try_catch_checker(origin_move, destiny_move){
    catch_checker(origin_move, destiny_move, A1, B2, C3);
    catch_checker(origin_move, destiny_move, C1, B2, A3);
    catch_checker(origin_move, destiny_move, C1, D2, E3);
    
    catch_checker(origin_move, destiny_move, E1, D2, C3);
    catch_checker(origin_move, destiny_move, E1, F2, G3);
    
    catch_checker(origin_move, destiny_move, G1, F2, E3);
    
    catch_checker(origin_move, destiny_move, B2, C3, D4);
    
    catch_checker(origin_move, destiny_move, D2, C3, B4);
    catch_checker(origin_move, destiny_move, D2, E3, F4);
    
    catch_checker(origin_move, destiny_move, F2, E3, D4);
    catch_checker(origin_move, destiny_move, F2, G3, H4);
    
    catch_checker(origin_move, destiny_move, H2, G3, F4);
    
    catch_checker(origin_move, destiny_move, A3, B2, C1);
    catch_checker(origin_move, destiny_move, A3, B4, C5);
    
    catch_checker(origin_move, destiny_move, C3, B2, A1);
    catch_checker(origin_move, destiny_move, C3, D2, E1);
    catch_checker(origin_move, destiny_move, C3, B4, A5);
    catch_checker(origin_move, destiny_move, C3, D4, E5);
    
    catch_checker(origin_move, destiny_move, E3, D2, C1);
    catch_checker(origin_move, destiny_move, E3, F2, G1);
    catch_checker(origin_move, destiny_move, E3, D4, C5);
    catch_checker(origin_move, destiny_move, E3, F4, G5);
    
    catch_checker(origin_move, destiny_move, G3, F2, E1);
    catch_checker(origin_move, destiny_move, G3, F4, E5);
    
    catch_checker(origin_move, destiny_move, B4, C3, D2);
    catch_checker(origin_move, destiny_move, B4, C5, D6);
    
    catch_checker(origin_move, destiny_move, D4, C3, B2);
    catch_checker(origin_move, destiny_move, D4, E3, F2);
    catch_checker(origin_move, destiny_move, D4, C5, B6);
    catch_checker(origin_move, destiny_move, D4, E5, F6);
    
    catch_checker(origin_move, destiny_move, F4, E3, D2);
    catch_checker(origin_move, destiny_move, F4, G3, H2);
    catch_checker(origin_move, destiny_move, F4, E5, D6);
    catch_checker(origin_move, destiny_move, F4, G5, H6);
    
    catch_checker(origin_move, destiny_move, H4, G3, F2);
    catch_checker(origin_move, destiny_move, H4, G5, F6);
    
    catch_checker(origin_move, destiny_move, A5, B4, C3);
    catch_checker(origin_move, destiny_move, A5, B6, C7);
    
    catch_checker(origin_move, destiny_move, C5, B4, A3);
    catch_checker(origin_move, destiny_move, C5, D4, E3);
    catch_checker(origin_move, destiny_move, C5, B6, A7);
    catch_checker(origin_move, destiny_move, C5, D6, E7);
    
    catch_checker(origin_move, destiny_move, E5, D4, C3);
    catch_checker(origin_move, destiny_move, E5, F4, G3);
    catch_checker(origin_move, destiny_move, E5, D6, C7);
    catch_checker(origin_move, destiny_move, E5, F6, G7);
    
    catch_checker(origin_move, destiny_move, G5, F4, E3);
    catch_checker(origin_move, destiny_move, G5, F6, E7);
    
    catch_checker(origin_move, destiny_move, B6, C5, D4);
    catch_checker(origin_move, destiny_move, B6, C7, D8);
    
    catch_checker(origin_move, destiny_move, D6, C5, B4);
    catch_checker(origin_move, destiny_move, D6, E5, F4);
    catch_checker(origin_move, destiny_move, D6, C7, B8);
    catch_checker(origin_move, destiny_move, D6, E7, F8);
    
    catch_checker(origin_move, destiny_move, F6, E5, D4);
    catch_checker(origin_move, destiny_move, F6, G5, H4);
    catch_checker(origin_move, destiny_move, F6, E7, D8);
    catch_checker(origin_move, destiny_move, F6, G7, H8);
    
    catch_checker(origin_move, destiny_move, H6, G5, F4);
    catch_checker(origin_move, destiny_move, H6, G7, F8);
    
    catch_checker(origin_move, destiny_move, A7, B6, C5);
    
    catch_checker(origin_move, destiny_move, C7, B6, A5);
    catch_checker(origin_move, destiny_move, C7, D6, E5);
    
    catch_checker(origin_move, destiny_move, E7, D6, C5);
    catch_checker(origin_move, destiny_move, E7, F6, G5);
    
    catch_checker(origin_move, destiny_move, G7, F6, E5);
    
    catch_checker(origin_move, destiny_move, B8, C7, D6);
    
    catch_checker(origin_move, destiny_move, D8, C7, B6);
    catch_checker(origin_move, destiny_move, D8, E7, F6);
    
    catch_checker(origin_move, destiny_move, F8, E7, D6);
    catch_checker(origin_move, destiny_move, F8, G7, H6);
    
    catch_checker(origin_move, destiny_move, H8, G7, F6);    
}

const CLASS_BOTTOM_TO_TOP_LEFT = 'circlemove-bottom-to-top-left';
const CLASS_BOTTOM_TO_TOP_LEFT_2 = 'circlemove2-bottom-to-top-left';
const CLASS_BOTTOM_TO_TOP_RIGHT = 'circlemove-bottom-to-top-right';
const CLASS_BOTTOM_TO_TOP_RIGHT_2 = 'circlemove2-bottom-to-top-right';
const CLASS_TOP_TO_BOTTOM_LEFT = 'circlemove-top-to-bottom-left';
const CLASS_TOP_TO_BOTTOM_LEFT_2 = 'circlemove2-top-to-bottom-left';
const CLASS_TOP_TO_BOTTOM_RIGHT = 'circlemove-top-to-bottom-right';
const CLASS_TOP_TO_BOTTOM_RIGHT_2 = 'circlemove2-top-to-bottom-right';

const OPOSITE_MOVE_CLASS = {
    'circlemove-bottom-to-top-left' : 'circlemove-top-to-bottom-right',
    'circlemove2-bottom-to-top-left' : 'circlemove2-top-to-bottom-right',
    'circlemove-bottom-to-top-right' : 'circlemove-top-to-bottom-left',
    'circlemove2-bottom-to-top-right' : 'circlemove2-top-to-bottom-left',
    'circlemove-top-to-bottom-left' : 'circlemove-bottom-to-top-right',
    'circlemove2-top-to-bottom-left' : 'circlemove2-bottom-to-top-right',
    'circlemove-top-to-bottom-right' : 'circlemove-bottom-to-top-left',
    'circlemove2-top-to-bottom-right' : 'circlemove2-bottom-to-top-left'        
};

const   A1 = 'A1',              C1 = 'C1',              E1 = 'E1',              G1 = 'G1',
                    B2 = 'B2',              D2 = 'D2',              F2 = 'F2',              H2 = 'H2',
        A3 = 'A3',              C3 = 'C3',              E3 = 'E3',              G3 = 'G3',
                    B4 = 'B4',              D4 = 'D4',              F4 = 'F4',              H4 = 'H4',
        A5 = 'A5',              C5 = 'C5',              E5 = 'E5',              G5 = 'G5',
                    B6 = 'B6',              D6 = 'D6',              F6 = 'F6',              H6 = 'H6',
        A7 = 'A7',              C7 = 'C7',              E7 = 'E7',              G7 = 'G7',
                    B8 = 'B8',              D8 = 'D8',              F8 = 'F8',              H8 = 'H8';
                       

function direction_animation(origin_move, destiny_move){     
    try_direction(origin_move, destiny_move, A1, C3, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, C1, A3, CLASS_BOTTOM_TO_TOP_LEFT_2);
    try_direction(origin_move, destiny_move, C1, E3, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, E1, C3, CLASS_BOTTOM_TO_TOP_LEFT_2);
    try_direction(origin_move, destiny_move, E1, G3, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, G1, E3, CLASS_BOTTOM_TO_TOP_LEFT_2);
    
    try_direction(origin_move, destiny_move, B2, D4, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, D2, B4, CLASS_BOTTOM_TO_TOP_LEFT_2);
    try_direction(origin_move, destiny_move, D2, F4, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, F2, D4, CLASS_BOTTOM_TO_TOP_LEFT_2);
    try_direction(origin_move, destiny_move, F2, H4, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, H2, F4, CLASS_BOTTOM_TO_TOP_LEFT_2);
    
    try_direction(origin_move, destiny_move, A3, C1, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    try_direction(origin_move, destiny_move, A3, C5, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, C3, A1, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, C3, E1, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    try_direction(origin_move, destiny_move, C3, A5, CLASS_BOTTOM_TO_TOP_LEFT_2);
    try_direction(origin_move, destiny_move, C3, E5, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, E3, C1, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, E3, G1, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    try_direction(origin_move, destiny_move, E3, C5, CLASS_BOTTOM_TO_TOP_LEFT_2);
    try_direction(origin_move, destiny_move, E3, G5, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, G3, E1, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, G3, E5, CLASS_BOTTOM_TO_TOP_LEFT_2);
    
    try_direction(origin_move, destiny_move, B4, D2, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    try_direction(origin_move, destiny_move, B4, D6, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, D4, B2, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, D4, F2, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    try_direction(origin_move, destiny_move, D4, B6, CLASS_BOTTOM_TO_TOP_LEFT_2);
    try_direction(origin_move, destiny_move, D4, F6, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, F4, D2, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, F4, H2, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    try_direction(origin_move, destiny_move, F4, D6, CLASS_BOTTOM_TO_TOP_LEFT_2);
    try_direction(origin_move, destiny_move, F4, H6, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, H4, F2, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, H4, F6, CLASS_BOTTOM_TO_TOP_LEFT_2);
    
    try_direction(origin_move, destiny_move, A5, C3, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    try_direction(origin_move, destiny_move, A5, C7, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, C5, A3, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, C5, E3, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    try_direction(origin_move, destiny_move, C5, A7, CLASS_BOTTOM_TO_TOP_LEFT_2);
    try_direction(origin_move, destiny_move, C5, E7, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, E5, C3, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, E5, G3, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    try_direction(origin_move, destiny_move, E5, C7, CLASS_BOTTOM_TO_TOP_LEFT_2);
    try_direction(origin_move, destiny_move, E5, G7, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, G5, E3, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, G5, E7, CLASS_BOTTOM_TO_TOP_LEFT_2);
    
    try_direction(origin_move, destiny_move, B6, D4, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    try_direction(origin_move, destiny_move, B6, D8, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, D6, B4, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, D6, F4, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    try_direction(origin_move, destiny_move, D6, B8, CLASS_BOTTOM_TO_TOP_LEFT_2);
    try_direction(origin_move, destiny_move, D6, F8, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, F6, D4, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, F6, H4, CLASS_TOP_TO_BOTTOM_RIGHT_2);    
    try_direction(origin_move, destiny_move, F6, D8, CLASS_BOTTOM_TO_TOP_LEFT_2);
    try_direction(origin_move, destiny_move, F6, H8, CLASS_BOTTOM_TO_TOP_RIGHT_2);
    
    try_direction(origin_move, destiny_move, H6, F4, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, H6, F8, CLASS_BOTTOM_TO_TOP_LEFT_2);
    
    try_direction(origin_move, destiny_move, A7, C5, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    
    try_direction(origin_move, destiny_move, C7, A5, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, C7, E5, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    
    try_direction(origin_move, destiny_move, E7, C5, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, E7, G5, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    
    try_direction(origin_move, destiny_move, G7, E5, CLASS_TOP_TO_BOTTOM_LEFT_2);
    
    try_direction(origin_move, destiny_move, B8, D6, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    
    try_direction(origin_move, destiny_move, D8, B6, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, D8, F6, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    
    try_direction(origin_move, destiny_move, F8, D6, CLASS_TOP_TO_BOTTOM_LEFT_2);
    try_direction(origin_move, destiny_move, F8, H6, CLASS_TOP_TO_BOTTOM_RIGHT_2);
    
    try_direction(origin_move, destiny_move, H8, F6, CLASS_TOP_TO_BOTTOM_LEFT_2);
    
    try_direction(origin_move, destiny_move, A1, B2, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, C1, B2, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, C1, D2, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, E1, D2, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, E1, F2, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, G1, F2, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, G1, H2, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, B2, A1, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, B2, C1, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, B2, A3, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, B2, C3, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, D2, C1, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, D2, E1, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, D2, C3, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, D2, E3, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, F2, E1, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, F2, G1, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, F2, E3, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, F2, G3, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, H2, G1, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, H2, G3, CLASS_BOTTOM_TO_TOP_LEFT);
    
    try_direction(origin_move, destiny_move, A3, B2, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, A3, B4, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, C3, B2, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, C3, D2, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, C3, B4, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, C3, D4, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, E3, D2, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, E3, F2, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, E3, D4, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, E3, F4, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, G3, F2, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, G3, H2, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, G3, F4, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, G3, H4, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, B4, A3, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, B4, C3, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, B4, A5, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, B4, C5, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, D4, C3, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, D4, E3, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, D4, C5, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, D4, E5, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, F4, E3, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, F4, G3, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, F4, E5, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, F4, G5, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, H4, G3, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, H4, G5, CLASS_BOTTOM_TO_TOP_LEFT);
    
    try_direction(origin_move, destiny_move, A5, B4, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, A5, B6, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, C5, B4, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, C5, D4, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, C5, B6, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, C5, D6, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, E5, D4, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, E5, F4, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, E5, D6, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, E5, F6, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, G5, F4, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, G5, H4, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, G5, F6, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, G5, H6, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, B6, A5, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, B6, C5, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, B6, A7, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, B6, C7, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, D6, C5, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, D6, E5, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, D6, C7, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, D6, E7, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, F6, E5, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, F6, G5, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, F6, E7, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, F6, G7, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, H6, G5, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, H6, G7, CLASS_BOTTOM_TO_TOP_LEFT);
    
    try_direction(origin_move, destiny_move, A7, B6, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, A7, B8, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, C7, B6, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, C7, D6, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, C7, B8, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, C7, D8, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, E7, D6, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, E7, F6, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, E7, D8, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, E7, F8, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, G7, F6, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, G7, H6, CLASS_TOP_TO_BOTTOM_RIGHT);
    try_direction(origin_move, destiny_move, G7, F8, CLASS_BOTTOM_TO_TOP_LEFT);
    try_direction(origin_move, destiny_move, G7, H8, CLASS_BOTTOM_TO_TOP_RIGHT);
    
    try_direction(origin_move, destiny_move, B8, A7, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, B8, C7, CLASS_TOP_TO_BOTTOM_RIGHT);
    
    try_direction(origin_move, destiny_move, D8, C7, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, D8, E7, CLASS_TOP_TO_BOTTOM_RIGHT);
    
    try_direction(origin_move, destiny_move, F8, E7, CLASS_TOP_TO_BOTTOM_LEFT);
    try_direction(origin_move, destiny_move, F8, G7, CLASS_TOP_TO_BOTTOM_RIGHT);
    
    try_direction(origin_move, destiny_move, H8, G7, CLASS_TOP_TO_BOTTOM_LEFT);       
                                                    
}

function is_player1(square){     
    return document.getElementById(square).childNodes[0].style.backgroundColor === PLAYER1_COLOR;
}

function try_direction(origin_move, destiny_move, origin, destiny, move_class){ 
    if(origin_move === origin && destiny_move === destiny){        
        var circle_class = is_player1(origin_move) ? OPOSITE_MOVE_CLASS[move_class] : move_class;    
        if(testemunha){ 
            circle_class = OPOSITE_MOVE_CLASS[move_class];
        }
        direction_html(origin_move, destiny_move, circle_class, is_player1);               
    }        
}

function direction_html(origin_id, destiny_id, element_class){ 
    var element = document.getElementById(origin_id).childNodes[0];
    element.classList.remove("circle");
    element.classList.add(element_class);    
    setTimeout(() => {     
        document.getElementById(origin_id).innerHTML = null;
        element.classList.remove(element_class);
        element.classList.add("circle");
        document.getElementById(destiny_id).appendChild(element);          
        try_print_king(destiny_id);         
    }, 3142);       
}

function move_checker(player1, player2, turn, winner){       
    if(origin_moves_queue.length !== 0 && index_queue_moves !== origin_moves_queue.length){ 
        var origin_move = origin_moves_queue[index_queue_moves];
        var destiny_move = destiny_moves_queue[index_queue_moves]; 
        index_queue_moves = index_queue_moves + 1;        
        direction_animation(origin_move, destiny_move); 
        setTimeout(() => { try_catch_checker(origin_move, destiny_move); }, 500); 
        setTimeout(() => { move_checker(player1, player2, turn, winner); }, 4000);   
    } else {
        moves_queue_reset();
        render_html_panel(player1, player2, turn, winner);
    }          
} 

var current_positions = null;

function render_html_checkers(username, player1, player2, positions, turn, winner){     
    if(current_positions === null){  
        moves_queue_reset();
        if(username === player1){                
            player1_boardgame();
        } else {                
            player2_and_viewer_boardgame();
        } 
        Object.keys(positions).forEach(function(position){            
            add_boardgame_checker(position, positions[position]); 
        });            
        render_html_panel(player1, player2, turn, winner);
    } else { 
         move_checker(player1, player2, turn, winner);
    }
    current_positions = positions;    
}

function moves_queue_reset(){
    index_queue_moves = 0;
    origin_moves_queue = [];
    destiny_moves_queue = [];
}

var index_moves = null;
var index_queue_moves;
var origin_moves_queue;
var destiny_moves_queue;
moves_queue_reset();

function request(){
    var url = "GameInteraction?secret_identifier=" + secret_identifier;
    if(index_moves !== null){
        url += "&index_moves=" + index_moves;
    }
    var http_request = new XMLHttpRequest();			
    http_request.open("GET", url, true);			
    http_request.responseType = "responseText";
    http_request.onload = function(e) {
        var raw_attributes = http_request.response;        
        if(raw_attributes === ''){
            window.location = "/CHECKERS2/";            
        } else {
            attributes_to_html(raw_attributes);
        }
    };
    http_request.send();
}

function render_html_panel(player1, player2, turn, winner){         
    var game_panel = document.getElementById("game-panel");           
    
    if(winner !== ''){
        game_panel.style.backgroundColor = winner === 'player1' ? PLAYER1_COLOR : PLAYER2_COLOR;        
    } else {            
        game_panel.style.backgroundColor = turn === 'player1' ? PLAYER1_COLOR : PLAYER2_COLOR;        
    }
       
    var players_panel = document.getElementById("players-panel");     
    players_panel.innerHTML = null;        
    
    if(player1.length > 10 || player2.length > 10){
        
        var player1_panel_div = document.createElement("div");
        player1_panel_div.style.display = "flex";
        player1_panel_div.style.justifyContent = "center";
        
        var player1_panel = document.createElement("h1");    
        player1_panel.style.margin = "1%";
        player1_panel.innerHTML = player1;
        
        player1_panel_div.appendChild(player1_panel);
        
        var vs_panel_div = document.createElement("div");
        vs_panel_div.style.display = "flex";
        vs_panel_div.style.justifyContent = "center";
        
        var vs_panel = document.createElement("h1");    
        vs_panel.style.margin = "1%";
        vs_panel.innerHTML = "X";
        
        vs_panel_div.appendChild(vs_panel);
        
        var player2_panel_div = document.createElement("div");
        player2_panel_div.style.display = "flex";
        player2_panel_div.style.justifyContent = "center";
        
        var player2_panel = document.createElement("h1"); 
        player2_panel.style.margin = "1%";
        player2_panel.innerHTML = player2;
        
        player2_panel_div.appendChild(player2_panel);
        
        
        var player_name_status_panel_div = document.createElement("div");
        player_name_status_panel_div.style.display = "flex";
        player_name_status_panel_div.style.justifyContent = "center";
        
        var player_name_status_panel = document.createElement("h1"); 
        player_name_status_panel.style.margin = "1%";
        if(winner !== ''){
            player_name_status_panel.innerHTML = winner === 'player1' ? player1 : player2;        
        } else {            
            player_name_status_panel.innerHTML = turn === 'player1' ? player1 : player2;        
        }             
        player_name_status_panel_div.appendChild(player_name_status_panel);
        
        var player_status_panel_div = document.createElement("div");
        player_status_panel_div.style.display = "flex";
        player_status_panel_div.style.justifyContent = "center";
        
        var player_status_panel = document.createElement("h1"); 
        player_status_panel.style.margin = "1%";               
        
        if(winner !== ''){
            player_status_panel.innerHTML = 'venceu';
        } else {            
            player_status_panel.innerHTML = 'vez do';
        }                       
        player_status_panel_div.appendChild(player_status_panel);
        
        players_panel.appendChild(player1_panel_div);
        players_panel.appendChild(vs_panel_div);
        players_panel.appendChild(player2_panel_div);
                
        if(winner !== ''){
            players_panel.appendChild(player_name_status_panel_div);
            players_panel.appendChild(player_status_panel_div);       
        } else {            
            players_panel.appendChild(player_status_panel_div);
            players_panel.appendChild(player_name_status_panel_div);
        }
        
    } else {
        
        var panel_div = document.createElement("div");
        panel_div.style.display = "flex";
        panel_div.style.justifyContent = "center";
        
        var player1_panel = document.createElement("h1");        
        player1_panel.style.width = "45%";
        player1_panel.style.margin = "1%";
        player1_panel.style.textAlign = "right";
        player1_panel.innerHTML = player1;
        
        var vs = document.createElement("h1");
        vs.style.margin = "1%";
        vs.innerHTML = "X";
        
        var player2_panel = document.createElement("h1");        
        player2_panel.style.width = "45%";
        player2_panel.style.margin = "1%";
        player2_panel.innerHTML = player2;
        
        panel_div.appendChild(player1_panel);
        panel_div.appendChild(vs);
        panel_div.appendChild(player2_panel);                
        
        players_panel.appendChild(panel_div);      
        
        var game_panel_turn_div = document.createElement("div");   
        game_panel_turn_div.style.display = "flex";
        game_panel_turn_div.style.justifyContent = "center";
        
        var game_panel_turn = document.createElement("h1");       
        game_panel_turn.style.margin = "1%";
        game_panel_turn.innerHTML = player2;                
        
        if(winner !== ''){  
            game_panel_turn.innerHTML = (winner === "player1" ? player1 : player2) + ' venceu';             
        } else {                    
            game_panel_turn.innerHTML = 'vez do ' + (turn === "player1" ? player1 : player2);
        }
        
        game_panel_turn_div.appendChild(game_panel_turn);
                        
        players_panel.appendChild(game_panel_turn_div);   
    }
    setHeights();
}
var testemunha = true;
function attributes_to_html(raw_attributes){                  
    var username_index = 'me={'.length;
    var username_length = raw_attributes.indexOf('}') - username_index;        
    var username = raw_attributes.substr(username_index, username_length);           
    raw_attributes = raw_attributes.substr(raw_attributes.indexOf('}') + 1);
    
    var player1_index = 'player1={'.length;
    var player1_length = raw_attributes.indexOf('}') - player1_index;        
    var player1 = raw_attributes.substr(player1_index, player1_length);           
    raw_attributes = raw_attributes.substr(raw_attributes.indexOf('}') + 1);
    
    var player2_index = 'player2={'.length;
    var player2_length = raw_attributes.indexOf('}') - player2_index;        
    var player2 = raw_attributes.substr(player2_index, player2_length);            
    raw_attributes = raw_attributes.substr(raw_attributes.indexOf('}') + 1);
    
    testemunha = username !== player1 && username !== player2;    
    
    var turn_index = 'turn={'.length;
    var turn_length = raw_attributes.indexOf('}') - turn_index;  
    var turn = raw_attributes.substr(turn_index, turn_length);    
    raw_attributes = raw_attributes.substr(raw_attributes.indexOf('}') + 1);
    
    var raw_positions_index = 'positions={'.length;
    var raw_positions_length = raw_attributes.indexOf('}') - raw_positions_index;  
    var raw_positions = raw_attributes.substr(raw_positions_index, raw_positions_length).split(',');  
    raw_positions = raw_positions.length === 1 && raw_positions[0] === '' ? [] : raw_positions;   
    raw_attributes = raw_attributes.substr(raw_attributes.indexOf('}') + 1);         
    
    var positions = [];
    for(var i = 0; i < raw_positions.length; i++){      
        var position = raw_positions[i];
        var position_index = (position + '={').length;
        var position_length = raw_attributes.indexOf('}') - position_index;  
        var position_value = raw_attributes.substr(position_index, position_length);
        positions[position] = position_value;
        raw_attributes = raw_attributes.substr(raw_attributes.indexOf('}') + 1);
    }    
        
    var raw_winner_index = 'winner={'.length;
    var raw_winner_length = raw_attributes.indexOf('}') - raw_winner_index;  
    var winner = raw_attributes.substr(raw_winner_index, raw_winner_length);      
    raw_attributes = raw_attributes.substr(raw_attributes.indexOf('}') + 1);               
    
    var raw_moves_index = 'moves={'.length;
    var raw_moves_length = raw_attributes.indexOf('}') - raw_moves_index;  
    var raw_moves = raw_attributes.substr(raw_moves_index, raw_moves_length).split(',');
    raw_moves = raw_moves.length === 1 && raw_moves[0] === '' ? [] : raw_moves;
    raw_attributes = raw_attributes.substr(raw_attributes.indexOf('}') + 1);
    
    moves = [];
    for(var i = 0; i < raw_moves.length; i++){ 
        var move = raw_moves[i];
        var move_index = (move + '={').length;
        var move_length = raw_attributes.indexOf('}') - move_index;  
        var move_value = raw_attributes.substr(move_index, move_length);
        moves[move] = move_value.split(',');
        raw_attributes = raw_attributes.substr(raw_attributes.indexOf('}') + 1);
    }       
               
    var origin_moves = [];
    if(raw_attributes.indexOf('origin_moves=[') !== -1){
        var origin_moves_index = 'origin_moves=['.length;
        var origin_moves_length = raw_attributes.indexOf(']') - origin_moves_index;  
        var origin_moves_value = raw_attributes.substr(origin_moves_index, origin_moves_length).split(', ');      
        origin_moves = origin_moves_value.length === 1 && origin_moves_value[0] === '' ? [] : origin_moves_value;       
        raw_attributes = raw_attributes.substr(raw_attributes.indexOf(']') + 1);     
    }

    var destiny_moves = [];
    if(raw_attributes.indexOf('destiny_moves=[') !== -1){
        var destiny_moves_index = 'destiny_moves=['.length;
        var destiny_moves_length = raw_attributes.indexOf(']') - destiny_moves_index;  
        var destiny_moves_value = raw_attributes.substr(destiny_moves_index, destiny_moves_length).split(', ');  
        destiny_moves = destiny_moves_value.length === 1 && destiny_moves_value[0] === '' ? [] : destiny_moves_value;   
        raw_attributes = raw_attributes.substr(raw_attributes.indexOf(']') + 1);    
    }
    if(destiny_moves.length > 0){
        if(index_moves === null){
            index_moves = destiny_moves.length - 1;
        } else {
            index_moves = index_moves + destiny_moves.length;
        }
    }       

    for(var index = 0; index !== destiny_moves.length; index = index + 1){
        origin_moves_queue[origin_moves_queue.length] = origin_moves[index];
        destiny_moves_queue[destiny_moves_queue.length] = destiny_moves[index];
    }    
    
    var contact_index = 'contact=['.length;
    var contact_length = raw_attributes.indexOf(']') - contact_index;        
    var contact = raw_attributes.substr(contact_index, contact_length);        
    contact = contact === '' ? [] : contact.split(', ');
    raw_attributes = raw_attributes.substr(raw_attributes.indexOf(']') + 1);    
    
    var block_index = 'block=['.length;
    var block_length = raw_attributes.indexOf(']') - block_index;        
    var block = raw_attributes.substr(block_index, block_length);        
    block = block === '' ? [] : block.split(', ');
    raw_attributes = raw_attributes.substr(raw_attributes.indexOf(']') + 1);
    
    var online_index = 'online=['.length;
    var online_length = raw_attributes.indexOf(']') - online_index;        
    var online = raw_attributes.substr(online_index, online_length);        
    online = online === '' ? [] : online.split(', ');
    raw_attributes = raw_attributes.substr(raw_attributes.indexOf(']') + 1);
   
    var viewers_index = 'viewers=['.length;
    var viewers_length = raw_attributes.indexOf(']') - viewers_index;  
    var viewers = raw_attributes.substr(viewers_index, viewers_length);
    viewers = viewers === '' ? [] : viewers.split(', ');                
    raw_attributes = raw_attributes.substr(raw_attributes.indexOf(']') + 1);                         
    
    var game_players = [player1, player2];
    while(viewers.length){
        game_players.push(viewers.pop());
    }       
        
    render_html_contacts(online, contact, block, game_players);
    render_html_chat(raw_attributes);    
    render_html_checkers(username, player1, player2, positions, turn, winner);   
}