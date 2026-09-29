public class RodCutting {

    public static void rodCut(int[] p, int n) {
        int[] r = new int[n + 1];
        int[] s = new int[n + 1];

        r[0] = 0;

        for (int i = 1; i <= n; i++) {
            int q = Integer.MIN_VALUE;

            for (int j = 1; j <= i; j++) {
                if (p[j] + r[i - j] > q) {
                    q = p[j] + r[i - j];
                    s[i] = j;
                }
            }
            r[i] = q;
        }

        System.out.println("r array:");
        for (int i = 0; i <= n; i++)
            System.out.print(r[i] + " ");

        System.out.println("\ns array:");
        for (int i = 0; i <= n; i++)
            System.out.print(s[i] + " ");


    }
    public static void main(String[] args) {
        int[] p = {0, 1, 3, 5, 5, 7, 8, 8, 9}; // index 0 unused
        int n = 8;

        rodCut(p, n);
    }
}
