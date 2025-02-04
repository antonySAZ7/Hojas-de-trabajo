public class HeapSort<T extends Comparable<T>> implements SortAlgoritmos<T> {
    @Override
    public void sort(T[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }
    }

    private void heapify(T[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left].compareTo(array[largest]) > 0) {
            largest = left;
        }
        if (right < n && array[right].compareTo(array[largest]) > 0) {
            largest = right;
        }
        if (largest != i) {
            swap(array, i, largest);
            heapify(array, n, largest);
        }
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
