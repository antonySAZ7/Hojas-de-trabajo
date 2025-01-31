import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private List<List<String>> expresiones;

    public FileReader(){
        this.expresiones = new ArrayList<>();
    }

    public List<List<String>> tomarExpresion(String archivo) throws IOException{
        expresiones.clear();
        try(BufferedReader br = new BufferedReader(new java.io.FileReader(archivo))){
            String linea;
            while ((linea = br.readLine()) != null){
                if (!linea.trim().isEmpty()) {
                     List<String> partes = List.of(linea.trim().split("\\s+")); // Lee todo pero separando los elementos por espacios " "
                     expresiones.add(partes);   
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
            System.out.println("Las expresiones encontradas son: ");
            for (int i = 0; i< expresiones.size(); i++) {
                System.out.println("Expresion" +(i+1) + " " + expresiones.get(i));
            }
        }
    }

    
}
