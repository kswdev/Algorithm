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
        if (dp[N][N] == -1) System.out.println(0);
        else System.out.println(dp[N][N]);
    }

    private static int countPreviousMethods(int i, int j) {
        if (i == 1 && j >= 2)
            return firstRow(j);

        return countRightward(i, j) + countDownward(i, j) - countDiagonal(i, j);
    }

    private static int firstRow(int x) {

        for (int i = 2; i < x; i++)
            if (isWall(1, i))
                return 0;

        return 1;
    }

    private static int countDiagonal(int i, int j) {
        if (isWall(i-1, j) || isWall(i, j-1) || isWall(i-1, j-1)) return 0;
        return dp[i-1][j-1];
    }

    private static int countRightward(int i, int j) {
        return sumDpRowBefore(i-1, j-1);
    }

    private static int countDownward(int i, int j) {
        return sumDpColumnBefore(i-1, j-1);
    }

    private static int sumDpRowBefore(int y , int x) {
        int sum = 0;

        for (int j = 1; j <= x; j++)
            if (isWall(y+1, j))
                sum = 0;
            else if (!isWall(y, j+1) && !isWall(y, j))
                sum += dp[y][j];

        return sum;
    }

    private static int sumDpColumnBefore(int y, int x) {
        int sum = 0;

        for (int i = 1; i <= y; i++)
            if (isWall(i, x+1))
                sum = 0;
            else if (!isWall(i+1, x) && !isWall(i, x))
                sum += dp[i][x];

        return sum;
    }

    private static boolean isWall(int i, int j) {
        return dp[i][j] == -1;
    }
}
