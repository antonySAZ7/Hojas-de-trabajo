

public class LDEncadenada<T> implements ILista<T>{
    private static class Nodo<T> {
        T info;
        Nodo<T> next, prevs;
        
        Nodo(T info){
            this.info = info;
            this.next = this.prevs =  null;
            
        }
    }

    public boolean isEmpty(){
        return primero == null;
    }
   

    private Nodo<T> primero, ultimo;

    public void addFirst(T t){
        Nodo<T> nNodo =  new Nodo<>(t);
        if(isEmpty()){
            primero = ultimo = nNodo;
        }else{
             nNodo.next = primero;
             primero.prevs =  nNodo;
             primero = nNodo;
        }
    }

    public T removeFirst(){
        if(!isEmpty()){
            T info = primero.info;
            if(primero == ultimo){
                primero = ultimo = null;
            }else{
                primero = primero.next;
                primero.prevs = null;
            }
            return info;
        }
        return null;
    }

    public T getFirst(){
        return isEmpty() ? null : primero.info;
    }

    
}
