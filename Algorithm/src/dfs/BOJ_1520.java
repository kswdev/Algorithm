package dfs;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1520 {

    public static int X;
    public static int Y;

    public static int[][] map;
    public static int[][] dp;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        map     = new int[X][Y];
        dp      = new int[X][Y];

        for(int i = 0; i < X; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < Y; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j]  = -1;
            }
        }

        bw.write(dfs(0, 0) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int dfs(int x, int y) {

        if(x == X-1 && y == Y-1) {
            return 1;
        }

        if(dp[x][y] != -1) {
            return dp[x][y];
        }

        //가봤는데 없는 길 중복처리 방지
        dp[x][y] = 0;
        for(int k = 0; k < 4; k++) {
            int curx = dx[k] + x;
            int cury = dy[k] + y;

            if(curx < 0 || cury < 0 || curx >= X || cury >= Y) {
                continue;
            }

            if(map[x][y] > map[curx][cury]) {
                dp[x][y] += dfs(curx, cury);
            }
        }

        return dp[x][y];
    }
}
