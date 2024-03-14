package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2225 {

    private static int N, K;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N+1][K+1];

        int result = dynamic(N, K);
        System.out.println(result);
    }

    private static int dynamic(int n, int k) {

        if (n == 1) return k;
        if (k == 1) return 1;

        if (dp[n][k] == 0) dp[n][k] = dynamic(n-1, k) + dynamic(n, k-1);

        return dp[n][k]%1000000000;
    }
}