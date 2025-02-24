import java.util.ArrayList;

public class SArrayList<T> implements IStack<T> {
    private ArrayList<T> stack = new ArrayList<>();

    public void push(T t){
        stack.add(t);
    }

    public T pop(){
        if(!isEmpty()){
            return stack.remove(stack.size() -1);
        }
        return null;
    }

    public T peek(){
        if(!isEmpty()){
            return stack.get(stack.size() -1 );
        }
        return null;
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }
}
