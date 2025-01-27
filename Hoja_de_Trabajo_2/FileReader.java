import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileReader {
    private List<String> expresiones;

    public FileReader(){
        this.expresiones = new ArrayList<>();
    }

   

    public List<String> tomarExpresion(String archivo) throws IOException{
        expresiones.clear();
        try(BufferedReader br = new BufferedReader(new java.io.FileReader(archivo))){
            String linea;
            while ((linea = br.readLine()) != null){
                if (!linea.trim().isEmpty()) {
                     String[] partes = linea.trim().split("\\s+"); // Lee todo pero separando los elementos por espacios " "
                     expresiones.addAll(Arrays.asList(partes));   
                     }
            }
        }
        return expresiones;
        
    }

    public boolean isEmpty() {
        return expresiones.isEmpty();
    }

    public void reEspresion() {
        if (isEmpty()) {
            System.out.println("No se encontraron expresiones en el archivo.");
        } else {
            System.out.println("La siguiente expresion es: ");
            for (String expresion : expresiones) {
                System.out.println(expresion);
            }
        }
    }

    
}
