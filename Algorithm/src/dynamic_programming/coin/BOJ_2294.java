package dynamic_programming.coin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2294 {

    private static int N, K;
    private static int coins[];
    private static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K+1];
        coins = new int[N+1];

        Arrays.fill(dp, 100001);

        for (int i = 1; i <= N; i++) {
            coins[i] = Integer.parseInt(br.readLine());

            if (K >= coins[i])
                dp[coins[i]] = 1;

            for (int j = coins[i]; j <= K; j++) {
                dp[j] = Math.min(dp[j], dp[j-coins[i]] + 1);
            }
        }

        if (dp[K] == 100001)
            System.out.println(-1);
        else
            System.out.println(dp[K]);
    }
}
