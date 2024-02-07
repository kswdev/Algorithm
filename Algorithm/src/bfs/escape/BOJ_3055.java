package bfs.escape;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {

    private static int R, C;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int[][] map;
    private static int[][] water;
    private static Location start;
    private static Location seed;
    private static Location cave;
    private static Queue<Location> queue;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map   = new int[R][C];
        water = new int[R][C];

        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = row.charAt(j);

                if (map[i][j] == 'S') start = new Location(i, j);
                else if (map[i][j] == 'D') cave = new Location(i, j);
                else if (map[i][j] == '*') queue.add(new Location(i, j));
            }
        }

        bfs(start);
    }

    private static void bfs(Location location) {
        Queue<Location> locQueue = new LinkedList<>();
        locQueue.add(location);

        while (!locQueue.isEmpty()) {
            Location cur = locQueue.poll();

            int curx = cur.x;
            int cury = cur.y;

            flood();

            for (int i = 0; i < 4; i++) {

            }
        }
    }

    private static void flood() {
        Location waterFlood = queue.poll();

        int wx = waterFlood.x;
        int wy = waterFlood.y;

        water[wx][wy] = 1;

        for (int i = 0; i < 4; i++) {
            wx += dx[i];
            wy += dy[i];

            if (wx >= 0 && wy >= 0 && wx < R && wy < C) {
                if (map[wx][wy] == 'D' || map[wx][wy] == 'X') continue;

                queue.add(new Location(wx, wy));
            }
        }
    }

    private static class Location {
        private int x;
        private int y;
        private int count = 0;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Location(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Location location)) return false;
            return x == location.x && y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
