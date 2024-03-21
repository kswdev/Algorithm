package dfs.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13023 {

    private static int N, M;
    private static List<List<Integer>> relation;
    private static boolean[] visited;
    private static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        relation = new ArrayList<>();
        visited  = new boolean[N];

        for (int i = 0; i < N; i++)
            relation.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            relation.get(a).add(b);
            relation.get(b).add(a);
        }

        for (int i = 0; i < N; i++) {
            if (result != 1) {
                visited[i] = true;
                dfs(i, 0);
                visited[i] = false;
            }
        }

        System.out.println(result);
    }

    private static void dfs(int start, int depth) {

        if (depth == 4) {
            result = 1;
            return;
        }

        List<Integer> friendList = relation.get(start);

        for (int friend : friendList) {
            visited[start] = true;
            if (!visited[friend]) dfs(friend, depth+1);
            visited[start] = false;
        }
    }
}
