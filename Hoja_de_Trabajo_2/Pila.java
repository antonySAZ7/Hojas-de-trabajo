
import java.util.ArrayList;
import java.util.List;

public class Pila implements  IStack {

    public  List<Integer> elementos;


    public Pila(){
        this.elementos = new ArrayList<>();

    }

    public void push(int value){
        elementos.add(value);

    }

    public int pop(){
        if(elementos.isEmpty()){
            throw new IllegalStateException("la pila está vacía");
        }
        return  elementos.remove(elementos.size()-1);

    }

    public int operation(char operator, int value1, int value2){
        switch(operator){
            case '+': return value1 + value2;
            case '-': return value1 - value2;
            case '*': return value1 * value2;
            case '/': 
            if(value2 ==0){
                throw new ArithmeticException("No se puede dividir entre cero");
            }
            return value1/value2;
            case '%': return value1%value2;
            default: throw new IllegalArgumentException("Operador no válido: " + operator);
        }
    }
}
