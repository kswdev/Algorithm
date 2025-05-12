package implementation.gold._4.cleaner;

/*
 * 집 크기 : R x C
 * 공기청정기 :
 *  - 위치 : 1열
 *  - 크기 : 2칸
 *
 * 1초 동안 일어나는 현상
 *  1. 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
 *    - (r, c)에 있는 미세먼지는 인접한 네 방향으로 확산된다.
 *    - 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로 확산은 일어나지 않는다.
 *    - 확산되는 양은 A(r, c)/5이고 소수점은 버린다. 즉 LA(r, c)/5J이다.
 *    - (r, c)에 남은 미세먼지의 양은 A(r, c) - LA(r, c) / 5Jx(확산된 방향의 개수) 이다.
 *
 *  2. 공기청정기가 작동한다.
 *    - 공기청정기에서는 바람이 나온다.
 *    - 위쪽 공기청정기의 바람은 반시계방향으로 순환하고, 아래쪽 공기청정기의 방향은 시계방향으로 순환한다.
 *    - 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
 *    - 공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.
 *
 *  방에 정보가 주어졌을 때, T초가 지난 후 구사과의 방에 남아있는 미세먼지의 양을 구해보자
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;


public class BOJ_17144 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] map = new int[r][c];

        Room room = new Room();
        Cleaner cleaner = new Cleaner();

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) cleaner.addPoint(i, j);
                else if (map[i][j] != 0) room.getDusts().add(new Dust(i, j, map[i][j]));
            }
        }

        room.setCleaner(cleaner);
        room.setMap(map);

        for (int i = 0; i < t; i++) {
            room.diffuseAll();
            room.clean();
            room.resetDusts();
        }

        System.out.println(room.sumDusts());

    }

    private static class Room {

        private Cleaner cleaner;
        private List<Dust> dusts = new ArrayList<>();
        private int[][] map;

        private Room() {}

        private void diffuseAll() {
            List<Dust> newDusts = new ArrayList<>();
            int[][] newMap = new int[map.length][map[0].length];

            for (Dust dust : dusts) {
                List<Dust> diffused = dust.diffuse(this.map);
                this.merge(newDusts, diffused);
            }

            this.dusts = newDusts;
            this.resetMap(newMap, dusts);
        }

        private void clean() {
            this.cleaner.cleanTop(this.cleaner.getTop(), this.map);
            this.cleaner.cleanBottom(this.cleaner.getBottom(), this.map);
            this.resetDusts();
        }
        
        private void merge(List<Dust> dusts, List<Dust> diffused) {
            for (Dust dust : diffused) {
                if (dusts.contains(dust)) {
                    Dust duplicated = dusts.get(dusts.indexOf(dust));
                    duplicated.sumValue(dust);
                } else
                    dusts.add(dust);
            }
        }

        private void resetDusts() {
            dusts.clear();
            for (int i = 0; i < this.map.length; i++) {
                for (int j = 0; j < this.map[i].length; j++) {
                    if (map[i][j] != -1 || map[i][j] != 0) dusts.add(new Dust(i, j, map[i][j]));
                }
            }
        }

        private void resetMap(int[][] map, List<Dust> dusts) {
            for (Dust dust : dusts)
                map[dust.x][dust.y] = dust.value;

            int[] topCleaner = this.cleaner.getTop();
            int[] bottomCleaner = this.cleaner.getBottom();

            map[topCleaner[0]][topCleaner[1]] = -1;
            map[bottomCleaner[0]][bottomCleaner[1]] = -1;
            this.map = map;
        }

        private int sumDusts() {
            int sum = 0;

            for (int[] ints : map)
                for (int anInt : ints)
                    sum += anInt;

            return sum + 2;
        }

        private List<Dust> getDusts() {
            return this.dusts;
        }

        private void setCleaner(Cleaner cleaner) {
            this.cleaner = cleaner;
        }

        private void setMap(int[][] map) {
            this.map = map;
        }
    }

    private static class Cleaner {
        private final int[] cdx = {-1, 0, 1, 0};
        private final int[] cdy = {0, 1, 0, -1};
        private final int[] rcdx = {1, 0, -1, 0};
        private final int[] rcdy = {0, 1, 0, -1};

        private int[] x = new int[2];
        private int[] y = new int[2];
        private int index = 0;
        private Cleaner() {}

        private void addPoint(int x, int y) {
            this.x[index] = x;
            this.y[index] = y;
            index++;
        }

        private void cleanTop(int[] start, int[][] map) {
            int cx = start[0];
            int cy = start[1];

            for (int i = 0; i < 4; i++) {
                while(true) {
                    int tempX = cx;
                    int tempY = cy;
                    cx += cdx[i];
                    cy += cdy[i];

                    if (cx < 0 || cy >= map[0].length || cx > start[0] || cy < 0 || (cx == start[0] && cy == start[1])) {
                        cx -= cdx[i];
                        cy -= cdy[i];
                        break;
                    }

                    if (map[tempX][tempY] != -1) {
                        map[tempX][tempY] = map[cx][cy];
                    }
                    map[cx][cy] = 0;
                }
            }
        }

        private void cleanBottom(int[] start, int[][] map) {
            int cx = start[0];
            int cy = start[1];

            for (int i = 0; i < 4; i++) {
                while(true) {
                    int tempX = cx;
                    int tempY = cy;
                    cx += rcdx[i];
                    cy += rcdy[i];

                    if (start[0] > cx || cy >= map[0].length || cx >= map.length || cy < 0 || (cx == start[0] && cy == start[1])) {
                        cx -= rcdx[i];
                        cy -= rcdy[i];
                        break;
                    }

                    if (map[tempX][tempY] != -1) {
                        map[tempX][tempY] = map[cx][cy];
                    }
                    map[cx][cy] = 0;
                }
            }
        }

        private int[] getTop() {
            return new int[] {x[0], y[0]};
        }

        private int[] getBottom() {
            return new int[] {x[1], y[1]};
        }
    }

    private static class Dust {
        private final int[] dx = {-1, 1, 0, 0};
        private final int[] dy = {0, 0, -1, 1};
        private final int x;
        private final int y;
        private int value;

        private Dust(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        private List<Dust> diffuse(int[][] map) {
            List<Dust> dusts = new ArrayList<>();

            if (value < 5)  {
                dusts.add(this);
                return dusts;
            }

            int diffusedValue = this.value / 5;
            
            for (int i = 0; i < 4; i++) {
                int nX = this.x + dx[i];
                int nY = this.y + dy[i];
                
                if (nX < 0 || nY < 0 || nX >= map.length || nY >= map[0].length || map[nX][nY] == -1) continue;
                
                Dust newDust = new Dust(nX, nY, diffusedValue);
                
                dusts.add(newDust);
                this.value -= diffusedValue;
            }
            
            dusts.add(this);
            
            return dusts;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Dust dust = (Dust) o;
            return x == dust.x && y == dust.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public void sumValue(Dust dust) {
            this.value += dust.value;
        }
    }
}
