package dynamic_programming.gold4.rgb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17404 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int INF = 1000*1000;

        int n = Integer.parseInt(br.readLine());

        int[][] colorPrice = new int[n][3];

        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            colorPrice[i][0] = Integer.parseInt(st.nextToken());
            colorPrice[i][1] = Integer.parseInt(st.nextToken());
            colorPrice[i][2] = Integer.parseInt(st.nextToken());
        }

        for (int c = 0; c < 3; c++) {
            int[][] dp = new int[n][3];

            int[] tempColor = colorPrice[n-1].clone();
            colorPrice[n-1][c] = INF;

            dp[0][c] = colorPrice[0][c];
            dp[0][right(c)] = INF;
            dp[0][left(c)] = INF;

            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + colorPrice[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + colorPrice[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + colorPrice[i][2];

                if (i == n-1) {
                    minPrice = Math.min(tripleMathMin(dp[i][0], dp[i][1], dp[i][2]), minPrice);
                }
            }

            colorPrice[n-1] = tempColor;
        }

        System.out.println(minPrice);
    }

    private static int tripleMathMin(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
    private static int right(int i) {
        return (i+1) % 3;
    }

    private static int left(int i) {
        return (i+2) % 3;
    }
}
