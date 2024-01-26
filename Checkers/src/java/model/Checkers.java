/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lucas
 */
public class Checkers {

    public static String updateStatePlay1(String oldState, int position) {
        StringBuilder state = new StringBuilder().append(oldState);        
        if(state.charAt(position) == 'V' || state.charAt(position) == 'R'){           
            state.setCharAt(position, Character.toLowerCase(state.charAt(position)));  
            return state.toString();
        }
        
        if (state.charAt(position) == 'p') { //se há uma peça selecionada
            if (position > 7) {
                if (position % 8 < 4) {                    
                    if (position % 4 != 0) { //nw
                        if ((state.charAt(position - 4) == 'b' || state.charAt(position - 4) == 'w') && state.charAt(position - 9) == 'R') {
                            state.setCharAt(position - 9, 'p');
                            state.setCharAt(position - 4, 'p');
                            state.setCharAt(position, 'r');
                            return state.toString();
                        }
                    } 

                        if (position % 4 != 3) { //ne                       
                        if ((state.charAt(position - 3) == 'b' || state.charAt(position - 3) == 'w') && state.charAt(position - 7) == 'R') {
                            state.setCharAt(position - 7, 'p');
                            state.setCharAt(position - 3, 'p');
                            state.setCharAt(position, 'r');
                            return state.toString();
                        }
                    }

                } else {
                    if (position % 4 != 0) { //nw
                        if ((state.charAt(position - 5) == 'b' || state.charAt(position - 5) == 'w') && state.charAt(position - 9) == 'R') {
                            state.setCharAt(position - 9, 'p');
                            state.setCharAt(position - 5, 'p');
                            state.setCharAt(position, 'r');
                            return state.toString();
                        }
                    }  if (position % 4 != 3) { //ne
                        if ((state.charAt(position - 4) == 'b' || state.charAt(position - 4) == 'w') && state.charAt(position - 7) == 'R') {
                            state.setCharAt(position - 7, 'p');
                            state.setCharAt(position - 4, 'p');
                            state.setCharAt(position, 'r');
                            return state.toString();
                        }
                    }

                }
            }
            if (position < 24) {
                if (position % 8 < 4) {
                    if (position % 4 != 0) {//sw
                        if ((state.charAt(position + 4) == 'b' || state.charAt(position + 4) == 'w') && (state.charAt(position + 7) == 'R' || state.charAt(position + 7) == 'V')) {
                            if (state.charAt(position + 7) == 'R') {
                                state.setCharAt(position, 'r');
                            } else if (state.charAt(position + 7) == 'V') {

                                state.setCharAt(position, 'v');

                            }
                            state.setCharAt(position + 7, 'p');
                            state.setCharAt(position + 4, 'p');

                            return state.toString();
                        }
                    }
                    if (position % 4 != 3) {//se
                        if ((state.charAt(position + 5) == 'b' || state.charAt(position + 5) == 'w') && (state.charAt(position + 9) == 'R' || state.charAt(position + 9) == 'V')) {
                            if (state.charAt(position + 9) == 'R') {
                                state.setCharAt(position, 'r');
                            } else if (state.charAt(position + 9) == 'V') {
                                state.setCharAt(position, 'v');
                            }
                            state.setCharAt(position + 9, 'p');
                            state.setCharAt(position + 5, 'p');

                            return state.toString();
                        }
                    }
                } else {
                    if (position % 4 != 0) {//sw
                        if ((state.charAt(position + 3) == 'b' || state.charAt(position + 3) == 'w') && (state.charAt(position + 7) == 'R' || state.charAt(position + 7) == 'V')) {
                            if (state.charAt(position + 7) == 'R') {
                                state.setCharAt(position, 'r');
                            } else if (state.charAt(position + 7) == 'V') {
                                state.setCharAt(position, 'v');
                            }
                            state.setCharAt(position + 7, 'p');
                            state.setCharAt(position + 3, 'p');

                            return state.toString();
                        }
                    }
                    if (position % 4 != 3) {//se
                        if ((state.charAt(position + 4) == 'b' || state.charAt(position + 4) == 'w') && (state.charAt(position + 9) == 'R' || state.charAt(position + 9) == 'V')) {
                            if (state.charAt(position + 9) == 'R') {
                                state.setCharAt(position, 'r');
                            } else if (state.charAt(position + 9) == 'V') {
                                state.setCharAt(position, 'v');
                            }
                            state.setCharAt(position + 9, 'p');
                            state.setCharAt(position + 4, 'p');

                            return state.toString();
                        }
                    }
                }
            }
            if (position > 3) { //tentar fazer um movimento com a dama
                if (position % 8 < 4) {
                    if (position % 4 == 3) { //nw
                        if (state.charAt(position - 4) == 'R') {
                            state.setCharAt(position - 4, 'p');
                            state.setCharAt(position, 'r');
                            return state.toString();
                        }
                    } else { //ne
                        if (state.charAt(position - 3) == 'R') {
                            state.setCharAt(position - 3, 'p');
                            state.setCharAt(position, 'r');
                            return state.toString();
                        }
                        if (state.charAt(position - 4) == 'R') {
                            state.setCharAt(position - 4, 'p');
                            state.setCharAt(position, 'r');
                            return state.toString();
                        }

                    }
                } else {
                    if (position % 4 == 0) { //nw
                        if (state.charAt(position - 4) == 'R') {
                            state.setCharAt(position - 4, 'p');
                            state.setCharAt(position, 'r');
                            return state.toString();
                        }
                    } else { //ne
                        if (state.charAt(position - 5) == 'R') {
                            state.setCharAt(position - 5, 'p');
                            state.setCharAt(position, 'r');
                            return state.toString();
                        }
                        if (state.charAt(position - 4) == 'R') {
                            state.setCharAt(position - 4, 'p');
                            state.setCharAt(position, 'r');
                            return state.toString();
                        }

                    }
                }
            }
            if (position < 28) {//tenta fazer um movimento
                if (position % 8 < 4) {
                    if (position % 4 == 3) { //nw
                        if (state.charAt(position + 4) == 'R' || state.charAt(position + 4) == 'V') {
                            if (state.charAt(position + 4) == 'R') {
                                state.setCharAt(position, 'r');
                            }
                            if (state.charAt(position + 4) == 'V') {
                                state.setCharAt(position, 'v');
                            }
                            state.setCharAt(position + 4, 'p');
                            return state.toString();
                        }
                    } else { //ne
                        if (state.charAt(position + 4) == 'R' || state.charAt(position + 4) == 'V') {
                            if (state.charAt(position + 4) == 'R') {
                                state.setCharAt(position, 'r');
                            }
                            if (state.charAt(position + 4) == 'V') {

                                state.setCharAt(position, 'v');

                            }
                            state.setCharAt(position + 4, 'p');
                            return state.toString();
                        }
                        if (state.charAt(position + 5) == 'R' || state.charAt(position + 5) == 'V') {
                            if (state.charAt(position + 5) == 'R') {
                                state.setCharAt(position, 'r');
                            }
                            if (state.charAt(position + 5) == 'V') {

                                state.setCharAt(position, 'v');

                            }
                            state.setCharAt(position + 5, 'p');
                            return state.toString();
                        }
                    }
                } else {
                    if (position % 4 == 0) { //nw
                        if (state.charAt(position + 4) == 'R' || state.charAt(position + 4) == 'V') {
                            if (state.charAt(position + 4) == 'R') {
                                state.setCharAt(position, 'r');
                            }
                            if (state.charAt(position + 4) == 'V') {

                                state.setCharAt(position, 'v');

                            }
                            state.setCharAt(position + 4, 'p');
                            return state.toString();
                        }
                    } else { //ne
                        if (state.charAt(position + 4) == 'R' || state.charAt(position + 4) == 'V') {
                            if (state.charAt(position + 4) == 'R') {
                                state.setCharAt(position, 'r');
                            }
                            if (state.charAt(position + 4) == 'V') {

                                state.setCharAt(position, 'v');

                            }
                            state.setCharAt(position + 4, 'p');
                            return state.toString();
                        }
                        if (state.charAt(position + 3) == 'R' || state.charAt(position + 3) == 'V') {
                            if (state.charAt(position + 3) == 'R') {
                                state.setCharAt(position, 'r');
                            }
                            if (state.charAt(position + 3) == 'V') {

                                state.setCharAt(position, 'v');

                            }
                            state.setCharAt(position + 3, 'p');
                            return state.toString();
                        }
                    }
                }
            }
        }

        if (state.charAt(position) == 'v') {
            if (position > 3 && !isObrigatorioComerRed(state.toString())) { // se a peça pode ser selecionada
                if (position % 8 < 4) {
                    if (position % 4 == 3) {
                        if (state.charAt(position - 4) == 'p') {
                            state.setCharAt(position, 'V');

                            return state.toString();

                        }
                    } else if (state.charAt(position - 3) == 'p' || state.charAt(position - 4) == 'p') {
                        state.setCharAt(position, 'V');

                        return state.toString();

                    }
                } else {
                    if (position % 4 == 0) {
                        if (state.charAt(position - 4) == 'p') {
                            state.setCharAt(position, 'V');

                            return state.toString();

                        }
                    } else if (state.charAt(position - 5) == 'p' || state.charAt(position - 4) == 'p') {
                        state.setCharAt(position, 'V');

                        return state.toString();

                    }
                }
            }

            if (position > 7) {
                if (position % 8 < 4) {
                    if (position % 4 != 0) {
                        if ((state.charAt(position - 4) == 'b' || state.charAt(position - 4) == 'w') && state.charAt(position - 9) == 'p') {
                            state.setCharAt(position, 'V');

                            return state.toString();

                        }
                    }
                    if (position % 4 != 3) {
                        if ((state.charAt(position - 3) == 'b' || state.charAt(position - 3) == 'w') && state.charAt(position - 7) == 'p') {
                            state.setCharAt(position, 'V');

                            return state.toString();

                        }
                    }

                } else {
                    if (position % 4 != 0) {
                        if ((state.charAt(position - 5) == 'b' || state.charAt(position - 5) == 'w') && state.charAt(position - 9) == 'p') {
                            state.setCharAt(position, 'V');

                            return state.toString();

                        }
                    }
                    if (position % 4 != 3) {
                        if ((state.charAt(position - 4) == 'b' || state.charAt(position - 4) == 'w') && state.charAt(position - 7) == 'p') {
                            state.setCharAt(position, 'V');

                            return state.toString();

                        }
                    }
                }
            }
        }

        if (state.charAt(position) == 'r') {// se a dama pode ser selecionada
            if (position > 3 && !isObrigatorioComerRed(state.toString())) { // se a peça pode ser selecionada
                if (position % 8 < 4) {
                    if (position % 4 == 3) {
                        if (state.charAt(position - 4) == 'p') {
                            state.setCharAt(position, 'R');

                            return state.toString();

                        }
                    } else if (state.charAt(position - 3) == 'p' || state.charAt(position - 4) == 'p') {
                        state.setCharAt(position, 'R');

                        return state.toString();
                    }
                } else {
                    if (position % 4 == 0) {
                        if (state.charAt(position - 4) == 'p') {
                            state.setCharAt(position, 'R');

                            return state.toString();

                        }
                    } else if (state.charAt(position - 5) == 'p' || state.charAt(position - 4) == 'p') {
                        state.setCharAt(position, 'R');

                        return state.toString();

                    }
                }
            }

            if (position < 28 && !isObrigatorioComerRed(state.toString())) { // se a peça pode ser selecionada
                if (position % 8 < 4) {
                    if (position % 4 == 3) {
                        if (state.charAt(position + 4) == 'p') {
                            state.setCharAt(position, 'R');

                            return state.toString();

                        }
                    } else if (state.charAt(position + 4) == 'p' || state.charAt(position + 5) == 'p') {
                        state.setCharAt(position, 'R');

                        return state.toString();

                    }
                } else {
                    if (position % 4 == 0) {
                        if (state.charAt(position + 4) == 'p') {
                            state.setCharAt(position, 'R');

                            return state.toString();

                        }
                    } else if (state.charAt(position + 3) == 'p' || state.charAt(position + 4) == 'p') {
                        state.setCharAt(position, 'R');

                        return state.toString();

                    }
                }
            }

            if (position > 7) {
                if (position % 8 < 4) {
                    if (position % 4 != 0) {
                        if ((state.charAt(position - 4) == 'b' || state.charAt(position - 4) == 'w') && state.charAt(position - 9) == 'p') {
                            state.setCharAt(position, 'R');

                            return state.toString();

                        }
                    }
                    if (position % 4 != 3) {
                        if ((state.charAt(position - 3) == 'b' || state.charAt(position - 3) == 'w') && state.charAt(position - 7) == 'p') {
                            state.setCharAt(position, 'R');

                            return state.toString();

                        }
                    }

                } else {
                    if (position % 4 != 0) {
                        if ((state.charAt(position - 5) == 'b' || state.charAt(position - 5) == 'w') && state.charAt(position - 9) == 'p') {
                            state.setCharAt(position, 'R');

                            return state.toString();

                        }
                    }
                    if (position % 4 != 3) {
                        if ((state.charAt(position - 4) == 'b' || state.charAt(position - 4) == 'w') && state.charAt(position - 7) == 'p') {
                            state.setCharAt(position, 'R');

                            return state.toString();

                        }
                    }
                }
            }
            if (position < 24) {
                if (position % 8 < 4) {
                    if (position % 4 != 0) {
                        if ((state.charAt(position + 4) == 'b' || state.charAt(position + 4) == 'w') && state.charAt(position + 7) == 'p') {
                            state.setCharAt(position, 'R');

                            return state.toString();

                        }
                    }
                    if (position % 4 != 3) {
                        if ((state.charAt(position + 5) == 'b' || state.charAt(position + 5) == 'w') && state.charAt(position + 9) == 'p') {
                            state.setCharAt(position, 'R');

                            return state.toString();

                        }
                    }

                } else {
                    if (position % 4 != 0) {
                        if ((state.charAt(position + 3) == 'b' || state.charAt(position + 3) == 'w') && state.charAt(position + 7) == 'p') {
                            state.setCharAt(position, 'R');

                            return state.toString();

                        }
                    }
                    if (position % 4 != 3) {
                        if ((state.charAt(position + 4) == 'b' || state.charAt(position + 4) == 'w') && state.charAt(position + 9) == 'p') {
                            state.setCharAt(position, 'R');

                            return state.toString();

                        }
                    }
                }
            }
        }
        return state.toString();
    }

