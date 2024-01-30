package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13460 {

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    private static Bead escape;
    private static char[][] map;
    private static int N, M;

    private static Queue<Beads> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        Beads beads = new Beads();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String row = st.nextToken();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j);
                if (map[i][j] == 'R') {
                    beads.setRed(new Bead(i, j));
                } else if (map[i][j] == 'B') {
                    beads.setBlue(new Bead(i, j));
                } else if (map[i][j] == 'O') escape = new Bead(i, j);
            }

            bfs(beads);
        }
    }

    private static void bfs(Beads beads) {
        queue = new LinkedList<>();
        queue.add(beads);

        while (!queue.isEmpty()) {
            Beads curBeads = queue.poll();
            Bead red  = curBeads.getRed();
            Bead blue = curBeads.getBlue();

            for (int i = 0; i < 4; i++) {

            }
        }
    }

    private void straight (int i, Beads beads) {

    }

    private static class Beads {

        private Bead red, blue;
        private int cnt = 0;

        public void setRed(Bead red) { this.red = red; }

        public void setBlue(Bead blue) { this.blue = blue; }

        public Bead getRed() { return red; }

        public Bead getBlue() { return blue; }

        public void addCnt() { this.cnt++; }
    }

    private static class Bead {
        int x;
        int y;
        public Bead(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() { return x; }

        public void setX(int x) { this.x = x; }

        public int getY() { return y; }

        public void setY(int y) { this.y = y; }
    }
}
