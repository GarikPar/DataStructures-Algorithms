import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuickSelectExperiment {
    static int comparisons = 0;

    public static void main(String[] args) {
        int size = 1_000_000;
        ArrayList<Integer> seq = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            seq.add(i);
        }

        Collections.shuffle(seq, new Random());


        int[] Arr = seq.stream().mapToInt(Integer::intValue).toArray();


        int rand_indx = 1 + new Random().nextInt(size);
        System.out.println("Random i: " + rand_indx);


        comparisons = 0;
        int rand_el = quickSelect(Arr, 0, Arr.length - 1, rand_indx - 1);
        System.out.println(rand_indx + "-th smallest element is: " + rand_el);


        double ratio = comparisons / (4.0 * size);
        System.out.println("Total comparisons X: " + comparisons);
        System.out.println("X / 4n = " + ratio);
        System.out.println("Is ratio < 1: " + (ratio < 1));
    }


    public static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }
        int pivotIndex = partition(arr, left, right);

        if (k == pivotIndex){
            return arr[k];
        }
        else if (k < pivotIndex){
            return quickSelect(arr, left, pivotIndex - 1, k);
        }
        else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }


    private static int partition(int[] arr, int left, int right) {
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            comparisons++; 
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }

        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
