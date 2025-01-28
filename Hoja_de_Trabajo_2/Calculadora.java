import java.io.IOException;
import java.util.List;

public class Calculadora {
    public static void main(String[] args) {
        String archivo = "datos.txt"; 
        FileReader lector = new FileReader();
        StackTests stackTests = new StackTests();

        try {
            List<String> expresionACalcular = lector.tomarExpresion(archivo);

            System.out.println("Expresión que ha sido leida:" + expresionACalcular);

            int resultado = evaluarExpresion(expresionACalcular, stackTests);

            System.out.println("Resultado:  " + resultado);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al procesar la expresión: " + e.getMessage());
        }
    }

    private static int evaluarExpresion(List<String> expresionACalcular, StackTests stackTests) {
        for (String token : expresionACalcular) {
            if (esNumero(token)) {
                // Si es un número entero, se apila
                stackTests.push(Integer.parseInt(token));
            } else {
                int b = stackTests.pop();
                int a = stackTests.pop();
                int resultado = stackTests.getPila().operation(token.charAt(0), a, b);
                stackTests.push(resultado);
            }
        }

        return stackTests.pop();
    }

    private static boolean esNumero(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
