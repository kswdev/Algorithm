package data_structure.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOj_3190 {

    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    private static int N, K, L;
    private static int[][] map;
    private static int[][] snake;
    private static Map<Integer, String> dir = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        snake = new int[N+1][N+1];
        map   = new int[N+1][N+1];

        StringTokenizer st;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
        }

        L = Integer.parseInt(br.readLine());

        for (int j = 0; j < L; j++) {
            st = new StringTokenizer(br.readLine());

            int key    = Integer.parseInt(st.nextToken());
            String val = st.nextToken();
            dir.put(key, val);
        }


        snake[0][0] = 1;

        gamestart(0, 0);
    }

    private static void gamestart(int x, int y) {
        int curx = x;
        int cury = y;
        int dirIndex = 0;
        int time = 1;

        Iterator<Integer> key = dir.keySet().iterator();

        while (key.hasNext()) {
            int keyTime = key.next();
            String direction = dir.get(keyTime);

            for (int i = 0; i < keyTime; i++) {
                curx += dx[dirIndex];
                cury += dy[dirIndex];


            }
        }
    }

    private static boolean gameover(int time) {

        return false;
    }
}
