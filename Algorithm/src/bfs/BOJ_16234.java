package bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234 {

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static int N, L, R;
    public static int sum  = 0;
    public static int Time = 0;
    public static boolean flag = true;

    public static int[][] map;
    public static boolean[][] visited;

    public static Queue<int []> q;
    public static Queue<int []> union;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

        int average;

        do {
            flag    = true;
            union   = new LinkedList<>();
            visited = new boolean[N][N];


            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        union = bfs(i, j);

                        if(union.size() == 1) continue;

                        average = sum/union.size();

                        while (!union.isEmpty()) {
                            map[union.peek()[0]][union.peek()[1]] = average;
                            union.poll();
                        }
                        sum = 0;
                    }
                }
            }

            if(!flag) {
                Time++;
            }

        } while (!flag);

        System.out.println(Time);

        br.close();
    }

    public static Queue<int []> bfs(int x, int y) {

        Queue<int []> uni = new LinkedList<>();

        q = new LinkedList<>();
        q.offer(new int[] {x, y});
        uni.add(new int[] {x, y});

        sum = map[x][y];

        visited[x][y] = true;

        while (!q.isEmpty()) {
            int nx = q.peek()[0];
            int ny = q.peek()[1];

            q.poll();

            for(int i = 0; i < 4; i++) {
                int curx = nx + dx[i];
                int cury = ny + dy[i];

                if(curx < 0 || cury < 0 || curx >= N || cury >= N
                            || visited[curx][cury]
                            || !isOpen(map[nx][ny], map[curx][cury])) continue;

                q.offer(new int[] {curx, cury});
                uni.add(new int[] {curx, cury});

                visited[curx][cury] = true;
                sum += map[curx][cury];
                flag = false;
            }
        }

        return uni;
    }
    public static boolean isOpen(int a, int b) {
        int diff = Math.abs(a-b);
        return (L <= diff) && (diff <= R);
    }
}
