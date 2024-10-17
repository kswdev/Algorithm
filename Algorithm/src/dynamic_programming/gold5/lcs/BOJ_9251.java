package dynamic_programming.gold5.lcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9251 {

    private static String t1;
    private static String t2;

    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t1 = br.readLine();
        t2 = br.readLine();

        dp = new int[t1.length()+1][t2.length()+1];

        for (int i = 1; i <= t1.length(); i++) {
            for (int j = 1; j <= t2.length(); j++) {
                if(t1.charAt(i-1) == t2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        System.out.println(dp[t1.length()][t2.length()]);
    }
}
