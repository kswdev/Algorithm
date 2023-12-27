package data_structure.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOj_3190 {

    private static int N, K, L;
    private static int[][] map;
    private static int[][] dir;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        dir = new int[N+1][N+1];

        StringTokenizer st;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
        }

        L = Integer.parseInt(br.readLine());

        for (int j = 0; j < L; j++) {

        }
    }
}
