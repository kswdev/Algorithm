package bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236 {

    public static int N;
    public static int TIME;
    public static int[][] map;
    public static boolean[][] visited;

    private static Queue<int[]> q;

    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BabyShark babyShark = new BabyShark();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map    = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 9) {
                    babyShark.x = i;
                    babyShark.y = j;
                    map[i][j] = 0;
                }
            }
        }

        br.close();

        //targeting(babyShark);

        while (targeting(babyShark)) {
            TIME += bfs(babyShark);
        }

        System.out.println(TIME);
    }

    public static int bfs(BabyShark babyShark) {
        q = new LinkedList<>();
        q.add(new int[] {babyShark.x, babyShark.y, 0});

        TIME = 0;
        while (!q.isEmpty()) {
            int nx = q.peek()[0];
            int ny = q.peek()[1];
            int cnt = q.peek()[2];

            q.poll();

            for(int i = 0; i < 4; i++) {

                int curx = nx + dx[i];
                int cury = ny + dy[i];
                int curc = cnt + 1;

                if(curx < 0 || cury < 0 || curx > N || cury > N) continue;

                if(map[curx][cury] > babyShark.height) continue;

                if(curx == babyShark.targetX && cury == babyShark.targetY) {
                    babyShark.eat();
                    map[curx][cury] = 0;

                    babyShark.x = curx;
                    babyShark.y = cury;
                    babyShark.targetD = 1000;

                    visited[curx][cury] = true;
                    return curc;
                }

                if(map[curx][cury] == 0 || map[curx][cury] == babyShark.height) {
                    q.add(new int[] {curx, cury, curc});
                }
            }
        }
        return 0;
    }

    public static boolean targeting(BabyShark shark) {
        boolean result = false;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j] && map[i][j] < shark.height && map[i][j] != 0) {
                    shark.think(i, j);
                    result = true;
                }
            }
        }

        return result;
    }


    public static class BabyShark {
        public int height = 2;
        public int cnt = 0;

        public int x;
        public int y;
        public int targetX = 21;
        public int targetY = 21;
        public int targetD = 1000;

        public void eat() {
            cnt++;
            if(height == cnt) {
                cnt = 0;
                height++;
            }
        }


        public void think(int i, int j) {
            int distant = Math.abs(i-x) + Math.abs(j-y);
            if(distant < this.targetD) {
                targetX = i;
                targetY = j;
                targetD = distant;
            } else if (distant == this.targetD) {
                process(i, j);
            }
        }

        public void process(int i, int j) {
            if (targetX > i) {
                targetX = i;
                targetY = j;
            } else if ( targetY > j) {
                targetY = j;
            }
        }
    }
}
