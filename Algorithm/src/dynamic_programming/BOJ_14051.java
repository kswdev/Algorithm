package dynamic_programming;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14051 {

    private static int N;
    private static int[] t;
    private static int[] p;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        t  = new int[N+1];
        p  = new int[N+1];
        dp = new int[N+1];

        int max = 0;

        String input;
        for(int i = 0; i < N; i++) {
            input = br.readLine();
            t[i] = Integer.parseInt(input.split(" ")[0]);
            p[i]  = Integer.parseInt(input.split(" ")[1]);
        }

        for(int i = 0; i <= N; i++) {
            dp[i] = Math.max(max, dp[i]);

            if (t[i] + i <= N) {
                dp[t[i] + i] = Math.max(dp[t[i] + i], p[i] + dp[i]);
            }

            max = Math.max(max, dp[i]);

        }

        System.out.println(max);
    }
}
