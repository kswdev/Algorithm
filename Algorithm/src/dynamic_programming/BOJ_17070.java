package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_17070 {

    private static int N;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp  = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            String row = br.readLine();
            String[] rows = row.split(" ");
            for (int j = 1; j <= N; j++) {

                if (Integer.parseInt(rows[j-1]) == 1) {
                    dp[i][j] = -1;
                    continue;
                }

                dp[i][j] = countPreviousMethods(i, j);
            }
        }
        //System.out.println(dp[N][N]);

        for (int i = 0; i <= N; i++) {
            System.out.println("");
            for (int j = 0; j <= N; j++)
                System.out.print(dp[i][j] + " ");
        }

    }

    private static int countPreviousMethods(int i, int j) {
        if (i == 1 && j == 2) return 1;
        return countRightward(i, j) + countDownward(i, j) + countDiagonal(i, j);
    }

    private static int countDiagonal(int i, int j) {
        if (dp[i-1][j] == -1 || dp[i][j-1] == -1 || dp[i-1][j-1] == -1) return 0;
        return dp[i-1][j-1];
    }

    private static int countRightward(int i, int j) {
        if (dp[i][j-1] == -1)
            return 0;
        else
            return sumDpRowBefore(i-1, j-1);
    }

    private static int countDownward(int i, int j) {
        if (dp[i-1][j] == -1)
            return 0;
        else
            return sumDpColumnBefore(i-1, j-1);
    }

    private static int sumDpRowBefore(int y , int x) {
        int sum = 0;

        for (int j = 1; j < x; j++)
            sum += dp[y][j];

        return sum;
    }

    private static int sumDpColumnBefore(int y, int x) {
        int sum = 0;

        for (int i = 1; i < y; i++)
            sum += dp[i][x];

        return sum;
    }
}
