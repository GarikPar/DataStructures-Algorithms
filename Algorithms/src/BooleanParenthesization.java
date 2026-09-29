public class BooleanParenthesization {

    static String buildParenthesization(int i, int j, int[][] split, char[] B) {
        if (i == j) return String.valueOf(B[2 * i]);

        int k = split[i][j];
        char op = B[2 * k + 1];

        return "(" + buildParenthesization(i, k, split, B) +
                " " + op + " " +
                buildParenthesization(k + 1, j, split, B) + ")";
    }

    public static void main(String[] args) {
        char[] B = {'0','&','1','^','1','|','0'};
        int n = (B.length + 1) / 2;

        int[][] T = new int[n][n];
        int[][] F = new int[n][n];
        int[][] split = new int[n][n];


        for (int i = 0; i < n; i++) {
            if (B[2 * i] == '1') {
                T[i][i] = 1;
                F[i][i] = 0;
            } else {
                T[i][i] = 0;
                F[i][i] = 1;
            }
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                for (int k = i; k < j; k++) {
                    char op = B[2 * k + 1];

                    int lt = T[i][k], lf = F[i][k];
                    int rt = T[k + 1][j], rf = F[k + 1][j];

                    if (op == '&') {
                        T[i][j] += lt * rt;
                        F[i][j] += lt * rf + lf * rt + lf * rf;
                        if (lt * rt > 0) split[i][j] = k;
                    } else if (op == '|') {
                        T[i][j] += lt * rt + lt * rf + lf * rt;
                        F[i][j] += lf * rf;
                        if (lt * rt + lt * rf + lf * rt > 0) split[i][j] = k;
                    } else if (op == '^') {
                        T[i][j] += lt * rf + lf * rt;
                        F[i][j] += lt * rt + lf * rf;
                        if (lt * rf + lf * rt > 0) split[i][j] = k;
                    }
                }
            }
        }

        if (T[0][n - 1] > 0) {
            System.out.println("Can evaluate to TRUE");
            System.out.println("One valid parenthesization:");
            System.out.println(buildParenthesization(0, n - 1, split, B));
        } else {
            System.out.println("Cannot evaluate to TRUE");
        }
    }
}