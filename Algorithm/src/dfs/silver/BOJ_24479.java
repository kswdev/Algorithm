package dfs.silver;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_24479 {

    private static boolean[] visited;
    private static int[] vertex;
    private static int count = 0;
    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        visited = new boolean[n+1];
        vertex = new int[n+1];

        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dfs(r);

        for (int i = 1; i <= n; i++)
            bw.append(String.valueOf(vertex[i])).append("\n");

        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int start) {
        visited[start] = true;
        count++;

        vertex[start] = count;

        Collections.sort(graph.get(start));

        for (int neighbor : graph.get(start)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                dfs(neighbor);
            }
        }
    }
}