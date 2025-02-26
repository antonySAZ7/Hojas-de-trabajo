import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Seleccione la implementación de la pila (arraylist, vector, lista):");
        String tipoPila = scanner.nextLine().trim();
        String subtipoLista = null;
        if (tipoPila.equalsIgnoreCase("lista")) {
            System.out.println("Seleccione el tipo de lista (simple o doble):");
            subtipoLista = scanner.nextLine().trim();
        }
        
        IStack<String> stackParaConversion = PilaFactory.<String>crearPila(tipoPila, subtipoLista);
        
        String infix = "";
        try {
            Scanner fileScanner = new Scanner(new File("datos.txt"));
            if (fileScanner.hasNextLine()) {
                infix = fileScanner.nextLine();
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el  datos.txt");
            scanner.close();
            return;
        }
        
        System.out.println("Expresión infix leída: " + infix);
        
        Calculadora calc = Calculadora.getInstance();
        String postfix = calc.convertirInfixAPostfix(infix, stackParaConversion);
        System.out.println("Expresión postfix: " + postfix);
        
        int resultado = calc.evaluarPostfix(postfix);
        System.out.println("Resultado: " + resultado);
        
        scanner.close();
    }
}
