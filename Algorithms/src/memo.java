public class memo {

    static int[] memo = new int[1000];
    static boolean[] vis = new boolean[1000];


    static void init() {
        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }
    }

    public static int fREC(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        return fREC(n - 3) + fREC(n / 2);
    }

    public static int fTD1(int n) {
        if (memo[n] != -1) return memo[n];

        if (n == 0) return memo[n] = 0;
        if (n == 1) return memo[n] = 1;
        if (n == 2) return memo[n] = 2;

        return memo[n] = fTD1(n - 3) + fTD1(n / 2);
    }

    public static int fTD2(int n) {
        if (vis[n]) return memo[n];

        if (n == 0) memo[n] = 0;
        else if (n == 1) memo[n] = 1;
        else if (n == 2) memo[n] = 2;
        else memo[n] = fTD2(n - 3) + fTD2(n / 2);

        vis[n] = true;
        return memo[n];
    }

    public static int fBU(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        if (n >= 1) dp[1] = 1;
        if (n >= 2) dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 3] + dp[i / 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {

        init();

        System.out.println("fREC(12): " + fREC(12));
        System.out.println("fTD1(12): " + fTD1(12));
        System.out.println("fTD2(12): " + fTD2(12));

        long start = System.nanoTime();
        int result = fBU(1000);
        long end = System.nanoTime();

        long timeMicro = (end - start) / 1000;

        System.out.println("fBU(1000): " + result);
        System.out.println("Time (microseconds): " + timeMicro);
    }
}
