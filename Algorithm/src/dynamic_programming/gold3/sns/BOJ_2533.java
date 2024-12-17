package dynamic_programming.gold3.sns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2533 {

    private static int N;
    private static int[][] dp;
    private static List<List<Integer>> friends = new ArrayList<>();
    private static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][2];
        visited = new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            friends.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child  = Integer.parseInt(st.nextToken());

            friends.get(parent).add(child);
            friends.get(child).add(parent);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int parent) {
        dp[parent][0] = 0;
        dp[parent][1] = 1;
        visited[parent] = true;

        for (int child : friends.get(parent)) {
            if (!visited[child]) {
                dfs(child);
                dp[parent][0] += dp[child][1];
                dp[parent][1] += Math.min(dp[child][1], dp[child][0]);
            }
        }
    }
}