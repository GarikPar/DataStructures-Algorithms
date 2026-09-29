public class CountOccurrences {

    static int findFirst(int[] A, int k) {
        int low = 0, high = A.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (A[mid] == k) {
                result = mid;
                high = mid - 1; // search left part
            } else if (A[mid] < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    static int findLast(int[] A, int k) {
        int low = 0, high = A.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (A[mid] == k) {
                result = mid;
                low = mid + 1; // search right part
            } else if (A[mid] < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    static int countOccurrences(int[] A, int k) {
        int first = findFirst(A, k);
        if (first == -1) return 0;

        int last = findLast(A, k);
        return last - first + 1;
    }

    public static void main(String[] args) {
        int[] A = {1,2,2,3,4,5,5,7,7,8,9};
        int k = 5;

        int count = countOccurrences(A, k);
        System.out.print("I have array: {");
        for(int num:A){
            System.out.print(num+",");
        }
        System.out.print("}");
        System.out.println();
        System.out.println("My k is :" +" "+ k);
        System.out.println("Number of occurrences: " + count);
    }
}