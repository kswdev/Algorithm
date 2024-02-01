package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13460 {

    private static Bead escape;
    private static char[][] map;
    private static int N, M;
    private static int result = -1;

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
                    map[i][j] = '.';
                } else if (map[i][j] == 'B') {
                    beads.setBlue(new Bead(i, j));
                    map[i][j] = '.';
                } else if (map[i][j] == 'O') escape = new Bead(i, j);
            }
        }

        bfs(beads);

        System.out.println(result);
    }

    private static void bfs(Beads beads) {
        queue = new LinkedList<>();
        queue.add(beads);

        while (!queue.isEmpty()) {
            Beads curBeads = queue.poll();

            if (curBeads.getRed().equals(escape) &&
               !curBeads.getBlue().equals(escape)) {

                result = curBeads.getCnt();
                break;
            }

            if (curBeads.getCnt() >= 10) continue;

            for (int i = 0; i < 4; i++) {
                Beads newBeads = straight(i, curBeads);

                if (curBeads.equals(newBeads)) continue;

                queue.add(newBeads);
            }
        }
    }

    private static Beads straight (int i, Beads beads) {
        return switch (i) {
            case 0 -> up(beads);
            case 1 -> down(beads);
            case 2 -> right(beads);
            case 3 -> left(beads);
            default -> throw new IllegalStateException("Unexpected value: " + i);
        };
    }

    private static Beads up(Beads beads) {
        Bead red  = beads.getRed();
        Bead blue = beads.getBlue();

        int redX = red.getX();
        int redY = red.getY();

        int blueX = blue.getX();
        int blueY = blue.getY();

        int cnt = beads.getCnt();

        for (int i = 0; i < N; i++) {
            if (red.getX()-i >= 0 && ((map[red.getX()-i][redY] == '.') || map[red.getX()-i][redY] == 'O'))
                redX = red.getX()-i;

            if (blue.getX()-i >= 0 && (map[blue.getX()-i][blueY] == '.'))
                blueX = blue.getX()-i;
        }

        Bead newRed = new Bead(redX, redY);
        Bead newBlue = new Bead(blueX, blueY);

        return new Beads(newRed, newBlue, cnt+1);
    }

    private static Beads down(Beads beads) {
        Bead red  = beads.getRed();
        Bead blue = beads.getBlue();

        int redX = red.getX();
        int redY = red.getY();

        int blueX = blue.getX();
        int blueY = blue.getY();

        int cnt = beads.getCnt();

        for (int i = 0; i < N; i++) {
            if (red.getX()+i < N && ((map[red.getX()+i][blueY] == '.') || map[red.getX()+i][blueY] == 'O'))
                redX = red.getX()+i;

            if (blue.getX()+i < N && (map[blue.getX()+i][blueY] == '.'))
                blueX = blue.getX()+i;
        }

        Bead newRed = new Bead(redX, redY);
        Bead newBlue = new Bead(blueX, blueY);

        return new Beads(newRed, newBlue, cnt+1);
    }

    private static Beads right(Beads beads) {
        Bead red  = beads.getRed();
        Bead blue = beads.getBlue();

        int redX = red.getX();
        int redY = red.getY();

        int blueX = blue.getX();
        int blueY = blue.getY();

        int cnt = beads.getCnt();

        for (int i = 0; i < N; i++) {
            if (red.getY()+i < M && ((map[red.getX()][red.getY()+i] == '.') || map[red.getX()][red.getY()+i] == 'O'))
                redY = red.getY()+i;

            if (blue.getY()+i < M && (map[blue.getX()][blue.getY()+i] == '.'))
                blueX = blue.getY()+i;
        }

        Bead newRed = new Bead(redX, redY);
        Bead newBlue = new Bead(blueX, blueY);

        return new Beads(newRed, newBlue, cnt+1);
    }

    private static Beads left(Beads beads) {
        Bead red  = beads.getRed();
        Bead blue = beads.getBlue();

        int redX = red.getX();
        int redY = red.getY();

        int blueX = blue.getX();
        int blueY = blue.getY();

        int cnt = beads.getCnt();

        for (int i = 0; i < N; i++) {
            if (red.getY()-i >= 0 && ((map[red.getX()][red.getY()-i] == '.') || map[red.getX()][red.getY()-i] == 'O'))
                redY = red.getY()-i;

            if (blue.getY()-i >= 0 && (map[blue.getX()][blue.getY()-i] == '.'))
                blueX = blue.getY()-i;
        }

        Bead newRed = new Bead(redX, redY);
        Bead newBlue = new Bead(blueX, blueY);

        return new Beads(newRed, newBlue, cnt+1);
    }

    private static class Beads {

        private Bead red, blue;
        private int cnt = 0;

        public Beads() { }

        public Beads(Bead red, Bead blue, int cnt) {
            this.red = red;
            this.blue = blue;
            this.cnt = cnt;
        }

        public void setRed(Bead red) { this.red = red; }

        public void setBlue(Bead blue) { this.blue = blue; }

        public Bead getRed() { return red; }

        public Bead getBlue() { return blue; }

        public int getCnt() {
            return cnt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Beads beads)) return false;
            return getRed().equals(beads.getRed()) && getBlue().equals(beads.getBlue());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getRed(), getBlue());
        }
    }

    private static class Bead {
        final int x;
        final int y;

        public Bead(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() { return x; }

        public int getY() { return y; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Bead bead)) return false;
            return getX() == bead.getX() && getY() == bead.getY();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getX(), getY());
        }

        @Override
        public String toString() {
            return "Bead{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
