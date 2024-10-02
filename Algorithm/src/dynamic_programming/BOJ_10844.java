package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10844 {

    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[N+1][10];

        for (int i = 1; i <= 9; i++)
            dp[1][i] = 1;

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= 9; j++) {
                if (j == 1)
                    dp[i][j] = dp[i-1][j] + i-1;

                else if (j == 9)
                    dp[i][j] = 1;

                else
                    dp[i][j] = dp[i-1][j] * 2;
            }
        }

        int result = 0;
        for (int i = 1; i <= 9; i++) {
            result += dp[N][i];
        }
        System.out.println(result);
    }
}
