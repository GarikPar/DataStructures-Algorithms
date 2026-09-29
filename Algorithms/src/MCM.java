public class MCM {
    public static int[][] m;
    public static int[][] s;

    public static void main(String[] args) {
        int[] p = {2,5,4,1,10,2};
        int n = p.length - 1;
        m = new int[n+1][n+1];
        s = new int[n+1][n+1];

        for(int i=1;i<=n;i++) m[i][i]=0;

        for(int L=2;L<=n;L++){
            for(int i=1;i<=n-L+1;i++){
                int j=i+L-1;
                m[i][j] = Integer.MAX_VALUE;
                for(int k=i;k<j;k++){
                    int q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if(q < m[i][j]){
                        m[i][j]=q;
                        s[i][j]=k;
                    }
                }
            }
        }

        System.out.println("Minimum number of multiplications: " + m[1][n]);
        System.out.println("Optimal Parenthesization: " + printOptimal(1,n));
    }

    public static String printOptimal(int i, int j){
        if(i==j) return "A"+i;
        return "(" + printOptimal(i, s[i][j]) + " x " + printOptimal(s[i][j]+1,j) + ")";
    }
}