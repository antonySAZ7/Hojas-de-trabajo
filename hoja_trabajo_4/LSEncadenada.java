public class LSEncadenada<T> implements ILista<T> {
    private static class Nodo<T>{
        T info;
        Nodo<T> next;

        Nodo(T data){
            this.info = data;
            this.next = null;
        }
    }
    public boolean isEmpty(){
        return primero == null;
    }

    private Nodo<T> primero;

    public void addFirst(T t){
        Nodo<T> nNodo = new Nodo<>(t);
        nNodo.next = primero;
        primero = nNodo;
    }

    public T removeFirst() {
        if(!isEmpty()){
            T dato = primero.info;
            primero = primero.next;
            return dato;
        }
        return null;
    }

    public T getFirst(){
        return isEmpty() ? null : primero.info;
    }

    
    
}
