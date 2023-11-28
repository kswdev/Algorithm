package bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedTransferQueue;

public class BOJ_16234 {

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static int N, L, R;
    public static int Time = 0;

    public static int[][] map;
    public static boolean[][] visited;

    public static Queue<int []> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        visited = new boolean[N][N];
        map     = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(0, 0);
    }

    public static void bfs(int x, int y) {
        q = new LinkedList<>();
        q.offer(new int[] {x, y});

        while (!q.isEmpty()) {
            int nx = q.peek()[0];
            int ny = q.peek()[1];

            q.poll();

            for(int i = 0; i < 4; i++) {
                int curx = nx + dx[i];
                int cury = ny + dy[i];

                if(curx < 0 || cury < 0 || curx >= N || cury >= N
                            || !isOpen(map[nx][ny], map[curx][cury])) continue;

                q.offer(new int[] {curx, cury});
            }
        }
    }
    public static boolean isOpen(int a, int b) {
        int diff = Math.abs(a-b);
        return (L <= diff) && (diff <= R);
    }
}
