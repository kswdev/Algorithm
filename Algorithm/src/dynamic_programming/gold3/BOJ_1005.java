package dynamic_programming.gold3;

import java.io.*;
import java.util.*;

public class BOJ_1005 {

    private static int T, N, K, W;
    private static int[] buildingTime;
    private static int[] dp;
    private static List<ArrayList<Integer>> orderList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            String[] splitString = br.readLine().split(" ");

            N = Integer.parseInt(splitString[0]);
            K = Integer.parseInt(splitString[1]);

            orderList    = new ArrayList<>();
            dp           = new int[N+1];
            buildingTime = new int[N+1];

            splitString = br.readLine().split(" ");

            for (int n = 1; n <= N; n++) {
                buildingTime[n] = Integer.parseInt(splitString[n-1]);
                orderList.add(new ArrayList<>());
                dp[n] = buildingTime[n];
            }

            orderList.add(new ArrayList<>());

            for (int k = 0; k < K; k++) {
                splitString = br.readLine().split(" ");

                int parent = Integer.parseInt(splitString[0]);
                int child = Integer.parseInt(splitString[1]);

                orderList.get(child).add(parent);
            }

            W = Integer.parseInt(br.readLine());
            sb.append(dp(W)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static int dp(int n) {

        if (dp[n] != buildingTime[n])
            return dp[n];

        for (Integer integer : orderList.get(n))
            dp[n] = Math.max(dp(integer) + buildingTime[n], dp[n]);

        return dp[n];
    }
}
