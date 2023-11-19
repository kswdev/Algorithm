package bfs;

import java.io.*;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236 {

    public static int N;
    public static int[][] map;
    public static int[][] atable;

    private static Queue<int[]> q;

    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BabyShark babyShark = new BabyShark();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map    = new int[N+1][N+1];
        atable = new int[N+1][N+1];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 9) {
                    babyShark.x = i;
                    babyShark.y = j;
                }
            }
        }

        System.out.println(targeting(babyShark));

        System.out.println(babyShark.targetX);
        System.out.println(babyShark.targetY);
        br.close();
    }

    public static boolean targeting(BabyShark shark) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] < shark.height && map[i][j] != 0) {
                    shark.think(i, j);
                    return true;
                }
            }
        }

        return false;
    }


    public static class BabyShark {
        public int height = 2;
        public int cnt = 0;

        public int x;
        public int y;
        public int targetX = 21;
        public int targetY = 21;
        public int targetD = 1000;


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
            if (targetX < i) {
                targetX = i;
                targetY = j;
            } else if ( targetX == i && (targetY > j)) {
                targetY = j;
            }
        }
    }
}
