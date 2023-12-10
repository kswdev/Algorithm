package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2573 {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static int N, M;
    private static int[][] map;
    private static List<int[][]> day;
    private static Queue<int[]> q;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        day = new ArrayList<>();

        visited = new boolean[N+1][M+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(seperate());
    }

    public static void bfs(int x, int y) {
        q = new LinkedList<>();
        q.add(new int[] {x, y});

        while (!q.isEmpty()) {
            int nx = q.peek()[0];
            int ny = q.peek()[1];

            q.poll();

            for (int k = 0; k < 4; k++) {
                int curx = nx + dx[k];
                int cury = ny + dy[k];


            }
        }
    }

    public static int seperate() {
        visited = new boolean[N+1][M+1];

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void dfs(int i, int j) {
        visited[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int curx = i + dx[k];
            int cury = j + dy[k];

            if (curx < 0 || cury < 0 || curx >= N || cury >= M ||
                map[curx][cury] == 0 || visited[curx][cury]) continue;

            dfs(curx, cury);
        }
    }
}
