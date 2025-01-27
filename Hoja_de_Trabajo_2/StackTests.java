public class StackTests {
    private Pila pila;

    public StackTests() {
        this.pila = new Pila();
    }

    public void push(int value) {
        pila.push(value);
    }

    public int pop() {
        return pila.pop();
    }

    public boolean isEmpty() {
        return pila == null || pila.elementos.isEmpty();
    }

    public Pila getPila() {
        return pila;
    }
}
