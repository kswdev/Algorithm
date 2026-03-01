package dynamic_programming.gold5.infinite_sequence;

/*
    A[0] = 1
    A[i] = A[i/P] + A[i/Q] (i ≥ 1)
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1351 {

    private static int N, P, Q;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        dp = new int[N+1];
        dp[0] = 1;

        System.out.println(solve(N));
    }

    private static int solve (int n) {
        if (dp[n] != 0) return dp[n];

        dp[n] = solve(n/P) + solve(n/Q);

        return dp[n];
    }
}
