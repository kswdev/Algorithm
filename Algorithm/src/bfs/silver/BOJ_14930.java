package bfs.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14930 {
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int[] goal;
    private static int[][] map;
    private static int[][] dp;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;

                if (map[i][j] == 2) {
                    dp[i][j] = 0;
                    goal = new int[]{i, j};
                } else if (map[i][j] == 0) {
                    dp[i][j] = 0;
                }
            }
        }


        countAvailableMethods(goal[0], goal[1]);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void countAvailableMethods(int i, int j) {
        int count = 0;

        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {i, j, count});

        while (!queue.isEmpty()) {

            int cx = queue.peek()[0];
            int cy = queue.peek()[1];
            int cc = queue.peek()[2];

            queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = cx + dx[k];
                int ny = cy + dy[k];
                int nc = cc + 1;
                if (nx >= N || ny >= M || nx < 0 || ny < 0 || isWall(nx, ny))
                    continue;

                if (dp[nx][ny] == -1) {
                    dp[nx][ny] = nc;
                    queue.add(new int[] {nx, ny, nc});
                }
            }
        }
    }

    private static boolean isWall(int x, int y) {
        return map[x][y] == 0;
    }
}
