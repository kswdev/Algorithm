package dynamic_programming.gold3.app_activate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_7579 {

    private static int N, M;
    private static final int INF = 10000001;
    private static int[] memory;
    private static int[] activate;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N+1][M+1];

        memory = new int[N+1];
        activate = new int[N+1];

        StringTokenizer memoryTokenizer = new StringTokenizer(br.readLine());
        StringTokenizer activateTokenizer = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int appMemory = Integer.parseInt(memoryTokenizer.nextToken());
            int appActivate = Integer.parseInt(activateTokenizer.nextToken());

            memory[i] = appMemory;
            activate[i] = appActivate;

            Arrays.fill(dp[i], INF);
        }

        System.out.println(topDown(N, M));
    }

    private static int topDown(int i, int m) {

        if (i < 1)
            return INF;

        if (dp[i][m] == INF) {

            if (memory[i] < m) {
                dp[i][m] = Math.min(topDown(i-1, m-memory[i]) + activate[i], topDown(i-1, m));
            } else {
                dp[i][m] = Math.min(topDown(i-1, m), activate[i]);
            }
        }

        return dp[i][m];
    }

    private static int bottomUp() {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {

            }
        }

        return dp[N][M];
    }
}