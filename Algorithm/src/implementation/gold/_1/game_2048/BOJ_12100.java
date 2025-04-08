package implementation.gold._1.game_2048;

/*
 *
 * 이 게임에서 한 번의 이동은 보드 위에 있는 전체 블록을 상하좌우 네 방향 중 하나로 이동시키는 것이다.
 * 이때, 같은 값을 갖는 두 블록이 충돌하면 두 블록은 하나로 합쳐지게 된다.
 * 한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없다.
 * (실제 게임에서는 이동을 한 번 할 때마다 블록이 추가되지만, 이 문제에서 블록이 추가되는 경우는 없다)
 *
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12100 {

    public static void main(String[] args) throws IOException {

        Board board = new Board();
        board.init();
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.toString(board.map[i]));
        }
        board.merge(Board.Direction.UP, 1, 1);
        System.out.println("============");
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.toString(board.map[i]));
        }
    }

    private static class Board {

        private int[][] map;
        private int n;

        private void init() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());

            map = new int[n+1][n+1];
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 1; j <= n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        private void merge(Direction direction, int x, int y) {
            Queue<int []> q = new LinkedList<>();
            q.add(new int [] {x, y});
            int dx = direction.getDx();
            int dy = direction.getDy();

            while (!q.isEmpty()) {
                int [] point = q.poll();
                int curX = point[0];
                int curY = point[1];

                for (int k = 0; k < n; k++) {
                    int newX = curX + dx;
                    int newY = curY + dy;

                    if (newX <= 0 || newY <= 0 ||
                        newX > n || newY > n ) continue;

                    compactFrom(direction, curX, curY);

                    if (map[newX][newY] == map[curX][curY]) {
                        map[curX][curY] += map[newX][newY];
                        map[newX][newY] = 0;
                        q.add(new int [] {newX + dx, newY + dy});
                    } else {
                        q.add(new int [] {newX, newY});
                    }
                }
            }
        }

        private void compactFrom(Direction direction, int x, int y) {
            switch(direction) {
                case UP: compactToUp(direction, x, y); break;
                case DOWN: compactToDown(direction, x, y); break;
                case RIGHT: compactToRight(direction, x, y); break;
                case LEFT: compactToLeft(direction, x, y); break;
            }
        }

        private void compactToUp(Direction direction, int x, int y) {
            for (int i = 1; i <= n; i++) {

            }
        }
        private void compactToDown(Direction direction, int x, int y) {}
        private void compactToRight(Direction direction, int x, int y) {}
        private void compactToLeft(Direction direction, int x, int y) {}

        private enum Direction {
            RIGHT(0, -1), LEFT(0, 1), UP(1, 0), DOWN(-1, 0);

            Direction(int dx, int dy) {
                this.dx = dx;
                this.dy = dy;
            }

            private final int dx;
            private final int dy;

            public int getDx() { return dx; }
            public int getDy() { return dy; }
        }
    }
}
