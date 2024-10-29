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

        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());

            time[n] = Integer.parseInt(st.nextToken());
            price[n] = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= n; i++) {
                int requiredDay = time[i];

                if (requiredDay <= n) {

                }
            }
        }
    }
}
