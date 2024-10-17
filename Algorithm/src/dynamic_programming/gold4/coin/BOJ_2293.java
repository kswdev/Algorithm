package dynamic_programming.gold4.coin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2293 {

    private static int N, K;
    private static int[] coins;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coins = new int[N+1];
        dp = new int[K+1];

        dp[0] = 1;

        for (int i = 1; i <= N; i++) {
            coins[i] = Integer.parseInt(br.readLine());

            for (int j = coins[i]; j <= K; j++) {
                dp[j] += dp[j-coins[i]];
            }
        }

        System.out.println(dp[K]);
    }
}
