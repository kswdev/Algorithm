package implementation.gold._3.surveillance;


/*
 *
 * 사무실 크기: N x M
 * 사각지대: CCTV 가 감시할 수 없는 영역
 * CCTV 개수: K
 * CCTV 종류: 5가지
 *  1. 한 쪽 방향만 감시
 *  2. 두 방향 감시 (서로 반대 방향만)
 *  3. 두 방향 감시 (서로 직각)
 *  4. 세 방향 감시
 *  5. 네 방향 감시
 *
 * CCTV 는 감시할 수 있는 방향에 있는 칸 정체를 감시 가능
 *  - 벽은 통과 불가
 *
 *
 * CCTV 는 회전 가능
 *  - 90도 방향
 *  - 가로, 세로 방향만 가능(대각x)
 *
 * 지도
 *  1. 빈 칸: 0
 *  2. 벽: 6
 *  3. CCTV: 1 ~ 5
 *
 * 문제: 사각 지대의 최소 크기를 구하라
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15683 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int [][] map = new int[n][m];
        List<Cctv> cctvList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= map[i][j] && map[i][j] <= 5) {
                    switch (map[i][j]) {
                        case 1:
                            cctvList.add(new CctvA(i, j));
                            break;
                        case 2:
                            cctvList.add(new CctvB(i, j));
                            break;
                        case 3:
                            cctvList.add(new CctvC(i, j));
                            break;
                        case 4:
                            cctvList.add(new CctvD(i, j));
                            break;
                        case 5:
                            cctvList.add(new CctvE(i, j));
                            break;
                    }
                }
            }
        }

        Room room = new Room(map, cctvList);
        System.out.println(room.minArea());
    }

    private static class Room {
        private final int[][] map;
        private final List<Cctv> cctvList;
        private int min = Integer.MAX_VALUE;

        private Room(int[][] map, List<Cctv> cctvList) {
            this.map = map;
            this.cctvList = cctvList;
        }

        private int minArea() {
            int[][] newMap = deepCopy(this.map);
            dfs(0, newMap);
            return this.min;
        }

        private void dfs(int depth, int[][] map) {
            if (depth >= cctvList.size()) {
                 this.min = Math.min(this.min, countArea(map));
                 return;
            }

            Cctv currentCctv = cctvList.get(depth);
            int actionCount = currentCctv.getActionCount();

            for (int i = 0; i < actionCount; i++) {
                int[][] surveillanced = currentCctv.surveillance(i, deepCopy(map));
                dfs(depth + 1, surveillanced);
            }
        }

        private int[][] deepCopy(int[][] map) {
            int[][] tempMap = new int[map.length][map[0].length];

            for (int i = 0; i < tempMap.length; i++) {
                System.arraycopy(map[i], 0, tempMap[i], 0, tempMap[i].length);
            }

            return tempMap;
        }

        private int countArea(int[][] map) {
            int count = 0;

            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[x].length; y++) {
                    if (map[x][y] == 0) count++;
                }
            }

            return count;
        }
    }

    private interface Cctv {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int[][] surveillance(int idx, int[][] map);
        int getActionCount();
        void markEmptyRoom(int dir, int[][] map);
    }

    private static class CctvA implements Cctv {
        private final int x;
        private final int y;
        private final int[][] dirs;


        private CctvA(int x, int y) {
            this.x = x;
            this.y = y;
            this.dirs = new int[][] {{0}, {1}, {2}, {3}};
        }

        public int[][] surveillance(int idx, int[][] map) {

            int[] dir = dirs[idx];

            for (int d : dir) {
                markEmptyRoom(d, map);
            }

            return map;
        }

        @Override
        public int getActionCount() {
            return dirs.length;
        }

        @Override
        public void markEmptyRoom(int dir, int[][] map) {
            int cx = this.x;
            int cy = this.y;
            while(true) {
                cx += dx[dir];
                cy += dy[dir];

                if (cx >= map.length || cx < 0 || cy >= map[0].length || cy < 0)
                    break;
                if (map[cx][cy] == 6)
                    break;

                else if (map[cx][cy] == 0)
                    map[cx][cy] = -1;
            }
        }
    }

    private static class CctvB implements Cctv {
        private final int x;
        private final int y;
        private final int[][] dirs;

        private CctvB(int x, int y) {
            this.x = x;
            this.y = y;
            this.dirs = new int[][] {{0, 1},
                                   {2, 3}};
        }

        public int[][] surveillance(int idx, int[][] map) {

            int[] dir = dirs[idx];

            for (int d : dir) {
                markEmptyRoom(d, map);
            }

            return map;
        }

        @Override
        public int getActionCount() {
            return dirs.length;
        }

        @Override
        public void markEmptyRoom(int dir, int[][] map) {
            int cx = this.x;
            int cy = this.y;
            while(true) {
                cx += dx[dir];
                cy += dy[dir];

                if (cx >= map.length || cx < 0 || cy >= map[0].length || cy < 0)
                    break;
                if (map[cx][cy] == 6)
                    break;

                else if (map[cx][cy] == 0)
                    map[cx][cy] = -1;
            }
        }
    }

    private static class CctvC implements Cctv {
        private final int x;
        private final int y;
        private final int[][] dirs;

        private CctvC(int x, int y) {
            this.x = x;
            this.y = y;
            this.dirs = new int[][] {{0, 3}, {3, 1}, {1, 2}, {2, 0}};
        }

        public int[][] surveillance(int idx, int[][] map) {

            int[] dir = dirs[idx];

            for (int d : dir) {
                markEmptyRoom(d, map);
            }

            return map;
        }

        @Override
        public int getActionCount() {
            return dirs.length;
        }

        @Override
        public void markEmptyRoom(int dir, int[][] map) {
            int cx = this.x;
            int cy = this.y;
            while(true) {
                cx += dx[dir];
                cy += dy[dir];

                if (cx >= map.length || cx < 0 || cy >= map[0].length || cy < 0)
                    break;
                if (map[cx][cy] == 6)
                    break;

                else if (map[cx][cy] == 0)
                    map[cx][cy] = -1;
            }
        }
    }

    private static class CctvD implements Cctv {
        private final int x;
        private final int y;
        private final int[][] dirs;

        private CctvD(int x, int y) {
            this.x = x;
            this.y = y;
            this.dirs = new int[][] {{0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {0, 2, 3}};
        }

        public int[][] surveillance(int idx, int[][] map) {

            int[] dir = dirs[idx];

            for (int d : dir) {
                markEmptyRoom(d, map);
            }

            return map;
        }

        @Override
        public int getActionCount() {
            return dirs.length;
        }

        @Override
        public void markEmptyRoom(int dir, int[][] map) {
            int cx = this.x;
            int cy = this.y;
            while(true) {
                cx += dx[dir];
                cy += dy[dir];

                if (cx >= map.length || cx < 0 || cy >= map[0].length || cy < 0)
                    break;
                if (map[cx][cy] == 6)
                    break;

                else if (map[cx][cy] == 0)
                    map[cx][cy] = -1;
            }
        }
    }

    private static class CctvE implements Cctv {
        private final int x;
        private final int y;
        private final int[][] dirs;

        private CctvE(int x, int y) {
            this.x = x;
            this.y = y;
            this.dirs = new int[][] {{0, 1, 2, 3}};
        }

        public int[][] surveillance(int idx, int[][] map) {

            int[] dir = dirs[idx];

            for (int d : dir) {
                markEmptyRoom(d, map);
            }

            return map;
        }

        @Override
        public int getActionCount() {
            return dirs.length;
        }

        @Override
        public void markEmptyRoom(int dir, int[][] map) {
            int cx = this.x;
            int cy = this.y;
            while(true) {
                cx += dx[dir];
                cy += dy[dir];

                if (cx >= map.length || cx < 0 || cy >= map[0].length || cy < 0)
                    break;
                if (map[cx][cy] == 6)
                    break;

                else if (map[cx][cy] == 0)
                    map[cx][cy] = -1;
            }
        }
    }
}
