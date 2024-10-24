package dynamic_programming.gold3.app_activate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_7579 {

    private static final int INF = 10000001;
    private static int[] memory;
    private static int[] activate;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dp = new int[n + 1][m + 1];

        memory = new int[n + 1];
        activate = new int[10001];

        StringTokenizer memoryTokenizer = new StringTokenizer(br.readLine());
        StringTokenizer activateTokenizer = new StringTokenizer(br.readLine());

        Arrays.fill(dp[0], INF);

        for (int i = 1; i <= n; i++) {
            int appMemory = Integer.parseInt(memoryTokenizer.nextToken());
            int appActivate = Integer.parseInt(activateTokenizer.nextToken());

            memory[i] = appMemory;
            activate[i] = appActivate;
        }

        System.out.println(bottomUp(n, m));
    }

    private static int bottomUp(int n, int m) {

        for (int i = 1; i <= n; i++) {
            int memoryValue = memory[i];
            int activateValue = activate[i];

            for (int j = 0; j <= 10000; j++) {

                if (i == 1) {
                    if (activateValue == 0) dp[i][j] = memoryValue;
                } else {

                }
            }
        }

        return dp[n][m];
    }
}