    public static String updateStatePlay2(String oldState, int position) {
        StringBuilder state = new StringBuilder().append(oldState);
        
        if(state.charAt(position) == 'B' || state.charAt(position) == 'W'){
            state.setCharAt(position, Character.toLowerCase(state.charAt(position)));   
            return state.toString();
        }
        
        if (state.charAt(position) == 'p') { //se há uma peça selecionada
            if (position < 24) {
                if (position % 8 < 4) {
                    if (position % 4 != 0) { //nw
                        if ((state.charAt(position + 4) == 'v' || state.charAt(position + 4) == 'r') && state.charAt(position + 7) == 'W') {
                            state.setCharAt(position + 7, 'p');
                            state.setCharAt(position + 4, 'p');
                            state.setCharAt(position, 'w');
                            return state.toString();
                        }
                    } if (position % 4 != 3) { //ne 
                        if ((state.charAt(position + 5) == 'v' || state.charAt(position + 5) == 'r') && state.charAt(position + 9) == 'W') {
                            state.setCharAt(position + 9, 'p');
                            state.setCharAt(position + 5, 'p');
                            state.setCharAt(position, 'w');
                            return state.toString();
                        }
                    }

                } else {
                    if (position % 4 != 3) { //nw 
                        if ((state.charAt(position + 4) == 'v' || state.charAt(position + 4) == 'r') && state.charAt(position + 9) == 'W') {
                            state.setCharAt(position + 9, 'p');
                            state.setCharAt(position + 4, 'p');
                            state.setCharAt(position, 'w');
                            return state.toString();
                        }
                    } if (position % 4 != 0) { //ne 
                        if ((state.charAt(position + 3) == 'v' || state.charAt(position + 3) == 'r') && state.charAt(position + 7) == 'W') {
                            state.setCharAt(position + 7, 'p');
                            state.setCharAt(position + 3, 'p');
                            state.setCharAt(position, 'w');
                            return state.toString();
                        }
                    }

                }
            }
            if (position > 7) {
                if (position % 8 < 4) {
                    if (position % 4 != 0) {//sw
                        if ((state.charAt(position - 4) == 'v' || state.charAt(position - 4) == 'r') && (state.charAt(position - 9) == 'W' || state.charAt(position - 9) == 'B')) {
                            if (state.charAt(position - 9) == 'W') {
                                state.setCharAt(position, 'w');
                            } else if (state.charAt(position - 9) == 'B') {
                                state.setCharAt(position, 'b');
                            }
                            state.setCharAt(position - 9, 'p');
                            state.setCharAt(position - 4, 'p');

                            return state.toString();
                        }
                    }
                    if (position % 4 != 3) {//se
                        if ((state.charAt(position - 3) == 'v' || state.charAt(position - 3) == 'r') && (state.charAt(position - 7) == 'W' || state.charAt(position - 7) == 'B')) {
                            if (state.charAt(position - 7) == 'W') {
                                state.setCharAt(position, 'w');
                            } else if (state.charAt(position - 7) == 'B') {
                                state.setCharAt(position, 'b');
                            }
                            state.setCharAt(position - 7, 'p');
                            state.setCharAt(position - 3, 'p');

                            return state.toString();
                        }
                    }
                } else {
                    if (position % 4 != 3) {//sw
                        if ((state.charAt(position - 4) == 'v' || state.charAt(position - 4) == 'r') && (state.charAt(position - 7) == 'W' || state.charAt(position - 7) == 'B')) {
                            if (state.charAt(position - 7) == 'W') {
                                state.setCharAt(position, 'w');
                            } else if (state.charAt(position - 7) == 'B') {
                                state.setCharAt(position, 'b');
                            }
                            state.setCharAt(position - 7, 'p');
                            state.setCharAt(position - 4, 'p');

                            return state.toString();
                        }
                    }
                    if (position % 4 != 0) {//se
                        if ((state.charAt(position - 5) == 'v' || state.charAt(position - 5) == 'r') && (state.charAt(position - 9) == 'W' || state.charAt(position - 9) == 'B')) {
                            if (state.charAt(position - 9) == 'W') {
                                state.setCharAt(position, 'w');
                            } else if (state.charAt(position - 9) == 'B') {
                                state.setCharAt(position, 'b');
                            }
                            state.setCharAt(position - 9, 'p');
                            state.setCharAt(position - 5, 'p');

                            return state.toString();
                        }
                    }
                }
            }
            if (position < 28) { //tentar fazer um movimento com a dama
                if (position % 8 < 4) {
                    if (position % 4 == 3) { //nw
                        if (state.charAt(position + 4) == 'W') {
                            state.setCharAt(position + 4, 'p');
                            state.setCharAt(position, 'w');
                            return state.toString();
                        }
                    } else { //ne
                        if (state.charAt(position + 5) == 'W') {
                            state.setCharAt(position + 5, 'p');
                            state.setCharAt(position, 'w');
                            return state.toString();
                        }
                        if (state.charAt(position + 4) == 'W') {
                            state.setCharAt(position + 4, 'p');
                            state.setCharAt(position, 'w');
                            return state.toString();
                        }

                    }
                } else {
                    if (position % 4 == 0) { //nw
                        if (state.charAt(position + 4) == 'W') {
                            state.setCharAt(position + 4, 'p');
                            state.setCharAt(position, 'w');
                            return state.toString();
                        }
                    } else { //ne
                        if (state.charAt(position + 3) == 'W') {
                            state.setCharAt(position + 3, 'p');
                            state.setCharAt(position, 'w');
                            return state.toString();
                        }
                        if (state.charAt(position + 4) == 'W') {
                            state.setCharAt(position + 4, 'p');
                            state.setCharAt(position, 'w');
                            return state.toString();
                        }

                    }
                }
            }
            if (position > 3) {//tenta fazer um movimento
                if (position % 8 < 4) {
                    if (position % 4 == 3) { //nw
                        if (state.charAt(position - 4) == 'W' || state.charAt(position - 4) == 'B') {
                            if (state.charAt(position - 4) == 'W') {
                                state.setCharAt(position, 'w');
                            }
                            if (state.charAt(position - 4) == 'B') {

                                state.setCharAt(position, 'b');

                            }
                            state.setCharAt(position - 4, 'p');
                            return state.toString();
                        }
                    } else { //ne
                        if (state.charAt(position - 4) == 'W' || state.charAt(position - 4) == 'B') {
                            if (state.charAt(position - 4) == 'W') {
                                state.setCharAt(position, 'w');
                            }
                            if (state.charAt(position - 4) == 'B') {

                                state.setCharAt(position, 'b');

                            }
                            state.setCharAt(position - 4, 'p');
                            return state.toString();
                        }
                        if (state.charAt(position - 3) == 'W' || state.charAt(position - 3) == 'B') {
                            if (state.charAt(position - 3) == 'W') {
                                state.setCharAt(position, 'w');
                            }
                            if (state.charAt(position - 3) == 'B') {

                                state.setCharAt(position, 'b');

                            }
                            state.setCharAt(position - 3, 'p');
                            return state.toString();
                        }
                    }
                } else {
                    if (position % 4 == 0) { //nw
                        if (state.charAt(position - 4) == 'W' || state.charAt(position - 4) == 'B') {
                            if (state.charAt(position - 4) == 'W') {
                                state.setCharAt(position, 'w');
                            }
                            if (state.charAt(position - 4) == 'B') {

                                state.setCharAt(position, 'b');

                            }
                            state.setCharAt(position - 4, 'p');
                            return state.toString();
                        }
                    } else { //ne
                        if (state.charAt(position - 4) == 'W' || state.charAt(position - 4) == 'B') {
                            if (state.charAt(position - 4) == 'W') {
                                state.setCharAt(position, 'w');
                            }
                            if (state.charAt(position - 4) == 'B') {

                                state.setCharAt(position, 'b');

                            }
                            state.setCharAt(position - 4, 'p');
                            return state.toString();
                        }
                        if (state.charAt(position - 5) == 'W' || state.charAt(position - 5) == 'B') {
                            if (state.charAt(position - 5) == 'W') {
                                state.setCharAt(position, 'w');
                            }
                            if (state.charAt(position - 5) == 'B') {

                                state.setCharAt(position, 'b');

                            }
                            state.setCharAt(position - 5, 'p');
                            return state.toString();
                        }
                    }
                }
            }
        }

        if (state.charAt(position) == 'b') {

            if (position < 28 && !isObrigatorioComerWhite(state.toString())) { // se a peça pode ser selecionada

                if (position % 8 < 4) {
                    if (position % 4 == 3) {
                        if (state.charAt(position + 4) == 'p') {
                            state.setCharAt(position, 'B');

                            return state.toString();

                        }
                    } else if (state.charAt(position + 4) == 'p' || state.charAt(position + 5) == 'p') {
                        state.setCharAt(position, 'B');

                        return state.toString();

                    }
                } else {
                    if (position % 4 == 0) {
                        if (state.charAt(position + 4) == 'p') {
                            state.setCharAt(position, 'B');

                            return state.toString();

                        }
                    } else if (state.charAt(position + 3) == 'p' || state.charAt(position + 4) == 'p') {
                        state.setCharAt(position, 'B');

                        return state.toString();

                    }
                }
            }

            if (position < 24) { // se pode comer
                if (position % 8 < 4) {
                    if (position % 4 != 0) {//sw
                        if ((state.charAt(position + 4) == 'v' || state.charAt(position + 4) == 'r') && state.charAt(position + 7) == 'p') {
                            state.setCharAt(position, 'B');

                            return state.toString();

                        }
                    }
                    if (position % 4 != 3) {//se
                        if ((state.charAt(position + 5) == 'v' || state.charAt(position + 5) == 'r') && state.charAt(position + 9) == 'p') {
                            state.setCharAt(position, 'B');

                            return state.toString();

                        }
                    }

                } else {
                    if (position % 4 != 0) {//sw
                        if ((state.charAt(position + 3) == 'v' || state.charAt(position + 3) == 'r') && state.charAt(position + 7) == 'p') {
                            state.setCharAt(position, 'B');

                            return state.toString();

                        }
                    }
                    if (position % 4 != 3) {//se
                        if ((state.charAt(position + 4) == 'v' || state.charAt(position + 4) == 'r') && state.charAt(position + 9) == 'p') {
                            state.setCharAt(position, 'B');

                            return state.toString();

                        }
                    }
                }
            }
        }

        if (state.charAt(position) == 'w') {// se a dama pode ser selecionada
            if (position < 24 && !isObrigatorioComerWhite(state.toString())) { // se a peça pode ser selecionada
                if (position % 8 < 4) {
                    if (position % 4 == 3) {
                        if (state.charAt(position + 4) == 'p') {
                            state.setCharAt(position, 'W');

                            return state.toString();

                        }
                    } else if (state.charAt(position + 4) == 'p' || state.charAt(position + 5) == 'p') {
                        state.setCharAt(position, 'W');

                        return state.toString();

                    }
                } else {
                    if (position % 4 == 0) {
                        if (state.charAt(position + 4) == 'p') {
                            state.setCharAt(position, 'W');

                            return state.toString();

                        }
                    } else if (state.charAt(position + 3) == 'p' || state.charAt(position + 4) == 'p') {
                        state.setCharAt(position, 'W');

                        return state.toString();

                    }
                }
            }
            if (position > 3 && !isObrigatorioComerWhite(state.toString())) { // se a peça pode ser selecionada
                if (position % 8 < 4) {
                    if (position % 4 == 3) {
                        if (state.charAt(position - 4) == 'p') {
                            state.setCharAt(position, 'W');

                            return state.toString();

                        }
                    } else if (state.charAt(position - 3) == 'p' || state.charAt(position - 4) == 'p') {
                        state.setCharAt(position, 'W');

                        return state.toString();

                    }
                } else {
                    if (position % 4 == 0) {
                        if (state.charAt(position - 4) == 'p') {
                            state.setCharAt(position, 'W');

                            return state.toString();

                        }
                    } else if (state.charAt(position - 5) == 'p' || state.charAt(position - 4) == 'p') {
                        state.setCharAt(position, 'W');

                        return state.toString();

                    }
                }
            }

            if (position > 7) {
                if (position % 8 < 4) {
                    if (position % 4 != 0) {
                        if ((state.charAt(position - 4) == 'v' || state.charAt(position - 4) == 'r') && state.charAt(position - 9) == 'p') {
                            state.setCharAt(position, 'W');

                            return state.toString();

                        }
                    }
                    if (position % 4 != 3) {
                        if ((state.charAt(position - 3) == 'v' || state.charAt(position - 3) == 'r') && state.charAt(position - 7) == 'p') {
                            state.setCharAt(position, 'W');

                            return state.toString();

                        }
                    }

                } else {
                    if (position % 4 != 0) {
                        if ((state.charAt(position - 5) == 'v' || state.charAt(position - 5) == 'r') && state.charAt(position - 9) == 'p') {
                            state.setCharAt(position, 'W');

                            return state.toString();

                        }
                    }
                    if (position % 4 != 3) {
                        if ((state.charAt(position - 4) == 'v' || state.charAt(position - 4) == 'r') && state.charAt(position - 7) == 'p') {
                            state.setCharAt(position, 'W');

                            return state.toString();

                        }
                    }
                }
            }
            if (position < 24) {
                if (position % 8 < 4) {
                    if (position % 4 != 0) {
                        if ((state.charAt(position + 4) == 'v' || state.charAt(position + 4) == 'r') && state.charAt(position + 7) == 'p') {
                            state.setCharAt(position, 'W');

                            return state.toString();

                        }
                    }
                    if (position % 4 != 3) {
                        if ((state.charAt(position + 5) == 'v' || state.charAt(position + 5) == 'r') && state.charAt(position + 9) == 'p') {
                            state.setCharAt(position, 'W');

                            return state.toString();

                        }
                    }

                } else {
                    if (position % 4 != 0) {
                        if ((state.charAt(position + 3) == 'v' || state.charAt(position + 3) == 'r') && state.charAt(position + 7) == 'p') {
                            state.setCharAt(position, 'W');

                            return state.toString();

                        }
                    }
                    if (position % 4 != 3) {
                        if ((state.charAt(position + 4) == 'v' || state.charAt(position + 4) == 'r') && state.charAt(position + 9) == 'p') {
                            state.setCharAt(position, 'W');

                            return state.toString();

                        }
                    }
                }
            }
        }
        return state.toString();
    }

