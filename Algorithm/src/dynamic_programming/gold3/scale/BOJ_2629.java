package dynamic_programming.gold3.scale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2629 {

    private static int[] weights;
    private static boolean[][] dp;
    private static int weightCount;
    private static final int MAX_BEAD_WEIGHT = 40000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        weightCount = Integer.parseInt(br.readLine());

        weights = new int[weightCount + 1];
        dp = new boolean[weightCount + 1][MAX_BEAD_WEIGHT + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= weightCount; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        checkWeight(0, 0);

        int beadCount = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < beadCount; i++) {
            int bead = Integer.parseInt(st.nextToken());

            if (dp[weightCount][bead])
                sb.append("Y ");
            else
                sb.append("N ");
        }

        System.out.println(sb);
    }

    private static void checkWeight(int num, int weight) {
        if (dp[num][weight]) return;

        dp[num][weight] = true;

        if (num == weightCount) return;

        checkWeight(num + 1, weight);
        checkWeight(num + 1, weights[num+1] + weight);
        checkWeight(num + 1, Math.abs(weights[num+1] - weight));
    }
}