package dynamic_programming.gold1.tps;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2098 {

    private static int N, checkVisit;
    private static int INF = 16000000;
    private static int[][] map;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        checkVisit = (1 << N) - 1;

        map = new int[N + 1][N+1];
        dp  = new int[N + 1][checkVisit];

        for (int n = 1; n <= N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[n], -1);
            for (int j = 1; j <= N; j++) {
                map[n][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve(1, 1));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int solve(int start, int check) {

        if (check == checkVisit) {
            if (map[start][1] == 0)
                return INF;
            else
                return map[start][1];
        }

        if (dp[start][check] != -1)
            return dp[start][check];

        dp[start][check] = INF;

        for (int i = 2; i <= N; i++) {
            int nextCheck = check | (1 << i - 1);

            if (map[start][i] == 0 || (check & (1 << (i - 1))) != 0) continue;

            dp[start][check] = Math.min(dp[start][check], solve(i, nextCheck) + map[start][i]);
        }

        return dp[start][check];
    }
}
