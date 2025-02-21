public class HeapSortTest {
    @Test
    public void testHeapSort() {
        Integer[] array = {4, 10, 3, 5, 1};
        HeapSort<Integer> sorter = new HeapSort<>();
        sorter.sort(array);
        assertArrayEquals(new Integer[]{1, 3, 4, 5, 10}, array);
            }
        
            private void assertArrayEquals(Integer[] integers, Integer[] array) {
                throw new UnsupportedOperationException("metodo sin implementar 'assertArrayEquals'");
            }       
}
