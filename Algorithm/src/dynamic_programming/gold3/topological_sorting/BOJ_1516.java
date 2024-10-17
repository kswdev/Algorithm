package dynamic_programming.gold3.topological_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1516 {

    private static int N;
    private static List<List<Integer>> building = new ArrayList<>();
    private static int[] time;
    private static int[] requiredBuildingNumber;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        time = new int[N+1];
        requiredBuildingNumber = new int[N+1];

        for (int i = 0; i <= N; i++)
            building.add(new ArrayList<>());

        for (int top = 1; top <= N; top++) {
            st = new StringTokenizer(br.readLine());

            time[top] = Integer.parseInt(st.nextToken());

            while (st.hasMoreElements()) {
                int subBuilding = Integer.parseInt(st.nextToken());
                if (subBuilding == -1) continue;

                building.get(subBuilding).add(top);

                requiredBuildingNumber[top]++;
            }
        }

        solve();

        for (int i = 1; i <= N; i++) {
            System.out.println(dp[i]);
        }
    }

    private static void solve() {

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (requiredBuildingNumber[i] == 0) {
                dp[i] = time[i];
                queue.add(new int[]{i, time[i]});
            }
        }

        while (!queue.isEmpty()) {
            int[] sub = queue.poll();

            int subBuilding = sub[0];
            int subBuildingTime = sub[1];

            for (int top : building.get(subBuilding)) {

                dp[top] = Math.max(dp[top], subBuildingTime + time[top]);

                if (--requiredBuildingNumber[top] == 0)
                    queue.add(new int[] {top, dp[top]});
            }
        }
    }
}
