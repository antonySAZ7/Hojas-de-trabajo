public class PilaFactory {
    /**
     * se crea una instancia de IStack según el tipo especificado por el user
     * @param tipo arraylist, vector o lista
     * @param subtipoLista Si se selecciona "lista", se indica si es simple o "doble
     * @return una instancia de IStack<T>
     */
    public static <T> IStack<T> crearPila(String tipo, String subtipoLista) {
        if (tipo == null) {
            throw new IllegalArgumentException("el tipo no puede ser nulo, tienes que poner ´arraylist´, ´vector´ o ´lista´");
        }
        switch (tipo.toLowerCase()) {
            case "arraylist":
                return new SArrayList<>();
            case "vector":
                return new SVector<>();
            case "lista":
                ILista<T> lista;
                if (subtipoLista == null) {
                    throw new IllegalArgumentException("Debes indicar el subtipo de lista");
                }
                if (subtipoLista.equalsIgnoreCase("simple")) {
                    lista = new LSEncadenada<>();
                } else if (subtipoLista.equalsIgnoreCase("doble")) {
                    lista = new LDEncadenada<>();
                } else {
                    throw new IllegalArgumentException("NO tenemos ese tipo de lista");
                }
                return new SLista<>(lista);
            default:
                throw new IllegalArgumentException("No tenemos ese tipo de pila");
        }
    }
}
