package divide_and_rule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630 {

    private static int[][] map;

    private static int blue = 0;
    private static int white = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];

        for (int x = 0; x < N; x++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for (int y = 0; y < N; y++)
                map[x][y] = Integer.parseInt(st.nextToken());

        }


        paper(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }

    private static void paper(int x, int y, int size) {

        if (check(x, y, size)) {
            if (map[x][y] == 1) blue++;
            else white++;

            return;
        }

        int newSize = size/2;

        paper(x, y, newSize);
        paper(x + newSize, y, newSize);
        paper(x, y + newSize, newSize);
        paper(x + newSize, y + newSize, newSize);
    }

    private static boolean check(int x, int y, int size) {

        int flag = map[x][y];

        for (int i = x; i < x + size; i++)
            for (int j = y; j < y + size; j++)
                if (map[i][j] != flag) return false;

        return true;
    }
}
