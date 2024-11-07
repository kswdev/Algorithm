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

        dp = new int[N+2];
        time = new int[N+2];
        price = new int[N+2];

        int max = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(st.nextToken());
            price[i] = Integer.parseInt(st.nextToken());

            if (max < dp[i])
                max = dp[i];

            int finishedDay = i + time[i];

            if (finishedDay <= N + 1)
                dp[finishedDay] = Math.max(dp[finishedDay], max + price[i]);
        }

        System.out.println(max);
    }
}