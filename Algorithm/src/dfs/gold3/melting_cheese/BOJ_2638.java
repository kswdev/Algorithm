package dfs.gold3.melting_cheese;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2638 {
    private static int hour = 0;
    private static int N, M;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    private static int[][] cheeseMap;
    private static boolean[][] visited;
    private static boolean[][] air;
    private static Queue<int []> cheeseQueue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        air     = new boolean[N][M];
        visited = new boolean[N][M];
        cheeseMap   = new int[N][M];
        cheeseQueue = new LinkedList<>();

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                cheeseMap[i][j] = Integer.parseInt(st.nextToken());
                if (cheeseMap[i][j] == 1) cheeseQueue.add(new int[] {i, j});
            }
        }

        while (!isComplete()) {
            airCheck();

            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {

                    if (cheeseMap[i][j] == 1) {
                        visited[i][j] = true;
                        dfs(i, j);
                    }
                }
            }

            hour++;
        }

        System.out.println(hour);
    }

    private static boolean isComplete() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cheeseMap[i][j] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void dfs (int x, int y) {

        if (isMelting(x, y)) cheeseMap[x][y] = 9;

        for (int i = 0; i < 4; i++) {
            int curx = dx[i] + x;
            int cury = dy[i] + y;

            if (curx < 0 || curx >= N || cury < 0 || cury >= M) continue;

            if (cheeseMap[curx][cury] == 1 && !visited[curx][cury]) {
                visited[curx][cury] = true;
                dfs(curx, cury);
            }
        }
    }

    private static boolean isMelting(int x, int y) {

        int count = 0;

        for (int i = 0; i < 4; i++) {
            int curx = dx[i] + x;
            int cury = dy[i] + y;

            if (curx < 0 || curx >= N || cury < 0 || cury >= M) continue;

            if (cheeseMap[curx][cury] == 9 && !visited[curx][cury]) {
                count++;
            }
        }

        return count >= 2;
    }

    private static void airCheck() {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N][M];

        q.add(new int[] {0,0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int px = pos[0], py = pos[1];

            for (int i=0; i<4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (nx<0 || nx>N-1 || ny<0 || ny>M-1 || visited[nx][ny]) continue;

                if (cheeseMap[nx][ny] != 1) {
                    cheeseMap[nx][ny] = 9;
                    visited[nx][ny] = true;
                    q.add(new int[] {nx, ny});
                }
            }
        }
    }
}