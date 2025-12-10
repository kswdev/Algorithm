package dijkstra.gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4485 {

    private static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cnt = 0;
        while (true) {
            cnt++;
            int size = Integer.parseInt(br.readLine());
            if (size == 0) break;

            int[][] map = new int[size][size];
            dist = new int[size][size];

            for (int i = 0; i < size; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < size; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            int result = searchSmallestPrice(map, size);
            System.out.println("Problem "+ cnt +": " + result);
        }
    }

    private static int searchSmallestPrice(int[][] map, int size) {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        queue.add(new int[] {0, 0, map[0][0]});

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        dist[0][0] = map[0][0];

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int cost = curr[2];

            if (x == size-1 && y == size-1) return dist[x][y];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= size || ny < 0 || ny >= size) continue;

                int nc = cost + map[nx][ny];

                if (nc < dist[nx][ny]) dist[nx][ny] = nc;
                else continue;

                queue.add(new int[] {nx, ny, nc});
            }
        }

        return dist[size-1][size-1];
    }
}
