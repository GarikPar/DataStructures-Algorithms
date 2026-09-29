public class InversionCount {

    public static long countInversions(int[] arr) {
        int[] temp = new int[arr.length];
        return mergeSort(arr, temp, 0, arr.length - 1);
    }

    private static long mergeSort(int[] arr, int[] temp, int left, int right) {
        long invCount = 0;

        if (left < right) {
            int mid = (left + right) / 2;

            invCount += mergeSort(arr, temp, left, mid);
            invCount += mergeSort(arr, temp, mid + 1, right);
            invCount += merge(arr, temp, left, mid, right);
        }

        return invCount;
    }

    private static long merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        long invCount = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                invCount += (mid - i + 1);
            }
        }

        while (i <= mid)
            temp[k++] = arr[i++];

        while (j <= right)
            temp[k++] = arr[j++];

        for (i = left; i <= right; i++)
            arr[i] = temp[i];

        return invCount;
    }

    public static void main(String[] args) {
        int[] A = {3, 2, 4, 1};
        System.out.print("I have array: {");
        for(int num:A){
            System.out.print(num+",");
        }
        System.out.print("}");
        System.out.println();

        long inversions = countInversions(A);


        System.out.println("Number of inversions: " + inversions);
    }
}