package dynamic_programming.gold3.app_activate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_7579 {

    private static int[] memory;
    private static int[] activate;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        memory = new int[n + 1];
        activate = new int[10001];

        StringTokenizer memoryTokenizer = new StringTokenizer(br.readLine());
        StringTokenizer activateTokenizer = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            int appMemory = Integer.parseInt(memoryTokenizer.nextToken());
            int appActivate = Integer.parseInt(activateTokenizer.nextToken());

            memory[i] = appMemory;
            activate[i] = appActivate;
        }


        int sum = Arrays.stream(activate).sum();

        dp = new int[n + 1][sum+1];
        System.out.println(bottomUp(n, m, sum));
    }

    private static int bottomUp(int n, int m, int sum) {
        int result = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            int memoryValue = memory[i];
            int activateValue = activate[i];

            for (int j = 0; j <= sum; j++) {

                if (activateValue <= j) {
                    dp[i][j] = Math.max(dp[i-1][j-activateValue] + memoryValue, dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }

                if (dp[i][j] >= m) {
                    result = Math.min(result, j);
                    break;
                }
            }
        }

        return result;
    }
}