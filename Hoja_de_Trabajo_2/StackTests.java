import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class StackTests {

    @Test
    public void testLecturaArchivo() {
        FileReader lector = new FileReader();
        try {
            List<List<String>> expresiones = lector.tomarExpresion("datos.txt");
            
            assertNotNull("El archivo no debería ser nulo", expresiones);
            assertFalse("El archivo no debería estar vacío", expresiones.isEmpty());

            assertEquals(Arrays.asList("1", "2", "+", "4", "*", "3", "+"), expresiones.get(0));
            assertEquals(Arrays.asList("5", "6", "2", "*", "+"), expresiones.get(1));
            assertEquals(Arrays.asList("10", "4", "3", "+", "2", "*", "-"), expresiones.get(2));
            assertEquals(Arrays.asList("8", "2", "/", "3", "+"), expresiones.get(3));

        } catch (IOException e) {
            fail("No debería lanzarse una excepción al leer el archivo.");
        }
    }

    @Test
    public void testEvaluacionExpresiones() {
        List<List<String>> expresiones = Arrays.asList(
            Arrays.asList("1", "2", "+", "4", "*", "3", "+"),  // (1 + 2) * 4 + 3 = 15
            Arrays.asList("5", "6", "2", "*", "+"),            // 5 + (6 * 2) = 17
            Arrays.asList("10", "4", "3", "+", "2", "*", "-"), // 10 - ((4 + 3) * 2) = -4
            Arrays.asList("8", "2", "/", "3", "+")             // (8 / 2) + 3 = 7
        );
    
        int[] resultadosEsperados = {15, 17, -4, 7};
    
        for (int i = 0; i < expresiones.size(); i++) {
            int resultado = Calculadora.evaluarExpresion(expresiones.get(i));
            assertEquals("Error en la expresión " + expresiones.get(i), resultadosEsperados[i], resultado);
        }
    }
    
}
