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
                            cctvList.add(new CctvA(map[i][j], i, j));
                            break;
                        case 2:
                            cctvList.add(new CctvB(map[i][j], i, j));
                            break;
                        case 3:
                            cctvList.add(new CctvC(map[i][j], i, j));
                            break;
                        case 4:
                            cctvList.add(new CctvD(map[i][j], i, j));
                            break;
                        case 5:
                            cctvList.add(new CctvE(map[i][j], i, j));
                            break;
                    }
                }
            }
        }

        Room room = new Room(map, cctvList);
    }

    private static class Room {
        private final int[][] map;
        private final List<Cctv> cctvList;

        private Room(int[][] map, List<Cctv> cctvList) {
            this.map = map;
            this.cctvList = cctvList;
        }
    }

    private interface Cctv {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        void surveillance();
    }

    private static class CctvA implements Cctv {
        private final int id;
        private final int x;
        private final int y;
        private final int[][] dir;


        private CctvA(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dir = new int[][] {{0, 1, 2, 3}};
        }

        public void surveillance() {

        }
    }

    private static class CctvB implements Cctv {
        private final int id;
        private final int x;
        private final int y;
        private final int[][] dir;

        private CctvB(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dir = new int[][] {{0, 1},
                                   {2, 3}};
        }

        public void surveillance() {

        }
    }

    private static class CctvC implements Cctv {
        private final int id;
        private final int x;
        private final int y;
        private final int[] dir;

        private CctvC(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dir = new int[] {0, 1, 2, 3};
        }

        public void surveillance() {

        }
    }

    private static class CctvD implements Cctv {
        private final int id;
        private final int x;
        private final int y;
        private final int[] dir;

        private CctvD(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dir = new int[] {0, 1, 2, 3};
        }

        public void surveillance() {

        }
    }

    private static class CctvE implements Cctv {
        private final int id;
        private final int x;
        private final int y;
        private final int[] dir;

        private CctvE(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dir = new int[] {0};
        }

        public void surveillance() {

        }
    }
}
