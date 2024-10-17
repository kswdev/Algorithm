package dynamic_programming.gold5.downward;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2096 {

    private static int N;
    private static int[] dx = {-1, 0, 1};
    private static int[][] map;
    private static int[][] max;
    private static int[][] min;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][3];
        max = new int[N][3];
        min = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max[i][j] = maxValue(i, j);
                min[i][j] = minValue(i, j);
            }
        }

        int maxResult = Math.max(max[N-1][0], Math.max(max[N-1][1], max[N-1][2]));
        int minResult = Math.min(min[N-1][0], Math.min(min[N-1][1], min[N-1][2]));

        System.out.println(maxResult + " " + minResult);
    }

    private static int maxValue(int i, int j) {
        int current = map[i][j];
        if (i == 0) return current;
        else {
            int maxValue = 0;
            for (int x : dx) {
                int nx = j + x;
                if (nx < 0 || nx >= 3) continue;
                maxValue = Math.max(maxValue, max[i-1][nx]);
            }
            current += maxValue;
            return current;
        }
    }

    private static int minValue(int i, int j) {
        int current = map[i][j];
        if (i == 0) return current;
        else {
            int minValue = 900000;
            for (int x : dx) {
                int nx = j + x;
                if (nx < 0 || nx >= 3) continue;
                minValue = Math.min(minValue, min[i-1][nx]);
            }
            current += minValue;
            return current;
        }
    }
}
