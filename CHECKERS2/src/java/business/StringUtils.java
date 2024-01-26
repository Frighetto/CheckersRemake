package business;

/**
 *
 * @author Lucas Fernando Frighetto
 */
public class StringUtils {
    
    public static String romano(int numero){    
        StringBuilder romano = new StringBuilder();                
        while(numero > 0){
            int numeral_romano;
            boolean quintos = false;
            for(numeral_romano = 1; numeral_romano < numero; numeral_romano = quintos ? numeral_romano * 5 : numeral_romano * 2){
                quintos = !quintos;
            }
            if(numeral_romano > numero){
                int anterior = quintos ? numeral_romano / 5 : numeral_romano / 2;
                int anterior_nao_quintos = quintos ? numeral_romano / 5 : numeral_romano / 10;
                if(numeral_romano - anterior_nao_quintos <= numero){
                    romano.append(numero_romano(anterior_nao_quintos));
                    romano.append(numero_romano(numeral_romano));
                    numero = numero - (numeral_romano - anterior_nao_quintos);
                } else if((!quintos) && (anterior <= numero)) {
                    romano.append(numero_romano(anterior));
                    numero = numero - anterior;
                } else if(anterior_nao_quintos * 3 <= numero) {
                    romano.append(numero_romano(anterior_nao_quintos));
                    romano.append(numero_romano(anterior_nao_quintos));
                    romano.append(numero_romano(anterior_nao_quintos));
                    numero = numero - (anterior_nao_quintos * 3);
                } else if(anterior_nao_quintos * 2 <= numero) {
                    romano.append(numero_romano(anterior_nao_quintos));
                    romano.append(numero_romano(anterior_nao_quintos));                    
                    numero = numero - (anterior_nao_quintos * 2);
                } else {
                    romano.append(numero_romano(anterior_nao_quintos));
                    numero = numero - anterior_nao_quintos;
                }
            } else if(numeral_romano == numero){
                romano.append(numero_romano(numero));
                numero = numero - numero;
            }
        }
        return romano.toString();
    }
    
    public static String numero_romano(int numero){ 
        switch(numero){ 
            case 1: return "I";
            case 5: return "V";
            case 10: return "X";
            case 50: return "L";
            case 100: return "C";
            case 500: return "D";
            case 1000: return "M";
            default: return "?";            
        }
    }
    
}
