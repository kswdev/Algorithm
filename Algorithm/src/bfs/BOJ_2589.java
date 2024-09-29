package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589 {
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int Y;
    private static int X;
    private static char[][] map;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        int max = 0;
        map = new char[Y][X];
        visited = new boolean[Y][X];

        for (int i = 0; i < Y; i++) {
            String row = br.readLine();
            for (int j = 0; j < X; j++) {
                map[i][j] = row.charAt(j);
            }
        }

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if (map[i][j] == 'L') {
                    visited = new boolean[Y][X];
                    max = Math.max(bfs(i, j), max);
                }
            }
        }

        System.out.println(max);
    }

    private static int bfs(int y, int x) {
        visited[y][x] = true;
        int result = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {y, x, 0});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int cury = poll[0];
            int curx = poll[1];
            int time = poll[2];

            for (int i = 0; i < 4; i++) {
                int nexty = cury + dy[i];
                int nextx = curx + dx[i];
                int nextT = time + 1;
                if (nexty < 0 || nextx < 0 || nexty >= Y || nextx >= X) continue;
                if (!visited[nexty][nextx] && map[nexty][nextx] != 'W') {
                    visited[nexty][nextx] = true;
                    queue.add(new int[]{nexty, nextx, nextT});
                }
            }

            result = Math.max(time, result);
        }

        return result;
    }
}