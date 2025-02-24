import java.util.Vector;

public class SVector<T> implements IStack<T> {

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    private Vector<T> stack = new Vector<>();

    public void push(T t) {
        stack.add(t);
    }

    
    public T pop() {
        if (!isEmpty()) {
            return stack.remove(stack.size() - 1);
        }
        return null;
    }

    public T peek() {
        if (!isEmpty()) {
            return stack.get(stack.size() - 1);
        }
        return null;
    }


   


}
