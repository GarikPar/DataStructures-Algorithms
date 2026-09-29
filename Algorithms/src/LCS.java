public class LCS {

    public static void lcs(char[] X, char[] Y) {
        int m = X.length;
        int n = Y.length;

        int[][] c = new int[m + 1][n + 1];
        char[][] b = new char[m + 1][n + 1];

        // Build tables
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X[i - 1] == Y[j - 1]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = '↖';
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = '↑';
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = '←';
                }
            }
        }

        System.out.println("Matrix c:");
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }


        System.out.println("\nMatrix b:");
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }

        System.out.print("\nLCS: ");
        printLCS(b, X, m, n);
    }

    public static void printLCS(char[][] b, char[] X, int i, int j) {
        if (i == 0 || j == 0) return;

        if (b[i][j] == '↖') {
            printLCS(b, X, i - 1, j - 1);
            System.out.print(X[i - 1] + " ");
        } else if (b[i][j] == '↑') {
            printLCS(b, X, i - 1, j);
        } else {
            printLCS(b, X, i, j - 1);
        }
    }

    public static void main(String[] args) {
        char[] X = {'B','C','B','A','C','D'};
        char[] Y = {'B','A','C','A','D','B'};

        lcs(X, Y);
    }
}