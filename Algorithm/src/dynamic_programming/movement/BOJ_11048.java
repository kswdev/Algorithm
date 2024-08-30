package dynamic_programming.movement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11048 {

    private static int N, M;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp  = new int[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= M; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken()) + tripleMax(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]);
            }
        }

        System.out.println(dp[N][M]);
    }

    private static int tripleMax(int dp1, int dp2, int dp3) {
        return Math.max(dp1, Math.max(dp2, dp3));
    }
}
