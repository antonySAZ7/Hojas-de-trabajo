public class RadixSort implements SortAlgoritmos<Integer> {
    @Override
    public void sort(Integer[] array) {
        int max = getMax(array);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp);
        }
    }

    private int getMax(Integer[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) max = num;
        }
        return max;
    }

    private void countingSortByDigit(Integer[] array, int exp) {
        int n = array.length;
        Integer[] output = new Integer[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(array[i] / exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }
        System.arraycopy(output, 0, array, 0, n);
    }
}
