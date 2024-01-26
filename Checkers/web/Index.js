/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * document.getElementById("main").innerHTML = result;
 * $('#main').html(result).fadeIn();
 */

function visitor() {

    $(document).ready(function () {
        $.get('IndexController?action=visitor', function (result) {
           $('#main').html(result);
        });
    });

}

function login(){
    var id = document.getElementById('id').value;
    var pass = document.getElementById('pass').value;
    
     $(document).ready(function () {
        $.get('IndexController?action=login&id='+id+"&pass="+pass, function (result) {
           $('#main').html(result);
        });
    });
}

function createAccount(){   
  
    var id = document.getElementById('new_account').value;
    var pass = document.getElementById('new_pass').value;
    var confirmPass = document.getElementById('confirm_pass').value;
    
     $(document).ready(function () {
        $.get('IndexController?action=createAccount&new_account='+id+"&new_pass="+pass+"&confirm_pass="+confirmPass, function (result) {           
           $('#main').html(result);
        });
    });
}
