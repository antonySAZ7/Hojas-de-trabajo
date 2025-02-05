import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el algoritmo de ordenamiento:");
        System.out.println("1. Insertion Sort");
        System.out.println("2. Merge Sort");
        System.out.println("3. Quick Sort");
        System.out.println("4. Radix Sort");
        System.out.println("5. Heap Sort");
        int opcion = scanner.nextInt();

        SortAlgoritmos<Integer> algoritmo;
        switch (opcion) {
            case 1:algoritmo = new InsertionSort<>(); break;
            case 2: algoritmo = new MergeSort<>(); break;
            case 3: algoritmo = new QuickSort<>(); break;
            case 4: algoritmo = new RadixSort(); break;
            case 5: algoritmo = new HeapSort<>(); break;
            default:
            System.out.println("elija una opcion valida");
                return;
        }

        TesteoSort test = new TesteoSort(algoritmo);
        Integer[] array = TesteoSort.generadoRandomArrays(1000);
        try{
            TesteoSort.guardarNumeros(array, "numeros.txt");
            array = TesteoSort.cargarnumeros("numeros.txt");

        } catch(IOException e){
            System.out.println("Error con el archivo " + e.getMessage());
            return;
        }
        long tiempo = test.testeoSort(array);
        System.out.println("Tiempo de ejecucion" + tiempo + "en nano segundos");

        


        
    }
    
}
