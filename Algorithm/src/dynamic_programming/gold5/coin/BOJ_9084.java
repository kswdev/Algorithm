package dynamic_programming.gold5.coin;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_9084 {

    private static int[] coins;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {

            int value = 0;
            int n = Integer.parseInt(br.readLine());

            coins = new int[n+1];

            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++)
                coins[j] = Integer.parseInt(st.nextToken());

            int money = Integer.parseInt(br.readLine());

            dp = new int[n+1][money+1];


            for (int c = 1; c <= n; c++) {
                int coin = coins[c];
                dp[c][0] = 1;

                for (int m = 1; m <= money; m++) {
                    if (m >= coin)
                        dp[c][m] = dp[c-1][m] + dp[c][m-coin];
                    else
                        dp[c][m] = dp[c-1][m];
                }

                value = Math.max(value, dp[c][money]);
            }

            sb.append(value).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

/*
        1 2 3 4 5 6 7 8 9 10
     1  1 1 1 1 1 1 1 1 1 1
     2  1 2 2 3 3 4 4 5 5 6
     5  1 2 2 3 4 5 6 7 8 10

        1 2 3 4 5 6 7 8 9 10
     2  0 1 0 1 0 1 0 1 0 1
     5  0 1 0 1 1 1 1 1 1 2
     7  0 1 0 1 1 1 2 1 2 2
 */
