package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9465 {

    private static int[][] sticker;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            sticker = new int[3][N+1];
            dp      = new int[3][N+1];

            for (int i = 1; i <= 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int n = 1; n <= N; n++) {
                    sticker[i][n] = Integer.parseInt(st.nextToken());
                }
            }

            dp[1][1] = sticker[1][1];
            dp[2][1] = sticker[2][1];

            for (int n = 2; n <= N; n++) {
                dp[1][n] = Math.max(dp[2][n-1] + sticker[1][n], dp[2][n-2] + sticker[1][n]);
                dp[2][n] = Math.max(dp[1][n-1] + sticker[2][n], dp[1][n-2] + sticker[2][n]);
            }

            sb.append(Math.max(dp[1][N], dp[2][N])).append("\n");
        }
        System.out.println(sb);
    }
}
