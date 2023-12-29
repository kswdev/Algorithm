package data_structure.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOj_3190 {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static int N, K, L;
    private static int[][] map;
    private static final Queue<int[]> snake = new LinkedList<>();
    private static final Map<Integer, String> dir = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map   = new int[N+1][N+1];

        StringTokenizer st;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = 1;
        }

        L = Integer.parseInt(br.readLine());

        for (int j = 0; j < L; j++) {
            st = new StringTokenizer(br.readLine());

            int key    = Integer.parseInt(st.nextToken());
            String val = st.nextToken();
            dir.put(key, val);
        }


        snake.add(new int[] {0, 0});
        System.out.println(gamestart(0, 0));
    }

    private static int gamestart(int x, int y) {
        int curx = x;
        int cury = y;
        int dirIndex = 0;
        int time = 0;

        while (true) {
            time++;
            curx += dx[dirIndex];
            cury += dy[dirIndex];

            if (gameover(curx, cury)) {
                break;
            }

            if (!eatApple(curx, cury)) snake.poll();

            dirIndex += alterDirection(time);
            if (dirIndex < 0) dirIndex = 3;
            if (dirIndex >= 4) dirIndex = 0;
        }

        return time;
    }

    private static int alterDirection(int time) {
        if (dir.containsKey(time)) {
            String direction = dir.get(time);
            if (direction.equals("D")) {
                return 1;
            } else {
                return - 1;
            }
        }

        return 0;
    }

    private static boolean eatApple(int x, int y) {
        snake.add(new int[] {x, y});

        if (map[x][y] == 1) {
            map[x][y] = 0;
            return true;
        }

        return false;
    }

    private static boolean gameover(int x, int y) {

        if (x < 0 || y < 0 || x >= N || y >= N)
            return true;

        for (int[] arr : snake)
            if (arr[0] == x && arr[1] == y) return true;

        return false;
    }
}
