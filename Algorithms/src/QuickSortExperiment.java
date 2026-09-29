//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Random;
//
////import java.util.*;
////
////public class QuickSortExperiment {
////
////    static int comparisons = 0;
////
////    public static void quicksort(int[] a, int low, int high) {
////        if (low >= high) return;
////        int p = partition(a, low, high);
////        quicksort(a, low, p - 1);
////        quicksort(a, p + 1, high);
////    }
////
////    private static int partition(int[] a, int low, int high) {
////        int pivot = a[high];
////        int i = low - 1;
////        for (int j = low; j < high; j++) {
////            comparisons++;
////            if (a[j] <= pivot) {
////                i++;
////                swap(a, i, j);
////            }
////        }
////        swap(a, i + 1, high);
////        return i + 1;
////    }
////
////    private static void swap(int[] a, int i, int j) {
////        int temp = a[i];
////        a[i] = a[j];
////        a[j] = temp;
////    }
////
////    public static void main(String[] args) {
////        int n = 1_000_000;
////
////        ArrayList<Integer> seq = new ArrayList<>(n);
////        for (int i = 1; i <= n; i++) seq.add(i);
////        Collections.shuffle(seq, new Random());
////
////        int[] A = seq.stream().mapToInt(Integer::intValue).toArray();
////        for(int i = 0; i<A.length;i++){
////            System.out.print(A[i]+" ");
////        }
////
////        quicksort(A, 0, n - 1);
////        System.out.println();
////        for(int i = 0; i<A.length;i++){
////            System.out.print(A[i]+" ");
////        }
////
////        double Expected = 2.0 * n * Math.log(n);
////        double ratio = comparisons / Expected;
////
////        System.out.println("Comparisons X = " + comparisons);
////        System.out.println("Expected E = " + Expected);
////        System.out.println("X / E = " + ratio);
////    }
////}
