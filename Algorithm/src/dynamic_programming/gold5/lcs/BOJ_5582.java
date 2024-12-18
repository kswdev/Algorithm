package dynamic_programming.gold5.lcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5582 {

    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String sequenceOne = br.readLine();
        String sequenceTwo = br.readLine();

        dp = new int[sequenceOne.length()+1][sequenceTwo.length()+1];

        int count = 0;

        for (int i = 1; i <= sequenceOne.length(); i++) {
            for (int j = 1; j <= sequenceTwo.length(); j++) {
                if (sequenceOne.charAt(i-1) == sequenceTwo.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    count = Math.max(count, dp[i][j]);
                }
            }
        }

        System.out.println(count);
    }
}