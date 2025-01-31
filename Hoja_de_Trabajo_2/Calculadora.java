import java.io.IOException;
import java.util.List;

public class Calculadora {
    public static void main(String[] args) {
        String archivo = "datos.txt"; 
        FileReader lector = new FileReader();

        try {
            List<List<String>> expresiones = lector.tomarExpresion(archivo);

            System.out.println("Expresiones leídas del archivo:");
            for (List<String> expresion : expresiones) {
                System.out.println(expresion);
                int resultado = evaluarExpresion(expresion);
                System.out.println("Resultado: " + resultado);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al procesar la expresión: " + e.getMessage());
        }
    }

    public static int evaluarExpresion(List<String> expresion) {
        Pila pila = new Pila(); // Usamos Pila directamente
        for (String token : expresion) {
            if (esNumero(token)) {
                pila.push(Integer.parseInt(token));
            } else {
                int b = pila.pop();
                int a = pila.pop();
                int resultado = pila.operation(token.charAt(0), a, b);
                pila.push(resultado);
            }
        }
        return pila.pop();
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
