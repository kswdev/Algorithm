package dynamic_programming.gold1;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2098 {

    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];

        for (int n = 1; n <= N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                map[n][j] = Integer.parseInt(st.nextToken());
            }
        }



        bw.flush();
        bw.close();
        br.close();
    }
}
