package dfs.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1926 {

    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    private static int[][] map;
    private static boolean[][] visited;
    private static int N, M;
    private static int cnt = 0, width = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer row = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(row.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    int wid = dfs(i, j, 0);
                    width = Math.max(wid, width);
                    cnt++;
                }
            }
        }

        br.close();

        System.out.println(cnt);
        System.out.println(width);
    }

    private static int dfs(int x, int y, int wid) {
        wid++;
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int curx = x + dx[i];
            int cury = y + dy[i];

            if (curx < 0 || cury < 0 || curx >= N || cury >= M || visited[curx][cury]) continue;

            if (map[curx][cury] == 1) {
                wid += dfs(curx, cury, 0);
            }
        }

        return wid;
    }
}
