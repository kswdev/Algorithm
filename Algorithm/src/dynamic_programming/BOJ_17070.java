package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17070 {

    private static int N;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp  = new int[N+1][N+1];
        dp[1][2] = 1;

        for (int i = 1; i <= N; i++) {
            String row = br.readLine();
            String[] rows = row.split(" ");
            for (int j = 1; j <= N; j++) {

                if (Integer.parseInt(rows[j-1]) == 1) {
                    dp[i][j] = 0;
                    continue;
                }

                dp[i][j] = countPreviousMethods(i, j);

            }
        }
    }

    private static int countPreviousMethods(int i, int j) {

        return dp[i-1][j] + dp[i][j-1] + dp[i-1][j-1];
    }
}
