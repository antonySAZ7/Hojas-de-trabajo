

public class SLista<T> implements IStack<T> {
    private ILista<T> stack;

    public SLista(ILista<T> lista) {
        this.stack = lista;
    }

    public void push(T item) {
        stack.addFirst(item);
    }

    public T pop() {
        return stack.removeFirst();
    }

  
    public T peek() {
        if (!stack.isEmpty()) {
            return stack.getFirst();
        }
        return null;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
    
}
