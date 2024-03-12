package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11606 {

    private static int N, M;
    private static int[][] map;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map  = new int[N+1][N+1];
        dp   = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int nextToken = Integer.parseInt(st.nextToken());
                map[i][j]  = map[i][j-1] + nextToken;
                dp[i][j] = dp[i-1][j] + map[i][j];
            }
        }

        for (int i = 0; i < M; i++) {
            int x1, y1, x2, y2;

            st = new StringTokenizer(br.readLine());

            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            System.out.println(sum(x1, y1, x2, y2));
        }
    }

    private static int sum(int x1, int y1, int x2, int y2) {
        int big = dp[x2][y2];
        int dup = dp[x1-1][y1-1];
        int left = dp[x1-1][y2];
        int right = dp[x2][y1-1];

        return big + dup - left - right;
    }
}
