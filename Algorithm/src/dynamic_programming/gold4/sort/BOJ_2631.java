package dynamic_programming.gold4.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2631 {

    private static int N;
    private static int[] children;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        children = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            children[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {

            }
        }

        System.out.println(children.length - dp[N][N]);
    }
}
/*
       3  7  5  2  6  1  4
    3  1
    7     2
    5        3
    2           1
    6              3
    1                 1
    4                    2
 */