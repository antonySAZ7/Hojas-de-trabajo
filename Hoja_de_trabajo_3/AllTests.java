
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class AllTests {
    public static void main(String[] args) {
        System.out.println("corruendo tests...");
    }

    @Test
    public void testHeapSort() {
        Integer[] array = {4, 10, 3, 5, 1};
        HeapSort<Integer> sorter = new HeapSort<>();
        sorter.sort(array);
        assertArrayEquals(new Integer[]{1, 3, 4, 5, 10}, array);
            }
        

    @Test
    public void testInsertionSort() {
        Integer[] array = {4, 10, 3, 5, 1};
        InsertionSort<Integer> sorter = new InsertionSort<>();
        sorter.sort(array);
        assertArrayEquals(new Integer[]{1, 3, 4, 5, 10}, array);
    }
    
    @Test
    public void testMergeSort() {
        Integer[] array = {4, 10, 3, 5, 1};
        MergeSort<Integer> sorter = new MergeSort<>();
        sorter.sort(array);
        assertArrayEquals(new Integer[]{1, 3, 4, 5, 10}, array);
    }

    @Test
    public void testQuickSort() {
        Integer[] array = {4, 10, 3, 5, 1};
        QuickSort<Integer> sorter = new QuickSort<>();
        sorter.sort(array);
        assertArrayEquals(new Integer[]{1, 3, 4, 5, 10}, array);
    }
    

    @Test
    public void testRadixSort() {
        Integer[] array = {4, 10, 3, 5, 1};
        RadixSort sorter = new RadixSort();
        sorter.sort(array);
        assertArrayEquals(new Integer[]{1, 3, 4, 5, 10}, array);
    } 
}
