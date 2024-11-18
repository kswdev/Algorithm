package dynamic_programming.gold5.calculate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5557 {

    private static int N;
    private static int MAX = 20;
    private static int MIN = 0;
    private static int[] arr;
    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N+1];
        dp  = new long[N+1][MAX + 1];

        for (int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        dp[1][arr[1]] = 1;

        int plus;
        int minus;

        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i-1][j] != 0) {
                    plus = j + arr[i];
                    minus = j - arr[i];

                    if (MIN <= plus && plus <= MAX)
                        dp[i][plus] += dp[i-1][j];

                    if (MIN <= minus && minus <= MAX)
                        dp[i][minus] += dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N-1][arr[N]]);
    }
}