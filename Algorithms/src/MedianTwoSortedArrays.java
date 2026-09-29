public class MedianTwoSortedArrays {

    public static int findNthSmallest(int[] A, int[] B) {
        int n = A.length;
        int low = 0, high = n;

        while (low <= high) {
            int i = (low + high) / 2;
            int j = n - i;

            int Aleft, Aright, Bleft, Bright;

            if (i == 0) {
                Aleft = Integer.MIN_VALUE;
            } else {
                Aleft = A[i - 1];
            }

            if (i == n) {
                Aright = Integer.MAX_VALUE;
            } else {
                Aright = A[i];
            }

            if (j == 0) {
                Bleft = Integer.MIN_VALUE;
            } else {
                Bleft = B[j - 1];
            }

            if (j == n) {
                Bright = Integer.MAX_VALUE;
            } else {
                Bright = B[j];
            }

            if (Aleft <= Bright && Bleft <= Aright) {
                return Math.max(Aleft, Bleft);
            } else if (Aleft > Bright) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] A = {1, 3, 5, 6, 7};
        int[] B = {2, 4, 5, 7, 8};

        int n = findNthSmallest(A, B);
        System.out.print("I have array: {");
        for(int num:A){
            System.out.print(num+",");
        }
        System.out.print("}");
        System.out.println();
        System.out.print("I have array: {");
        for(int num:B){
            System.out.print(num+",");
        }
        System.out.print("}");
        System.out.println();
        System.out.println("n-th smallest element = " + n);
    }
}