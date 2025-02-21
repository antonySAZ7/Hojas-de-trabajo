import java.util.Arrays;
import java.util.Random;
import java.io.*;
import org.junit.Test;
import static org.junit.Assert.*;
public class TesteoSort {
    private static int cantidadMaxima = 3000;
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

    public static Integer[] generadoRandomArrays(int cantidadMaxima){
        Random random = new Random();
        Integer[] array =  new Integer[cantidadMaxima];
        for(int i = 0; i<cantidadMaxima; i++){
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
    
    @Test
    public void testHeapSort() {
        Integer[] array = {4, 10, 3, 5, 1};
        HeapSort<Integer> sorter = new HeapSort<>();
        sorter.sort(array);
        assertArrayEquals(new Integer[]{1, 3, 4, 5, 10}, array);
    }

    @Test
    public void testQuickSort() {
        Integer[] array = {8, 2, 7, 6, 1};
        QuickSort<Integer> sorter = new QuickSort<>();
        sorter.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 6, 7, 8}, array);
    }
}
