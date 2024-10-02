package dynamic_programming.gold3;

import java.io.*;
import java.util.*;

public class BOJ_1005 {

    private static int[] time;
    private static int[] link;
    private static int[] dp;
    private static boolean[][] map;
    private static List<List<Integer>> orders = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            dp   = new int[N+1];
            time = new int[N+1];
            link = new int[N+1];
            map  = new boolean[N+1][N+1];
            orders.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine());

            for (int n = 1; n <= N; n++) {
                time[n] = Integer.parseInt(st.nextToken());
                dp[n] = time[n];
            }

            for (int k = 1; k <= K; k++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end   = Integer.parseInt(st.nextToken());

                map[start][end] = true;
                link[end]++;
            }

            int W = Integer.parseInt(br.readLine());
            int result = findShortestRoute(W, N, K);
            sb.append(result).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static int findShortestRoute(int W, int N, int K) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++)
            if (link[i] == 0)
                queue.add(i);

        while (!queue.isEmpty()) {
            int building = queue.poll();

            for (int n = 1; n <= N; n++) {
                if (map[building][n]) {
                    dp[n] = Math.max(dp[n], dp[building] + time[n]);

                    link[n]--;

                    if (link[n] == 0)
                        queue.add(n);
                }
            }
        }

        return dp[W];
    }
}
