package dynamic_programming.gold4.biggest_square;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1915 {

    private static int N, M;
    private static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];

        int result = 0;

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = Character.getNumericValue(str.charAt(j-1));

                if (map[i][j] == 1) {
                    map[i][j] = Math.min(map[i-1][j-1], Math.min(map[i][j-1], map[i-1][j])) + 1;
                    result = Math.max(map[i][j]*map[i][j], result);
                }
            }
        }

        System.out.println(result);
    }
}