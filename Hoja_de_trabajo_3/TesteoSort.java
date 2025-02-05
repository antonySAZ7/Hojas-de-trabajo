import java.util.Arrays;
import java.util.Random;
import java.io.*;

public class TesteoSort {
    private static final int cantidadMaxima = 3000;
    private SortAlgoritmos<Integer> algoritmos;
    

    public TesteoSort(SortAlgoritmos<Integer> algoritmos){
        this.algoritmos = algoritmos;

    }

    public long testeoSort(Integer[] array){
        long inicio = System.nanoTime();
        algoritmos.sort(array);
        long fin = System.nanoTime();
        return fin - inicio;

    }

    public static Integer[] generadoRandomArrays(int tamanio){
        Random random = new Random();
        Integer[] array =  new Integer[tamanio];
        for(int i = 0; i<tamanio; i++){
            array[i] = random.nextInt(10000);
        }
        return array;
    }   

    public static void guardarNumeros(Integer[] array, String archivo) throws IOException{
        try(BufferedWriter w = new BufferedWriter(new FileWriter(archivo))){
            for(Integer num : array){
                w.write(num + "\n");
            }
        }
    }

    public static Integer[] cargarnumeros(String archivo) throws IOException{
        try(BufferedReader r = new BufferedReader(new FileReader(archivo))){
            return r.lines().map(Integer::parseInt).toArray(Integer[]::new);
        }
    }
    
}
