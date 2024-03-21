package dfs.gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2638_melting_cheese {

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

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (cheeseMap[i][j]) {
                    airCheck();

                    visited = new boolean[N][M];

                    if (cheeseMap[i][j] == 1) {
                        visited[i][j] = true;
                        dfs(i, j);
                        hour++;
                    }
                }
            }
        }


        System.out.println(hour);
    }

    private static void dfs (int x, int y) {

        if (isMelting(x, y)) cheeseMap[x][y] = 0;

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

            if (cheeseMap[curx][cury] == 0 && !visited[curx][cury] && air[curx][cury]) {
                count++;
            }
        }

        return count >= 2;
    }

    private static void airCheck() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cheeseMap[i][j] == 0 && !air[i][j]) {
                    air[i][j] = true;
                    airDfs(i, j);
                }
            }
        }
    }

    private static void airDfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int curx = dx[i] + x;
            int cury = dy[i] + y;

            if (curx < 0 || curx >= N || cury < 0 || cury >= M) continue;

            if (cheeseMap[curx][cury] == 0 && !air[curx][cury]) {
                air[curx][cury] = true;
                airDfs(curx, cury);
            }
        }
    }
}
