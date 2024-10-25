package dynamic_programming.gold4.rgb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17404 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] colorPrice = new int[n][3];
        int[][] dp = new int[n][3];

        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                colorPrice[i][j] = Integer.parseInt(st.nextToken());

                if (i <= 1)
                    dp[i][j] = colorPrice[i][j];
                else if (i == n-1) {
                    dp[i][j] = Math.min(dp[i-1][right(j)] + dp[0][left(j)], dp[i-1][left(j)] + dp[0][right(j)]) + colorPrice[i][j];
                    minPrice = Math.min(dp[i][j], minPrice);
                } else {
                    dp[i][j] = Math.min(dp[i-1][right(j)], dp[i-1][left(j)]) + colorPrice[i][j];
                }
            }
        }

        System.out.println(minPrice);
    }

    private static int right(int i) {
        return (i+1) % 3;
    }

    private static int left(int i) {
        return (i+2) % 3;
    }
}
