package implementation.gold._1.bead_escape;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13460 {

    private static int N, M;
    private static int holeX, holeY;
    private static char[][] map;
    private static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        Beads beads = new Beads(0);

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'R') {
                    beads.red[0] = i;
                    beads.red[1] = j;
                } else if (map[i][j] == 'B') {
                    beads.blue[0] = i;
                    beads.blue[1] = j;
                } else if (map[i][j] == 'O') {
                    holeX = i;
                    holeY = j;
                }
            }
        }

        System.out.println(game(beads));
    }

    private static int game(Beads beads) {
        Queue<Beads> queue = new LinkedList<>();
        queue.add(beads);

        while (!queue.isEmpty()) {
            int[] curRed = queue.peek().red;
            int[] curBlue = queue.peek().blue;
            int count = queue.peek().count;

            queue.poll();

            if (count >= 10) continue;

            for (int dir = 0; dir < 4; dir++) {
                int newRx = curRed[0];
                int newRy = curRed[1];
                int newBx = curBlue[0];
                int newBy = curBlue[1];
                int newCount = count + 1;
                boolean isRedHole = false;
                boolean isBlueHole = false;

                while(map[newRx + dx[dir]][newRy + dy[dir]] != '#') {
                    newRx += dx[dir];
                    newRy += dy[dir];

                    if(newRx == holeX && newRy == holeY) {
                        isRedHole = true;
                        break;
                    }
                }

                while(map[newBx + dx[dir]][newBy + dy[dir]] != '#') {
                    newBx += dx[dir];
                    newBy += dy[dir];

                    if(newBx == holeX && newBy == holeY) {
                        isBlueHole = true;
                        break;
                    }
                }

                if(isBlueHole) continue;
                if(isRedHole && !isBlueHole) return newCount;

                if (newRx == newBx && newRy == newBy) {
                    switch (dir) {
                        case 0:
                            if (curRed[0] > curBlue[0]) newRx -= dx[dir];
                            else newBx -= dx[dir];
                            break;
                        case 1:
                            if (curRed[0] > curBlue[0]) newBx -= dx[dir];
                            else newRx -= dx[dir];
                            break;
                        case 2:
                            if (curRed[1] > curBlue[1]) newBy -= dy[dir];
                            else newRy -= dy[dir];
                            break;
                        case 3:
                            if (curRed[1] > curBlue[1]) newRy -= dy[dir];
                            else newBy -= dy[dir];
                            break;
                    }
                }

                if (!visited[newRx][newRy][newBx][newBy]) {
                    visited[newRx][newRy][newBx][newBy] = true;
                    queue.add(new Beads(
                            new int[] {newRx, newRy},
                            new int[] {newBx, newBy},
                            newCount
                    ));
                }
            }
        }

        return -1;
    }

    private static class Beads {
        int[] red = new int[2];
        int[] blue = new int[2];
        int count;
        private Beads(int count) {
            this.count = count;
        }

        private Beads(int[] red, int[] blue, int count) {
            this.red = red;
            this.blue = blue;
            this.count = count;
        }
    }
}
