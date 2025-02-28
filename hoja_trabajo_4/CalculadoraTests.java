import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculadoraTests {
    
    private IStack<String> pilaArrayList;
    private IStack<String> pilaVector;
    private IStack<String> pilaLista;
    private ILista<String> listaSimple;
    private ILista<String> listaDoble;
    private Calculadora calculadora;

    @BeforeEach
    void setUp() {
        pilaArrayList = new SArrayList<>();
        pilaVector = new SVector<>();
        listaSimple = new LSEncadenada<>();
        listaDoble = new LDEncadenada<>();
        pilaLista = new SLista<>(listaSimple);
        calculadora = Calculadora.getInstance();
    }

    @Test
    void testPilaArrayList() {
        pilaArrayList.push("A");
        pilaArrayList.push("B");
        assertEquals("B", pilaArrayList.pop());
        assertEquals("A", pilaArrayList.peek());
        assertFalse(pilaArrayList.isEmpty());
        pilaArrayList.pop();
        assertTrue(pilaArrayList.isEmpty());
    }

    @Test
    void testPilaVector() {
        pilaVector.push("X");
        pilaVector.push("Y");
        assertEquals("Y", pilaVector.pop());
        assertEquals("X", pilaVector.peek());
        assertFalse(pilaVector.isEmpty());
        pilaVector.pop();
        assertTrue(pilaVector.isEmpty());
    }

    @Test
    void testPilaLista() {
        pilaLista.push("1");
        pilaLista.push("2");
        assertEquals("2", pilaLista.pop());
        assertEquals("1", pilaLista.peek());
        assertFalse(pilaLista.isEmpty());
        pilaLista.pop();
        assertTrue(pilaLista.isEmpty());
    }

    @Test
    void testListaSimplementeEncadenada() {
        listaSimple.addFirst("Hola");
        assertEquals("Hola", listaSimple.getFirst());
        assertFalse(listaSimple.isEmpty());
        assertEquals("Hola", listaSimple.removeFirst());
        assertTrue(listaSimple.isEmpty());
    }

    @Test
    void testListaDoblementeEncadenada() {
        listaDoble.addFirst("Mundo");
        assertEquals("Mundo", listaDoble.getFirst());
        assertFalse(listaDoble.isEmpty());
        assertEquals("Mundo", listaDoble.removeFirst());
        assertTrue(listaDoble.isEmpty());
    }

    @Test
    void testConversionInfixAPostfix() {
        IStack<String> stack = new SArrayList<>();
        String infix = "(3+5)*8";
        String expectedPostfix = "3 5 + 8 *";
        assertEquals(expectedPostfix, calculadora.convertirInfixAPostfix(infix, stack));
    }

    @Test
    void testEvaluacionPostfix() {
        String postfix = "3 5 + 8 *";
        assertEquals(16, calculadora.evaluarPostfix(postfix));
    }

    @Test
    void testDivisionPorCero() {
        String postfix = "8 0 /";
        assertThrows(ArithmeticException.class, () -> calculadora.evaluarPostfix(postfix));
    }
}