    public static boolean isObrigatorioComerRed(String estado) {
        for (int i = 0; i < estado.length(); i++) {
            if (estado.charAt(i) == 'r') { // se for uma dama.
                if (i > 7) {
                    if (i % 8 < 4) {
                        if (i % 4 != 3) {//ne
                            if (estado.charAt(i - 7) == 'p' && (estado.charAt(i - 3) == 'b' || estado.charAt(i - 3) == 'w')) {
                                return true;
                            }
                        }
                        if (i % 4 != 0) {//nw
                            if (estado.charAt(i - 9) == 'p' && (estado.charAt(i - 4) == 'b' || estado.charAt(i - 4) == 'w')) {
                                return true;
                            }
                        }
                    } else {
                        if (i % 4 != 3) {//ne
                            if (estado.charAt(i - 7) == 'p' && (estado.charAt(i - 4) == 'b' || estado.charAt(i - 4) == 'w')) {
                                return true;
                            }
                        }
                        if (i % 4 != 0) {//nw
                            if (estado.charAt(i - 9) == 'p' && (estado.charAt(i - 5) == 'b' || estado.charAt(i - 5) == 'w')) {
                                return true;
                            }
                        }
                    }
                }
                if (i < 24) {
                    if (i % 8 < 4) {
                        if (i % 4 != 3) {//se
                            if (estado.charAt(i + 9) == 'p' && (estado.charAt(i + 5) == 'b' || estado.charAt(i + 5) == 'w')) {
                                return true;
                            }
                        }
                        if (i % 4 != 0) {//sw
                            if (estado.charAt(i + 7) == 'p' && (estado.charAt(i + 4) == 'b' || estado.charAt(i + 4) == 'w')) {
                                return true;
                            }
                        }
                    } else {
                        if (i % 4 != 3) {//se
                            if (estado.charAt(i + 9) == 'p' && (estado.charAt(i + 4) == 'b' || estado.charAt(i + 4) == 'w')) {
                                return true;
                            }
                        }
                        if (i % 4 != 0) {//sw
                            if (estado.charAt(i + 7) == 'p' && (estado.charAt(i + 3) == 'b' || estado.charAt(i + 3) == 'w')) {
                                return true;
                            }
                        }
                    }
                }
            } else if (estado.charAt(i) == 'v') { // se for uma peça
                if (i > 7) {
                    if (i % 8 < 4) {
                        if (i % 4 != 3) {//ne
                            if (estado.charAt(i - 7) == 'p' && (estado.charAt(i - 3) == 'b' || estado.charAt(i - 3) == 'w')) {
                                return true;
                            }
                        }
                        if (i % 4 != 0) {//nw
                            if (estado.charAt(i - 9) == 'p' && (estado.charAt(i - 4) == 'b' || estado.charAt(i - 4) == 'w')) {
                                return true;
                            }
                        }
                    } else {
                        if (i % 4 != 3) {//ne
                            if (estado.charAt(i - 7) == 'p' && (estado.charAt(i - 4) == 'b' || estado.charAt(i - 4) == 'w')) {
                                return true;
                            }
                        }
                        if (i % 4 != 0) {//nw
                            if (estado.charAt(i - 9) == 'p' && (estado.charAt(i - 5) == 'b' || estado.charAt(i - 5) == 'w')) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isObrigatorioComerWhite(String estado) {
        for (int i = 0; i < estado.length(); i++) {
            if (estado.charAt(i) == 'w') { // se for uma dama.
                if (i > 7) {
                    if (i % 8 < 4) {
                        if (i % 4 != 3) {//ne
                            if (estado.charAt(i - 7) == 'p' && (estado.charAt(i - 3) == 'v' || estado.charAt(i - 3) == 'r')) {
                                return true;
                            }
                        }
                        if (i % 4 != 0) {//nw
                            if (estado.charAt(i - 9) == 'p' && (estado.charAt(i - 4) == 'v' || estado.charAt(i - 4) == 'r')) {
                                return true;
                            }
                        }
                    } else {
                        if (i % 4 != 3) {//ne
                            if (estado.charAt(i - 7) == 'p' && (estado.charAt(i - 4) == 'v' || estado.charAt(i - 4) == 'r')) {
                                return true;
                            }
                        }
                        if (i % 4 != 0) {//nw
                            if (estado.charAt(i - 9) == 'p' && (estado.charAt(i - 5) == 'v' || estado.charAt(i - 5) == 'r')) {
                                return true;
                            }
                        }
                    }
                }
                if (i < 24) {
                    if (i % 8 < 4) {
                        if (i % 4 != 3) {//se
                            if (estado.charAt(i + 9) == 'p' && (estado.charAt(i + 5) == 'v' || estado.charAt(i + 5) == 'r')) {
                                return true;
                            }
                        }
                        if (i % 4 != 0) {//sw
                            if (estado.charAt(i + 7) == 'p' && (estado.charAt(i + 4) == 'v' || estado.charAt(i + 4) == 'r')) {
                                return true;
                            }
                        }
                    } else {
                        if (i % 4 != 3) {//se
                            if (estado.charAt(i + 9) == 'p' && (estado.charAt(i + 4) == 'v' || estado.charAt(i + 4) == 'r')) {
                                return true;
                            }
                        }
                        if (i % 4 != 0) {//sw
                            if (estado.charAt(i + 7) == 'p' && (estado.charAt(i + 3) == 'v' || estado.charAt(i + 3) == 'r')) {
                                return true;
                            }
                        }
                    }
                }
            } else if (estado.charAt(i) == 'b') { // se for uma peça
                if (i < 24) {
                    if (i % 8 < 4) {
                        if (i % 4 != 3) {//se
                            if (estado.charAt(i + 9) == 'p' && (estado.charAt(i + 5) == 'v' || estado.charAt(i + 5) == 'r')) {
                                return true;
                            }
                        }
                        if (i % 4 != 0) {//sw
                            if (estado.charAt(i + 7) == 'p' && (estado.charAt(i + 4) == 'v' || estado.charAt(i + 4) == 'r')) {
                                return true;
                            }
                        }
                    } else {
                        if (i % 4 != 3) {//se
                            if (estado.charAt(i + 9) == 'p' && (estado.charAt(i + 4) == 'v' || estado.charAt(i + 4) == 'r')) {
                                return true;
                            }
                        }
                        if (i % 4 != 0) {//sw
                            if (estado.charAt(i + 7) == 'p' && (estado.charAt(i + 3) == 'v' || estado.charAt(i + 3) == 'r')) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;

    }

    public static String checkDamas(String newState) {
        StringBuilder str = new StringBuilder();
        str.append(newState);

        for (int i = 0; i < 4; i++) {

            if (str.charAt(i) == 'v') {
                str.setCharAt(i, 'r');
            }
        }
        for (int i = 28; i < 32; i++) {

            if (str.charAt(i) == 'b') {

                str.setCharAt(i, 'w');
            }
        }

        return str.toString();
    }

    public static String tabuleiroPlayer1(boolean isPlayer1Turn, String estadoX) {
        StringBuilder str = new StringBuilder();
        StringBuilder estado = new StringBuilder();
        StringBuilder preEstado = new StringBuilder();
        estado.append(estadoX);
        preEstado.append(estadoX);
        int bluep = containsBlue(estado.toString());

        if (isPlayer1Turn) { //Se é a vez do jogador

            if (bluep != -1) { // se há alguma peça selecionada

                if (estado.charAt(bluep) == 'R') { // se é uma dama, verifica se pode comer de ré                   
                    if (bluep < 24) {
                        if (bluep % 8 < 4) {
                            if (bluep % 4 != 3) { //se
                                if (estado.charAt(bluep + 9) == 'p' && (estado.charAt(bluep + 5) == 'b' || estado.charAt(bluep + 5) == 'w')) {
                                    estado.setCharAt(bluep + 9, 'P');
                                }
                            }
                            if (bluep % 4 != 0) { //sw
                                if (estado.charAt(bluep + 7) == 'p' && (estado.charAt(bluep + 4) == 'b' || estado.charAt(bluep + 4) == 'w')) {
                                    estado.setCharAt(bluep + 7, 'P');
                                }
                            }
                        } else {
                            if (bluep % 4 != 0) { //se
                                if (estado.charAt(bluep + 9) == 'p' && (estado.charAt(bluep + 3) == 'b' || estado.charAt(bluep + 3) == 'w')) {
                                    estado.setCharAt(bluep + 9, 'P');
                                }
                            }
                            if (bluep % 4 != 3) { //sw
                                if (estado.charAt(bluep + 7) == 'p' && (estado.charAt(bluep + 4) == 'b' || estado.charAt(bluep + 4) == 'w')) {
                                    estado.setCharAt(bluep + 7, 'P');
                                }
                            }
                        }
                    }
                }

                if (bluep > 7) { //verifica se pode comer de frente.

                    if (bluep % 8 < 4) {
                        if (bluep % 4 != 3) { //ne
                            if (estado.charAt(bluep - 7) == 'p' && (estado.charAt(bluep - 3) == 'b' || estado.charAt(bluep - 3) == 'w')) {
                                estado.setCharAt(bluep - 7, 'P');
                            }
                        }
                        if (bluep % 4 != 0) { //nw
                            if (estado.charAt(bluep - 9) == 'p' && (estado.charAt(bluep - 4) == 'b' || estado.charAt(bluep - 4) == 'w')) {
                                estado.setCharAt(bluep - 9, 'P');
                            }
                        }
                    } else {
                        if (bluep % 4 != 3) { //ne
                            if (estado.charAt(bluep - 7) == 'p' && (estado.charAt(bluep - 4) == 'b' || estado.charAt(bluep - 4) == 'w')) {
                                estado.setCharAt(bluep - 7, 'P');
                            }
                        }
                        if (bluep % 4 != 0) { //nw
                            if (estado.charAt(bluep - 9) == 'p' && (estado.charAt(bluep - 5) == 'b' || estado.charAt(bluep - 5) == 'w')) {
                                estado.setCharAt(bluep - 9, 'P');
                            }
                        }
                    }
                }
                if (estado.toString().compareTo(preEstado.toString()) == 0) { // se não da pra comer nada
                    int i = bluep;
                    if (estado.charAt(i) == 'R') {
                        if (i < 28) {
                            if (i % 8 < 4) {
                                if (i % 4 == 3) {
                                    if (estado.charAt(i + 4) == 'p') {
                                        estado.setCharAt(i + 4, 'P');
                                    }
                                } else {
                                    if (estado.charAt(i + 4) == 'p') {
                                        estado.setCharAt(i + 4, 'P');
                                    }
                                    if (estado.charAt(i + 5) == 'p') {
                                        estado.setCharAt(i + 5, 'P');
                                    }
                                }
                            } else {
                                if (i % 4 == 0) {
                                    if (estado.charAt(i + 4) == 'p') {
                                        estado.setCharAt(i + 4, 'P');
                                    }
                                } else {
                                    if (estado.charAt(i + 3) == 'p') {
                                        estado.setCharAt(i + 3, 'P');
                                    }
                                    if (estado.charAt(i + 4) == 'p') {
                                        estado.setCharAt(i + 4, 'P');
                                    }
                                }
                            }
                        }

                    }
                    if (estado.charAt(i) == 'V' || estado.charAt(i) == 'R') {
                        if (i > 3) {
                            if (i % 8 < 4) {
                                if (i % 4 == 3) {
                                    if (estado.charAt(i - 4) == 'p') {
                                        estado.setCharAt(i - 4, 'P');
                                    }
                                } else {
                                    if (estado.charAt(i - 4) == 'p') {
                                        estado.setCharAt(i - 4, 'P');
                                    }
                                    if (estado.charAt(i - 3) == 'p') {
                                        estado.setCharAt(i - 3, 'P');
                                    }
                                }
                            } else {
                                if (i % 4 == 0) {
                                    if (estado.charAt(i - 4) == 'p') {
                                        estado.setCharAt(i - 4, 'P');
                                    }
                                } else {
                                    if (estado.charAt(i - 5) == 'p') {
                                        estado.setCharAt(i - 5, 'P');
                                    }
                                    if (estado.charAt(i - 4) == 'p') {
                                        estado.setCharAt(i - 4, 'P');
                                    }
                                }
                            }
                        }
                    }
                }

            } else { // se não há peças selecionadas então mostrar quais podem ser selecionadas
                for (int i = 0; i < estado.length(); i++) {
                    if (estado.charAt(i) == 'r') { // se for uma dama.
                        if (i > 7) {
                            if (i % 8 < 4) {
                                if (i % 4 != 3) {//ne
                                    if (estado.charAt(i - 7) == 'p' && (estado.charAt(i - 3) == 'b' || estado.charAt(i - 3) == 'w')) {
                                        estado.setCharAt(i, 'S');
                                    }
                                }
                                if (i % 4 != 0) {//nw
                                    if (estado.charAt(i - 9) == 'p' && (estado.charAt(i - 4) == 'b' || estado.charAt(i - 4) == 'w')) {
                                        estado.setCharAt(i, 'S');
                                    }
                                }
                            } else {
                                if (i % 4 != 3) {//ne
                                    if (estado.charAt(i - 7) == 'p' && (estado.charAt(i - 4) == 'b' || estado.charAt(i - 4) == 'w')) {
                                        estado.setCharAt(i, 'S');
                                    }
                                }
                                if (i % 4 != 0) {//nw
                                    if (estado.charAt(i - 9) == 'p' && (estado.charAt(i - 5) == 'b' || estado.charAt(i - 5) == 'w')) {
                                        estado.setCharAt(i, 'S');
                                    }
                                }
                            }
                        }
                        if (i < 24) {
                            if (i % 8 < 4) {
                                if (i % 4 != 3) {//se
                                    if (estado.charAt(i + 9) == 'p' && (estado.charAt(i + 5) == 'b' || estado.charAt(i + 5) == 'w')) {
                                        estado.setCharAt(i, 'S');
                                    }
                                }
                                if (i % 4 != 0) {//sw
                                    if (estado.charAt(i + 7) == 'p' && (estado.charAt(i + 4) == 'b' || estado.charAt(i + 4) == 'w')) {
                                        estado.setCharAt(i, 'S');
                                    }
                                }
                            } else {
                                if (i % 4 != 3) {//se
                                    if (estado.charAt(i + 9) == 'p' && (estado.charAt(i + 4) == 'b' || estado.charAt(i + 4) == 'w')) {
                                        estado.setCharAt(i, 'S');
                                    }
                                }
                                if (i % 4 != 0) {//sw
                                    if (estado.charAt(i + 7) == 'p' && (estado.charAt(i + 3) == 'b' || estado.charAt(i + 3) == 'w')) {
                                        estado.setCharAt(i, 'S');
                                    }
                                }
                            }
                        }
                    } else if (estado.charAt(i) == 'v') { // se for uma peça
                        if (i > 7) {
                            if (i % 8 < 4) {
                                if (i % 4 != 3) {//ne
                                    if (estado.charAt(i - 7) == 'p' && (estado.charAt(i - 3) == 'b' || estado.charAt(i - 3) == 'w')) {
                                        estado.setCharAt(i, 'U');
                                    }
                                }
                                if (i % 4 != 0) {//nw
                                    if (estado.charAt(i - 9) == 'p' && (estado.charAt(i - 4) == 'b' || estado.charAt(i - 4) == 'w')) {
                                        estado.setCharAt(i, 'U');
                                    }
                                }
                            } else {
                                if (i % 4 != 3) {//ne
                                    if (estado.charAt(i - 7) == 'p' && (estado.charAt(i - 4) == 'b' || estado.charAt(i - 4) == 'w')) {
                                        estado.setCharAt(i, 'U');
                                    }
                                }
                                if (i % 4 != 0) {//nw
                                    if (estado.charAt(i - 9) == 'p' && (estado.charAt(i - 5) == 'b' || estado.charAt(i - 5) == 'w')) {
                                        estado.setCharAt(i, 'U');
                                    }
                                }
                            }
                        }
                    }
                }

                if (estado.toString().compareTo(preEstado.toString()) == 0) { // se não da pra comer nada                  
                    for (int i = 0; i < estado.length(); i++) {
                        if (estado.charAt(i) == 'r') {
                            if (i > 3) {
                                if (i % 8 < 4) {
                                    if (i % 4 == 3) {
                                        if (estado.charAt(i - 4) == 'p') {
                                            estado.setCharAt(i, 'S');
                                        }
                                    } else if (estado.charAt(i - 4) == 'p' || estado.charAt(i - 3) == 'p') {
                                        estado.setCharAt(i, 'S');
                                    }
                                } else {
                                    if (i % 4 == 0) {
                                        if (estado.charAt(i - 4) == 'p') {
                                            estado.setCharAt(i, 'S');
                                        }
                                    } else if (estado.charAt(i - 5) == 'p' || estado.charAt(i - 4) == 'p') {
                                        estado.setCharAt(i, 'S');
                                    }
                                }
                            }
                            if (estado.charAt(i) != 'S') {
                                if (i < 28) {
                                    if (i % 8 < 4) {
                                        if (i % 4 == 3) {
                                            if (estado.charAt(i + 4) == 'p') {
                                                estado.setCharAt(i, 'S');
                                            }
                                        } else if (estado.charAt(i + 4) == 'p' || estado.charAt(i + 5) == 'p') {
                                            estado.setCharAt(i, 'S');
                                        }
                                    } else {
                                        if (i % 4 == 0) {
                                            if (estado.charAt(i + 4) == 'p') {
                                                estado.setCharAt(i, 'S');
                                            }
                                        } else if (estado.charAt(i + 3) == 'p' || estado.charAt(i + 4) == 'p') {
                                            estado.setCharAt(i, 'S');
                                        }
                                    }
                                }
                            }

                        } else if (estado.charAt(i) == 'v') {
                            if (i > 3) {
                                if (i % 8 < 4) {
                                    if (i % 4 == 3) {
                                        if (estado.charAt(i - 4) == 'p') {
                                            estado.setCharAt(i, 'U');
                                        }
                                    } else if (estado.charAt(i - 4) == 'p' || estado.charAt(i - 3) == 'p') {
                                        estado.setCharAt(i, 'U');
                                    }
                                } else {
                                    if (i % 4 == 0) {
                                        if (estado.charAt(i - 4) == 'p') {
                                            estado.setCharAt(i, 'U');
                                        }
                                    } else if (estado.charAt(i - 5) == 'p' || estado.charAt(i - 4) == 'p') {
                                        estado.setCharAt(i, 'U');
                                    }
                                }
                            }
                        }
                    }
                }
                if (estado.toString().compareTo(preEstado.toString()) == 0) { // se não há mais jogadas.
                    return "2";
                }
            }

        } else {
            if (bluep != -1) {
                estado.setCharAt(bluep, Character.toLowerCase(estado.charAt(bluep)));
            }
        }

        return estado.toString();
    }

    public static String tabuleiroPlayer2(boolean isPlayer2Turn, String estadoX) {

        StringBuilder str = new StringBuilder();
        StringBuilder estado = new StringBuilder();
        StringBuilder preEstado = new StringBuilder();
        estado.append(estadoX);
        preEstado.append(estado);
        int bluep = containsBlue(estado.toString());

        if (isPlayer2Turn) { //Se é a vez do jogador

            if (bluep != -1) { // se há alguma peça selecionada

                if (estado.charAt(bluep) == 'W') { // se é uma dama, verifica se pode comer de ré                   
                    if (bluep > 7) { //verifica se pode comer de frente.
                        if (bluep % 8 < 4) {
                            if (bluep % 4 != 3) { //ne 
                                if (estado.charAt(bluep - 7) == 'p' && (estado.charAt(bluep - 3) == 'v' || estado.charAt(bluep - 3) == 'r')) {
                                    estado.setCharAt(bluep - 7, 'P');
                                }
                            }
                            if (bluep % 4 != 0) { //nw 
                                if (estado.charAt(bluep - 9) == 'p' && (estado.charAt(bluep - 4) == 'v' || estado.charAt(bluep - 4) == 'r')) {
                                    estado.setCharAt(bluep - 9, 'P');
                                }
                            }
                        } else {
                            if (bluep % 4 != 3) { //ne 
                                if (estado.charAt(bluep - 7) == 'p' && (estado.charAt(bluep - 4) == 'v' || estado.charAt(bluep - 4) == 'r')) {
                                    estado.setCharAt(bluep - 7, 'P');
                                }
                            }
                            if (bluep % 4 != 0) { //nw 
                                if (estado.charAt(bluep - 9) == 'p' && (estado.charAt(bluep - 5) == 'v' || estado.charAt(bluep - 5) == 'r')) {
                                    estado.setCharAt(bluep - 9, 'P');
                                }
                            }
                        }
                    }
                }
                if (bluep < 24) {
                    if (bluep % 8 < 4) {
                        if (bluep % 4 != 3) { //se
                            if (estado.charAt(bluep + 9) == 'p' && (estado.charAt(bluep + 5) == 'v' || estado.charAt(bluep + 5) == 'r')) {
                                estado.setCharAt(bluep + 9, 'P');
                            }
                        }
                        if (bluep % 4 != 0) { //sw
                            if (estado.charAt(bluep + 7) == 'p' && (estado.charAt(bluep + 4) == 'v' || estado.charAt(bluep + 4) == 'r')) {
                                estado.setCharAt(bluep + 7, 'P');
                            }
                        }
                    } else {
                        if (bluep % 4 != 3) { //se
                            if (estado.charAt(bluep + 9) == 'p' && (estado.charAt(bluep + 4) == 'v' || estado.charAt(bluep + 4) == 'r')) {
                                estado.setCharAt(bluep + 9, 'P');
                            }
                        }
                        if (bluep % 4 != 0) { //sw
                            if (estado.charAt(bluep + 7) == 'p' && (estado.charAt(bluep + 3) == 'v' || estado.charAt(bluep + 3) == 'r')) {
                                estado.setCharAt(bluep + 7, 'P');
                            }
                        }
                    }
                }
                if (estado.toString().compareTo(preEstado.toString()) == 0) { // se não da pra comer nada
                    int i = bluep;
                    if (estado.charAt(i) == 'W') {
                        if (i > 3) {
                            if (i % 8 < 4) {
                                if (i % 4 == 3) {
                                    if (estado.charAt(i - 4) == 'p') {
                                        estado.setCharAt(i - 4, 'P');
                                    }
                                } else {
                                    if (estado.charAt(i - 4) == 'p') {
                                        estado.setCharAt(i - 4, 'P');
                                    }
                                    if (estado.charAt(i - 3) == 'p') {
                                        estado.setCharAt(i - 3, 'P');
                                    }
                                }
                            } else {
                                if (i % 4 == 0) {
                                    if (estado.charAt(i - 4) == 'p') {
                                        estado.setCharAt(i - 4, 'P');
                                    }
                                } else {
                                    if (estado.charAt(i - 5) == 'p') {
                                        estado.setCharAt(i - 5, 'P');
                                    }
                                    if (estado.charAt(i - 4) == 'p') {
                                        estado.setCharAt(i - 4, 'P');
                                    }
                                }
                            }
                        }

                    }
                    if (estado.charAt(i) == 'W' || estado.charAt(i) == 'B') {

                        if (i < 28) {
                            if (i % 8 < 4) {
                                if (i % 4 == 3) {
                                    if (estado.charAt(i + 4) == 'p') {
                                        estado.setCharAt(i + 4, 'P');
                                    }
                                } else {
                                    if (estado.charAt(i + 4) == 'p') {
                                        estado.setCharAt(i + 4, 'P');
                                    }
                                    if (estado.charAt(i + 5) == 'p') {
                                        estado.setCharAt(i + 5, 'P');
                                    }
                                }
                            } else {
                                if (i % 4 == 0) {
                                    if (estado.charAt(i + 4) == 'p') {
                                        estado.setCharAt(i + 4, 'P');
                                    }
                                } else {
                                    if (estado.charAt(i + 3) == 'p') {
                                        estado.setCharAt(i + 3, 'P');
                                    }
                                    if (estado.charAt(i + 4) == 'p') {
                                        estado.setCharAt(i + 4, 'P');
                                    }
                                }
                            }

                        }
                    }
                }

            } else { // se não há peças selecionadas então mostrar quais podem ser selecionadas

                for (int i = 0; i < estado.length(); i++) {
                    if (estado.charAt(i) == 'w') { // se for uma dama.
                        if (i > 7) {
                            if (i % 8 < 4) {
                                if (i % 4 != 3) {//ne
                                    if (estado.charAt(i - 7) == 'p' && (estado.charAt(i - 3) == 'v' || estado.charAt(i - 3) == 'r')) {
                                        estado.setCharAt(i, 'X');
                                    }
                                }
                                if (i % 4 != 0) {//nw
                                    if (estado.charAt(i - 9) == 'p' && (estado.charAt(i - 4) == 'v' || estado.charAt(i - 4) == 'r')) {
                                        estado.setCharAt(i, 'X');
                                    }
                                }
                            } else {
                                if (i % 4 != 3) {//ne
                                    if (estado.charAt(i - 7) == 'p' && (estado.charAt(i - 4) == 'v' || estado.charAt(i - 4) == 'r')) {
                                        estado.setCharAt(i, 'X');
                                    }
                                }
                                if (i % 4 != 0) {//nw
                                    if (estado.charAt(i - 9) == 'p' && (estado.charAt(i - 5) == 'v' || estado.charAt(i - 5) == 'r')) {
                                        estado.setCharAt(i, 'X');
                                    }
                                }
                            }
                        }
                        if (i < 24) {

                            if (i % 8 < 4) {
                                if (i % 4 != 3) {//se
                                    if (estado.charAt(i + 9) == 'p' && (estado.charAt(i + 5) == 'v' || estado.charAt(i + 5) == 'r')) {
                                        estado.setCharAt(i, 'X');
                                    }
                                }
                                if (i % 4 != 0) {//sw
                                    if (estado.charAt(i + 7) == 'p' && (estado.charAt(i + 4) == 'v' || estado.charAt(i + 4) == 'r')) {
                                        estado.setCharAt(i, 'X');
                                    }
                                }
                            } else {
                                if (i % 4 != 3) {//se
                                    if (estado.charAt(i + 9) == 'p' && (estado.charAt(i + 4) == 'v' || estado.charAt(i + 4) == 'r')) {
                                        estado.setCharAt(i, 'X');
                                    }
                                }
                                if (i % 4 != 0) {//sw
                                    if (estado.charAt(i + 7) == 'p' && (estado.charAt(i + 3) == 'v' || estado.charAt(i + 3) == 'r')) {
                                        estado.setCharAt(i, 'X');
                                    }
                                }
                            }

                        }
                    } else if (estado.charAt(i) == 'b') { // se for uma peça
                        if (i < 24) {
                            if (i % 8 < 4) {
                                if (i % 4 != 3) {//se
                                    if (estado.charAt(i + 9) == 'p' && (estado.charAt(i + 5) == 'v' || estado.charAt(i + 5) == 'r')) {
                                        estado.setCharAt(i, 'C');
                                    }
                                }
                                if (i % 4 != 0) {//sw
                                    if (estado.charAt(i + 7) == 'p' && (estado.charAt(i + 4) == 'v' || estado.charAt(i + 4) == 'r')) {
                                        estado.setCharAt(i, 'C');
                                    }
                                }
                            } else {
                                if (i % 4 != 3) {//se                                    
                                    if (estado.charAt(i + 9) == 'p' && (estado.charAt(i + 4) == 'v' || estado.charAt(i + 4) == 'r')) {
                                        estado.setCharAt(i, 'C');
                                    }
                                }
                                if (i % 4 != 0) {//sw
                                    if (estado.charAt(i + 7) == 'p' && (estado.charAt(i + 3) == 'v' || estado.charAt(i + 3) == 'r')) {
                                        estado.setCharAt(i, 'C');
                                    }
                                }
                            }
                        }
                    }
                }
                if (estado.toString().compareTo(preEstado.toString()) == 0) { // se não da pra comer nada

                    for (int i = 0; i < estado.length(); i++) {
                        if (estado.charAt(i) == 'w') {
                            if (i < 28) {
                                if (i % 8 < 4) {
                                    if (i % 4 == 3) {
                                        if (estado.charAt(i + 4) == 'p') {
                                            estado.setCharAt(i, 'X');
                                        }
                                    } else if (estado.charAt(i + 4) == 'p' || estado.charAt(i + 5) == 'p') {
                                        estado.setCharAt(i, 'X');
                                    }
                                } else {
                                    if (i % 4 == 0) {
                                        if (estado.charAt(i + 4) == 'p') {
                                            estado.setCharAt(i, 'X');
                                        }
                                    } else if (estado.charAt(i + 3) == 'p' || estado.charAt(i + 4) == 'p') {
                                        estado.setCharAt(i, 'X');
                                    }
                                }
                            }
                            if (estado.charAt(i) != 'X') {
                                if (i > 3) {
                                    if (i % 8 < 4) {
                                        if (i % 4 == 3) {
                                            if (estado.charAt(i - 4) == 'p') {
                                                estado.setCharAt(i, 'X');
                                            }
                                        } else if (estado.charAt(i - 4) == 'p' || estado.charAt(i - 3) == 'p') {
                                            estado.setCharAt(i, 'X');
                                        }
                                    } else {
                                        if (i % 4 == 0) {
                                            if (estado.charAt(i - 4) == 'p') {
                                                estado.setCharAt(i, 'X');
                                            }
                                        } else if (estado.charAt(i - 5) == 'p' || estado.charAt(i - 4) == 'p') {
                                            estado.setCharAt(i, 'X');
                                        }
                                    }
                                }
                            }

                        } else if (estado.charAt(i) == 'b') {
                            if (i < 28) {
                                if (i % 8 < 4) {
                                    if (i % 4 == 3) {
                                        if (estado.charAt(i + 4) == 'p') {
                                            estado.setCharAt(i, 'C');
                                        }
                                    } else if (estado.charAt(i + 4) == 'p' || estado.charAt(i + 5) == 'p') {
                                        estado.setCharAt(i, 'C');
                                    }
                                } else {
                                    if (i % 4 == 0) {
                                        if (estado.charAt(i + 4) == 'p') {
                                            estado.setCharAt(i, 'C');
                                        }
                                    } else if (estado.charAt(i + 3) == 'p' || estado.charAt(i + 4) == 'p') {
                                        estado.setCharAt(i, 'C');
                                    }
                                }
                            }
                        }
                    }
                }
                if (estado.toString().compareTo(preEstado.toString()) == 0) { // se não há mais jogadas.
                    return "1";
                }
            }

        } else {
            if (bluep != -1) {
                estado.setCharAt(bluep, Character.toLowerCase(estado.charAt(bluep)));
            }
        }

        return estado.toString();
    }

    private static int containsBlue(String estado) {
        for (int i = 0; i < estado.length(); i++) {
            if (estado.charAt(i) == 'B' || estado.charAt(i) == 'V' || estado.charAt(i) == 'R' || estado.charAt(i) == 'W') {
                return i;
            }
        }
        return -1;
    }
    
     private static int containsBluePlayer1(String estado) {
        for (int i = 0; i < estado.length(); i++) {
            if (estado.charAt(i) == 'V' || estado.charAt(i) == 'R') {
                return i;
            }
        }
        return -1;
    }
     
      private static int containsBluePlayer2(String estado) {
        for (int i = 0; i < estado.length(); i++) {
            if (estado.charAt(i) == 'B' ||  estado.charAt(i) == 'W') {
                return i;
            }
        }
        return -1;
    }
}
