package dfs.gold3.panda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1937 {

    private static int N;
    private static int max = 0;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int[][] map;
    private static int[][] dp;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N   = Integer.parseInt(br.readLine());

        visited = new boolean[N][N];
        map = new int[N][N];
        dp  = new int[N][N];

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = true;
                max = Math.max(pandaMove(i, j), max);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    private static int pandaMove(int x, int y) {


        if (dp[x][y] != 0)
            return dp[x][y];

        dp[x][y] = 1;

        for (int i = 0; i < 4; i++) {

            int curX = x + dx[i];
            int curY = y + dy[i];

            if (curX < 0 || curY < 0 || curX >= N || curY >= N) continue;

            if (!visited[curX][curY] && map[curX][curY] > map[x][y]) {
                visited[curX][curY] = true;
                dp[x][y] = Math.max(pandaMove(curX, curY) + 1, dp[x][y]);
                visited[curX][curY] = false;
            }
        }

        return dp[x][y];
    }
}
