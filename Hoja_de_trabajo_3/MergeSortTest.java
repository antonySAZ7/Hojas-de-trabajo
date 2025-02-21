public class MergeSortTest {
    @Test
    public void testMergeSort() {
        Integer[] array = {4, 10, 3, 5, 1};
        MergeSort<Integer> sorter = new MergeSort<>();
        sorter.sort(array);
        assertArrayEquals(new Integer[]{1, 3, 4, 5, 10}, array);
    }
    
    private void assertArrayEquals(Integer[] integers, Integer[] array) {
        throw new UnsupportedOperationException("metodo sin implementar 'assertArrayEquals'");
    }
}
