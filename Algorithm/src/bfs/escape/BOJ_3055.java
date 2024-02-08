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
    private static boolean[][] visited;
    private static int[][] water;
    private static Location start;
    private static Location cave;
    private static Queue<Location> queue = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map   = new int[R][C];
        water = new int[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = row.charAt(j);

                if (map[i][j] == 'S') start = new Location(i, j);
                else if (map[i][j] == 'D') cave = new Location(i, j);
                else if (map[i][j] == '*') queue.add(new Location(i, j));
            }
        }

        Location result = bfs(start);
        if (result.count == 0) System.out.println("KAKTUS");
        else System.out.println(result.count);
    }

    private static Location bfs(Location location) {
        Queue<Location> locQueue = new LinkedList<>();
        locQueue.add(location);

        while (!locQueue.isEmpty()) {
            flood(queue.size());

            for (int k = 0; k < locQueue.size(); k++) {

                Location cur = locQueue.poll();

                int curx = cur.x;
                int cury = cur.y;
                int curCount = cur.count;

                if (cur.equals(cave)) return cur;

                for (int i = 0; i < 4; i++) {
                    int nx = curx + dx[i];
                    int ny = cury + dy[i];

                    if (nx >= 0 && ny >= 0 && nx < R && ny < C && !visited[nx][ny]) {
                        if (map[nx][ny] == 'X' || water[nx][ny] == 1) continue;

                        visited[nx][ny] = true;
                        locQueue.add(new Location(nx, ny, curCount+1));
                    }
                }
            }
        }

        return new Location(0, 0, 0);
    }

    private static void flood(int size) {

        for (int k = 0; k < size; k++) {

            Location waterFlood = queue.poll();

            assert waterFlood != null;

            int wx = waterFlood.x;
            int wy = waterFlood.y;

            water[wx][wy] = 1;

            for (int i = 0; i < 4; i++) {
                int nX = wx + dx[i];
                int nY = wy + dy[i];

                if (nX >= 0 && nY >= 0 && nX < R && nY < C) {
                    if (map[nX][nY] == 'D' || map[nX][nY] == 'X' || water[nX][nY] == 1) continue;
                    water[nX][nY] = 1;
                    queue.add(new Location(nX, nY));
                }
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
            if (!(o instanceof Location)) return false;
            Location location = (Location) o;
            return x == location.x && y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
