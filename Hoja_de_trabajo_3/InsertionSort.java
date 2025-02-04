
public class InsertionSort <T extends Comparable<T>> implements SortAlgoritmos<T>{
    @Override
    public void sort(T[] array){
        int n = array.length;
        for(int i = 1; i<n; i++){
            T key = array[i];
            int j =i -1;
            while(j >= 0 && array[j].compareTo(key)>0){
                array[j+1] = array[j];
                j--;
            }
            array[j +1]= key;

        }
    }
    
}
