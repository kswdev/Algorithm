package dynamic_programming.gold5.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15486 {

    private static int N;
    private static int[] dp;
    private static int[] time;
    private static int[] price;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];
        time = new int[N+1];
        price = new int[N+1];

        int maxValue = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(st.nextToken());
            price[i] = Integer.parseInt(st.nextToken());

            for (int j = 1; j <= i; j++) {
                int finishedDay = (j-1) + time[j];

                if (finishedDay > i) {
                    dp[j] = dp[j-1];
                    continue;
                }

                dp[i] = Math.max(dp[j-1] + price[j], dp[i]);

                if (dp[i] > maxValue) maxValue = dp[i];
            }
        }

        System.out.println(maxValue);
    }
}
