import java.util.Arrays;

public class FastMatrixMultiplication {

    private static final double INF = Double.POSITIVE_INFINITY;


    public static Result extendShortestPaths(double[][] D, int[][] Pi, double[][] W, int[][] Pi_W, int n) {
        double[][] D_new = new double[n][n];
        int[][] Pi_new = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(D_new[i], INF);
            Arrays.fill(Pi_new[i], -1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (D[i][k] != INF && W[k][j] != INF) {
                        if (D[i][k] + W[k][j] < D_new[i][j]) {
                            D_new[i][j] = D[i][k] + W[k][j];
                            if (k == j) {
                                Pi_new[i][j] = Pi[i][j];
                            } else {
                                Pi_new[i][j] = Pi_W[k][j];
                            }
                        }
                    }
                }
            }
        }
        return new Result(D_new, Pi_new);
    }


    public static Result fasterAllPairsShortestPaths(double[][] W, int n) {
        double[][] D = new double[n][n];
        int[][] Pi = new int[n][n];

        // Initialization
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                D[i][j] = W[i][j];
                if (i != j && W[i][j] < INF) {
                    Pi[i][j] = i;
                } else {
                    Pi[i][j] = -1;
                }
            }
        }

        int m = 1;
        while (m < n - 1) {
            Result res = extendShortestPaths(D, Pi, D, Pi, n);
            D = res.D;
            Pi = res.Pi;
            m *= 2;
        }
        return new Result(D, Pi);
    }

    public static void main(String[] args) {
        double[][] W = {
                {0, 3, 8, INF, -4},
                {INF, 0, INF, 1, 7},
                {INF, 4, 0, INF, INF},
                {2, INF, -5, 0, INF},
                {INF, INF, INF, 6, 0}
        };

        int n = W.length;
        Result finalResult = fasterAllPairsShortestPaths(W, n);

        System.out.println("Matrix D(4) (Shortest Path Weights):");
        printDoubleMatrix(finalResult.D);

        System.out.println("\nMatrix Pi(4) (Predecessors, 1-indexed, -1 for NIL):");
        printPiMatrix(finalResult.Pi);
    }


    static class Result {
        double[][] D;
        int[][] Pi;

        Result(double[][] D, int[][] Pi) {
            this.D = D;
            this.Pi = Pi;
        }
    }

    private static void printDoubleMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double val : row) {
                if (val == INF) System.out.printf("%5s ", "INF");
                else System.out.printf("%5.0f ", val);
            }
            System.out.println();
        }
    }

    private static void printPiMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                if (val == -1) System.out.printf("%3d ", -1);//-1 is Nil
                else System.out.printf("%3d ", val + 1);
            }
            System.out.println();
        }
    }
}