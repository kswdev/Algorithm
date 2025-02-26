package bfs.gold._3.baby_shark;

import java.io.*;
import java.util.*;

public class BOJ_16236 {

    public static int N;
    public static int TIME;
    public static int[][] map;
    public static boolean[][] visited;

    private static Queue<Fish> q;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {

        Fish fish = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        List<Fish> list = new ArrayList<>();
        q = new LinkedList<>();

        map = new int[N+1][N+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 9) {
                    fish = new Fish(i, j, 2, 0, 0);
                    map[i][j] = 0;
                }

                if (map[i][j] == 1) {
                    list.add(new Fish(i, j, 1, 0, 0));
                }
            }
        }

        if (list.isEmpty()) {
            System.out.println(0);
            return;
        }

        br.close();

        bfs(fish);
    }


    private static void bfs(Fish shark) {

        Queue<Fish> que = new PriorityQueue<>(Comparator
                .comparingInt((Fish f) -> f.time)
                .thenComparingInt(f -> f.x)
                .thenComparingInt(f -> f.y));

        q.offer(shark);

        while (true) {
            visited = new boolean[N+1][N+1];
            visited[q.peek().x][q.peek().y] = true;

            while (!q.isEmpty()) {
                Fish curF = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = curF.x + dx[i];
                    int ny = curF.y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N ||
                        visited[nx][ny] || map[nx][ny] > curF.size) {
                        continue;
                    }

                    if (map[nx][ny] < curF.size && map[nx][ny] != 0) {
                        que.offer(new Fish(nx, ny, curF.size, curF.eatCnt + 1, curF.time + 1));
                    }

                    q.offer(new Fish(nx, ny, curF.size, curF.eatCnt, curF.time + 1));
                    visited[nx][ny] = true;
                }
            }

            if (que.isEmpty()) {
                System.out.println(TIME);
                return;
            }

            Fish fish = que.poll();
            if (fish.size == fish.eatCnt) {
                fish.size++;
                fish.eatCnt = 0;
            }

            map[fish.x][fish.y] = 0;
            TIME += fish.time;

            q.offer(new Fish(fish.x, fish.y, fish.size, fish.eatCnt, 0));
            que.clear();
        }
    }

    private static class Fish {
        int x;
        int y;
        int size;
        int eatCnt;
        int time;

        public Fish(int x, int y, int size, int eatCnt, int time) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.eatCnt = eatCnt;
            this.time = time;
        }
    }
}
