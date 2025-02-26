package dfs.gold._3.melting_cheese;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636 {

    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int N, M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int time = 0;
        int cnt  = 0;

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) cnt++;
            }
        }

        airInit(0, 0);

        while (!isCompleteMelting()) {

            time++;
            airCheck();

            Queue<int[]> queue = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 && isMelting(i, j)) {
                        queue.add(new int[]{i, j});
                        cnt = queue.size();
                    }
                }
            }

            while (!queue.isEmpty()) {
                int currentX = queue.peek()[0];
                int currentY = queue.peek()[1];
                queue.poll();

                map[currentX][currentY] = 9;
            }
        }

        System.out.println(time);
        System.out.println(cnt);
    }

    private static void airCheck() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && isAir(i, j)) {
                    map[i][j] = 9;
                }
            }
        }
    }

    private static boolean isCompleteMelting() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) return false;
            }
        }

        return true;
    }

    private static boolean isAir(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        boolean[][] check = new boolean[N][M];

        while (!queue.isEmpty()) {

            int currentX = queue.peek()[0];
            int currentY = queue.peek()[1];
            queue.poll();

            for (int i = 0; i < 4; i++) {
                int dX = currentX + dx[i];
                int dY = currentY + dy[i];

                if (dX < 0 || dY < 0 || dX >= N || dY >= M) continue;

                if (map[dX][dY] == 0 && !check[dX][dY]) {
                    check[dX][dY] = true;
                    queue.add(new int[]{dX, dY});
                } else if (map[dX][dY] == 9) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isMelting(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});

        while (!queue.isEmpty()) {

            int currentX = queue.peek()[0];
            int currentY = queue.peek()[1];
            queue.poll();

            for (int i = 0; i < 4; i++) {
                int dX = currentX + dx[i];
                int dY = currentY + dy[i];

                if (dX < 0 || dY < 0 || dX >= N || dY >= M) continue;

                if (map[dX][dY] == 9)
                    return true;
            }
        }

        return false;
    }

    private static void airInit(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        map[x][y] = 9;
        queue.add(new int[] {x, y});

        while (!queue.isEmpty()) {

            int currentX = queue.peek()[0];
            int currentY = queue.peek()[1];
            queue.poll();

            for (int i = 0; i < 4; i++) {
                int dX = currentX + dx[i];
                int dY = currentY + dy[i];

                if (dX < 0 || dY < 0 || dX >= N || dY >= M) continue;

                if (map[dX][dY] == 0) {
                    queue.add(new int[]{dX, dY});
                    map[dX][dY] = 9;
                }
            }
        }
    }
}
