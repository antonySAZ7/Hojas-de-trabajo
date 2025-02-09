import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("Seleccione el algoritmo de ordenamiento:");
            System.out.println("1. Insertion Sort");
            System.out.println("2. Merge Sort");
            System.out.println("3. Quick Sort");
            System.out.println("4. Radix Sort");
            System.out.println("5. Heap Sort");
            System.out.println("0. Salir");
            
            int opcion = scanner.nextInt();
            
            if (opcion == 0) {
                System.out.println("Saliendo del programa...");
                continuar = false;
                break;
            }
            
            SortAlgoritmos<Integer> algoritmo;
            switch (opcion) {
                case 1: algoritmo = new InsertionSort<>(); break;
                case 2: algoritmo = new MergeSort<>(); break;
                case 3: algoritmo = new QuickSort<>(); break;
                case 4: algoritmo = new RadixSort(); break;
                case 5: algoritmo = new HeapSort<>(); break;
                default:
                    System.out.println("Elija una opción válida");
                    continue;
            }
            
            TesteoSort test = new TesteoSort(algoritmo);
            Integer[] array = TesteoSort.generadoRandomArrays(1000);
            try {
                TesteoSort.guardarNumeros(array, "numeros.txt");
                array = TesteoSort.cargarnumeros("numeros.txt");
            } catch(IOException e) {
                System.out.println("Error con el archivo: " + e.getMessage());
                continue;
            }
            
            long tiempo = test.testeoSort(array);
            System.out.println("Tiempo de ejecución: " + tiempo + " en nanosegundos");
            
            System.out.println("Quieres hacer los algoritmos con el array ya ordenado? (s/n)");
            String respuesta = scanner.next();
            if (!respuesta.equalsIgnoreCase("s")) {
                continuar = false;
            }
        }
        scanner.close();
    }
}
