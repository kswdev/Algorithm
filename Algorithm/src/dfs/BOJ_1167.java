package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1167 {

    private static int V;
    private static int max;
    private static int[][] map;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());

        map     = new int[V+1][V+1];
        visited = new boolean[V+1][V+1];

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int next;
            while ((next = Integer.parseInt(st.nextToken())) != -1) {
                map[parent][next] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i <= V; i++) {
            for (int j = 0; j <= V; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    visited[i][j] = true;
                    visited[j][i] = true;
                    dfs(i, j, map[i][j]);
                }
            }
        }

        System.out.println(max);
    }

    public static void dfs(int x, int y, int d) {
        int child   = y;

        for (int k = 0; k <= V; k++) {
            if (!visited[child][k] && map[child][k] != 0) {
                visited[child][k] = true;
                visited[k][child] = true;
                max = Math.max(max, map[child][k] + d);
                dfs(child, k, map[child][k] + d);
                visited[child][k] = false;
                visited[k][child] = false;
            }
        }
    }
}
