package dynamic_programming.gold5.coin;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_9084 {

    private static int[] coins;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {

            int n = Integer.parseInt(br.readLine());

            coins = new int[n+1];

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++)
                coins[j] = Integer.parseInt(st.nextToken());

            int money = Integer.parseInt(br.readLine());

            dp = new int[money+1];

            for (int m = 1; m <= money; m++) {
                for (int coin : coins) {
                    if (coin == m)
                        dp[m] += 1;
                    else if (coin < m) {
                        dp[m] = dp[m-coin] + 1;
                    }
                }
            }
        }

        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }
}

/*
        1 2 3 4 5 6 7 8 9 10
     1  1 1 1 1 1 1 1 1 1 1
     2  1 2 2 3 3 4 4 5 5 6
     5  1 2 2 3 4 5 5 6 6 8
 */
